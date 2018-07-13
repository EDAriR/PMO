package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.syncare1.model.TtshbRecluseRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TtshbRecluseRecordJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM ttshb_recluse_record WHERE sync_status = 'N' ORDER BY recluse_id;";


    public static void main(String[] args) throws SQLException {

        TtshbRecluseRecordJDBC s = new TtshbRecluseRecordJDBC();
        Connection conn = new Syncare1_GET_CONNECTION().getConn();

        Date star_time = new Date();
        List<TtshbRecluseRecord> ss = s.getAllTtshbRecluseRecords();
        Date end_time = new Date();

        System.out.println("star_time:" + star_time.toInstant());
        System.out.println("end_time:" + end_time.toInstant());
//        System.out.println("ss size:" + ss.size());

//        ss.forEach(sss -> s.updateLocation(new Syncare1_GET_CONNECTION().getConn(), sss.getId()));

    }

    public List<TtshbRecluseRecord> getAllTtshbRecluseRecords() {

        List<TtshbRecluseRecord> recluseRecords = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;
        Connection conn = new Syncare1_GET_CONNECTION().getConn();

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
//                    recluse_id, id_no, downtown_name, downtown_code, import_date

                    TtshbRecluseRecord recluseRecord = new TtshbRecluseRecord();

                    recluseRecord.setRecluseId(rs.getInt("recluse_id"));
                    recluseRecord.setIdNo(rs.getString("id_no"));
                    recluseRecord.setDowntown_name(rs.getString("downtown_name"));
                    recluseRecord.setDowntownCode(rs.getString("downtown_code"));
//                    recluseRecord.setImportDate(rs.getDate("import_date").getTime());

                    System.out.println("recluseRecordJDBC ==>" + recluseRecord);
                    recluseRecords.add(recluseRecord);
                }
            }

        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("conn or pstmt close fail" + conn + " || "+ pstmt);
                e.printStackTrace();
            }

        }
        return recluseRecords;
    }
}
