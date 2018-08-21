package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.measurement.BiochemistryJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.UserValueRecordJDBC;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.cip.model.Subject;
import com.syntrontech.pmo.measurement.Biochemistry;
import com.syntrontech.pmo.measurement.common.MeasurementStatusType;
import com.syntrontech.pmo.model.common.BiochemistryMappingsProject;
import com.syntrontech.pmo.syncare1.model.SystemUser;
import com.syntrontech.pmo.syncare1.model.UserValueRecord;
import com.syntrontech.pmo.syncare1.model.UserValueRecordMapping;
import com.syntrontech.pmo.util.CalendarUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SyncBiochemistry {

    // 暫時不轉
    private void syncBiochemistry(
            Connection authconn, Connection syncare1conn, Connection measurementconn,
            SystemUser su, Subject subject, Map<Integer, List<UserValueRecordMapping>> userValueRecordMap, UserValueRecordJDBC userValueRecordJDBC) {

        BiochemistryJDBC biochemistryJDBC = new BiochemistryJDBC();
        List<UserValueRecord> userBiochemistryJRecords = userValueRecordJDBC.getOneUserOtherValueRecord(su.getUserId());
        userBiochemistryJRecords.forEach(record -> {

            try {
                Biochemistry biochemistry = null;

                biochemistry = turnValueRecordToBiochemistry(authconn, record, subject, userValueRecordMap);

                biochemistryJDBC.insert(measurementconn, biochemistry);
                // update mapping
                userValueRecordJDBC.updateUserValueRecord(syncare1conn, record.getBodyValueRecordId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private Biochemistry setBiochemistryUnitInfo(Connection authconn, Biochemistry biochemistry, Subject subject, UserValueRecord record) throws SQLException {

        UnitJDBC unitJDBC = new UnitJDBC();
        Unit unit = unitJDBC.getUnitById(authconn, record.getLocationId());
        if (unit == null || unit.getId() == null) {
            unit = unitJDBC.getOtherUnit(unit);
        }
        biochemistry.setUnitId(unit.getId());
        biochemistry.setUnitName(unit.getName());
        biochemistry.setParentUnitId(unit.getParentId());
        biochemistry.setParentUnitName(unit.getParentName());

        return biochemistry;

    }

    private Biochemistry turnValueRecordToBiochemistry(Connection authconn, UserValueRecord record, Subject subject, Map<Integer, List<UserValueRecordMapping>> userValueRecordMap) throws SQLException {

        List<UserValueRecordMapping> values = userValueRecordMap.get(record.getBodyValueRecordId());
        if (values.size() == 0)
            return null;

        UserValueRecordMapping value = values.get(0);


        BiochemistryJDBC biochemistryJDBC = new BiochemistryJDBC();

        Biochemistry biochemistry = new Biochemistry();

        biochemistry.setValue(value.getRecordValue());
        biochemistry.setGroupId(biochemistryJDBC.getGroupId());

        biochemistry = setMapping(biochemistry, value);

        // recordtime, latitude, longitude
        biochemistry.setRecordTime(record.getRecordDate());
        biochemistry.setLatitude("0");
        biochemistry.setLongitude("0");

        // status, createtime, createby, tenant_id, device_mac_address
        // private MeasurementStatusType status;
        biochemistry.setStatus(MeasurementStatusType.EXISTED);
        biochemistry.setCreateTime(record.getUpdateDate());
        biochemistry.setCreateBy("TTSHB");
        biochemistry.setTenantId("TTSHB");

        // subject_seq, subject_id, subject_name, subject_gender, subject_age, subject_user_id, subject_user_name,
        biochemistry = setBiochemistrySubjectInfo(biochemistry, subject, record);

        // rule_seq, rule_description, unit_id, unit_name, parent_unit_id, parent_unit_name, device_id
        biochemistry = setBiochemistryUnitInfo(authconn, biochemistry, subject, record);

        return biochemistry;

    }

    private Biochemistry setBiochemistrySubjectInfo(Biochemistry biochemistry, Subject subject, UserValueRecord record) {

        biochemistry.setSubjectSeq(subject.getSequence());
        biochemistry.setSubjectId(subject.getId());
        biochemistry.setSubjectName(subject.getName());
        biochemistry.setSubjectGender(subject.getGender());
        biochemistry.setSubjectAge(CalendarUtil.getAgeFromBirthDate(subject.getBirthday(), record.getRecordDate()));
        biochemistry.setSubjectUserId(subject.getUserId());
        biochemistry.setSubjectUserName(subject.getName());

        return biochemistry;
    }

    private Biochemistry setMapping(Biochemistry biochemistry, UserValueRecordMapping value) {

        // HbA1C 新 1 舊 13
        // Triglyceride 新 2 舊 15
        // Total_Cholesterol 新 3 舊 14
        // HDL 新 4 舊 16
        // LDL 新 5 舊 17
        // GOT 新 6 舊 18
        // GPT 新 7 舊 19
        // Creatinine/RF 新 8 舊 20
        int mappingTypeId = value.getMapping().getTypeId();
        long mappingsSeq = 0L;

        BiochemistryMappingsProject pro = null;
        switch (mappingTypeId) {
            case 13:
                pro = BiochemistryMappingsProject.HbA1C;
                mappingsSeq = 1L;
                break;
            case 15:
                pro = BiochemistryMappingsProject.Triglyceride;
                mappingsSeq = 2L;
                break;
            case 14:
                mappingsSeq = 3L;
                pro = BiochemistryMappingsProject.Total_Cholesterol;
                break;
            case 16:
                mappingsSeq = 4L;
                pro = BiochemistryMappingsProject.HDL_Cholesterol;
                break;
            case 17:
                mappingsSeq = 5L;
                pro = BiochemistryMappingsProject.LDL_Cholesterol;
                break;
            case 18:
                mappingsSeq = 6L;
                pro = BiochemistryMappingsProject.GOT;
                break;
            case 19:
                mappingsSeq = 7L;
                pro = BiochemistryMappingsProject.GPT;
                break;
            case 20:
                mappingsSeq = 8L;
                pro = BiochemistryMappingsProject.Creatinine;
                break;
        }
        biochemistry.setMappingsSeq(mappingsSeq);
        biochemistry.setMappingsProject(pro);

        return biochemistry;
    }
}
