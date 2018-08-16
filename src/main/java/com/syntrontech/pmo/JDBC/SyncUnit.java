package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.Auth_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.cip.UnitMetaJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.LocationJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.cip.model.UnitMeta;
import com.syntrontech.pmo.model.common.ModelMgmtStatus;
import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.syncare1.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class SyncUnit {

    private static Logger logger = LoggerFactory.getLogger(SyncUnit.class);

    public static void main(String[] args) {
        new SyncUnit().syncLocationToUnit();
    }

    public void syncLocationToUnit() {

        LocationJDBC locationJDBC = new LocationJDBC();
        UnitJDBC unitJDBC = new UnitJDBC();
        UnitMetaJDBC unitMetaJDBC = new UnitMetaJDBC();

        List<Location> locations = locationJDBC.getAllLocation();

        logger.info("locations :[" + locations.size() + "] need sync");
//        System.out.println(" locations need sync" + locations.size());
        Connection authconn = new Auth_GET_CONNECTION().getConn();
        List<String> errors = new ArrayList<>();
        
        try {
        	for(Location l:locations) {
        		logger.info(l.toString());
                if (l.getId() == null || l.getId().equals("")) {

                } else {

                    String locationId = l.getId();

                    Unit unit = unitJDBC.getUnitById(authconn, locationId);
                    Unit parent = unitJDBC.getUnitByName(authconn, l.getCity());

                    if (unit == null && parent == null) {

                        unit = convertLocationToUnit(authconn, l);
                        unitJDBC.insertUnit(authconn, unit);

                    } else if (unit == null) {
                        unit = convertLocationToUnit(l, parent);
                        unitJDBC.insertUnit(authconn, unit);
                    }

                    UnitMeta unitMeta = unitMetaJDBC.getUnitMetaById(locationId);

                    if (unitMeta == null && parent == null)
                        unitMetaJDBC.insertUnitMeta(convertLocationToUnitMeta(authconn, l));
                    else if (unitMeta == null)
                        unitMetaJDBC.insertUnitMeta(convertLocationToUnitMeta(l, parent));

                    locationJDBC.updateLocation(locationId);
                }
            }
        } catch (SQLException e) {
            errors.add("sync unit fail " + e.getMessage());
            e.printStackTrace();
        }finally {

            try {
            	authconn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail " + authconn);
                e.printStackTrace();
            }

        }
        

        if (errors.size() > 0)
            errors.forEach(e -> System.out.println(e));
        else
            logger.info("sync unit : " + locations.size() + " successful");

    }

    private UnitMeta convertLocationToUnitMeta(Connection authconn, Location location) throws SQLException {

        UnitJDBC unitJDBC = new UnitJDBC();
        UnitMeta unitMeta = new UnitMeta();

        String unitId = location.getId();
        unitMeta.setUnitId(unitId);
        unitMeta.setUnitName(location.getName());

        unitMeta.setUnitStatus(ModelMgmtStatus.ENABLED); // ModelMgmtStatus
        unitMeta.setTenantId("TTSHB");

        if (unitId.length() > 6) {

            Unit parentUnit = unitJDBC.getUnitById(authconn, unitId.substring(0, 7));
            if (parentUnit != null) {
                unitMeta.setUnitParentId(parentUnit.getId());
                unitMeta.setUnitParentName(parentUnit.getName());
            }

        }

        if (unitId.length() > 6)
            unitMeta.setCategory(getCategory(unitId.substring(0, 7)));
        else
            unitMeta.setCategory("台東市");
        unitMeta.setContact(location.getContact());
        unitMeta.setAddress(location.getAddress());
        unitMeta.setHomePhone(location.getPhone());
        unitMeta.setMobilePhone(location.getPhone());
        Date date = new Date();
        unitMeta.setCreateTime(date); // Date
        unitMeta.setCreateBy("TTSHB");
        unitMeta.setUpdateTime(date); // Date
        unitMeta.setUpdateBy("TTSHB");
        return unitMeta;
    }

    private UnitMeta convertLocationToUnitMeta(Location location, Unit parent) {

        UnitMeta unitMeta = new UnitMeta();

        String unitId = location.getId();
        unitMeta.setUnitId(unitId);
        unitMeta.setUnitName(location.getName());

        unitMeta.setUnitParentId(parent.getId());
        unitMeta.setUnitParentName(parent.getName());

        unitMeta.setUnitStatus(ModelMgmtStatus.ENABLED); // ModelMgmtStatus
        unitMeta.setTenantId("TTSHB");

        unitMeta.setCategory(getCategory(parent.getId()));
        unitMeta.setUnitParentId(parent.getId());
        unitMeta.setUnitParentName(parent.getName());

        unitMeta.setContact(location.getContact());
        unitMeta.setAddress(location.getAddress());
        unitMeta.setHomePhone(location.getPhone());
        unitMeta.setMobilePhone(location.getPhone());
        Date date = new Date();
        unitMeta.setCreateTime(date); // Date
        unitMeta.setCreateBy("TTSHB");
        unitMeta.setUpdateTime(date); // Date
        unitMeta.setUpdateBy("TTSHB");
        return unitMeta;
    }

    private String getCategory(String category) {

        Map<String, String> map = new HashMap<>();
        //        離島 : [1001411, 1001416]
        //        離島 : [綠島鄉, 蘭嶼鄉]
        map.put("1001411", "離島");
        map.put("1001416", "離島");

        //        台東市 : [1001401, 1001404]
        //        台東市 : [卑南鄉, 台東市]
        map.put("1001401", "台東市");

        //        南迴線 : [太麻里鄉, 卑南鄉, 金峰鄉, 大武鄉, 達仁鄉]
        //        南迴線 : [1001414, 1001410, 1001409, 1001415, 1001404]
        map.put("1001404", "南迴線");
        map.put("1001410", "南迴線");
        map.put("1001409", "南迴線");
        map.put("1001415", "南迴線");
        map.put("1001404", "南迴線");

        //        海線 : [長濱鄉, 東河鄉, 成功鎮]
        //        海線 : [1001402, 1001408, 1001407]
        map.put("1001402", "海線");
        map.put("1001408", "海線");
        map.put("1001407", "海線");

        //        縱谷線 : [關山鎮, 海端鄉, 延平鄉, 鹿野鄉, 池上鄉]
        //        縱谷線 : [1001412, 1001403, 1001413, 1001405, 1001406]
        map.put("1001412", "縱谷線");
        map.put("1001403", "縱谷線");
        map.put("1001413", "縱谷線");
        map.put("1001405", "縱谷線");
        map.put("1001406", "縱谷線");

        return map.get(category);

    }

    private Unit convertLocationToUnit(Connection authconn, Location location) throws SQLException {

        UnitJDBC unitJDBC = new UnitJDBC();
        Unit unit = new Unit();

        String unitId = location.getId();
        unit.setId(unitId);
        unit.setName(location.getName());

        if (unitId.length() > 6) {

            Unit parentUnit = unitJDBC.getUnitById(authconn, unitId.substring(0, 7));
            if (parentUnit != null) {
                unit.setParentId(parentUnit.getId());
                unit.setParentName(parentUnit.getName());
            }

        }

        unit.setTenantId("TTSHB");
        unit.setMeta(null);
        unit.setCreateTime(new Date());
        unit.setCreateBy("TTSHB");
        unit.setUpdateTime(new Date());
        unit.setUpdateBy("TTSHB");
        unit.setStatus(ModelStatus.ENABLED);

        return unit;
    }

    private Unit convertLocationToUnit(Location location, Unit parent) {

        Unit unit = new Unit();

        String unitId = location.getId();
        unit.setId(unitId);
        unit.setName(location.getName());

        unit.setParentId(parent.getId());
        unit.setParentName(parent.getName());

        unit.setTenantId("TTSHB");
        unit.setMeta(null);
        unit.setCreateTime(new Date());
        unit.setCreateBy("TTSHB");
        unit.setUpdateTime(new Date());
        unit.setUpdateBy("TTSHB");
        unit.setStatus(ModelStatus.ENABLED);

        return unit;
    }
}
