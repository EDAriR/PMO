package com.syntrontech.pmo.JDBC.pmo;

import com.syntrontech.pmo.pmo.PmoResult;
import com.syntrontech.pmo.pmo.PmoUser;

import java.sql.Connection;

public class PmoUserJDBC {

    private static final String GET_ALL_STMT = "SELECT * FROM unit WHERE tenant_id='TTSHB' ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO unit " +
            "(sequence, id, name, parent_id, parent_name, tenant_id, meta, createtime, createby," +
            "updatetime, updateby, status) "
            + "VALUES (nextval('unit_sequence_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String GET_ONE = "SELECT * FROM unit WHERE id=? and tenant_id='TTSHB'" +
            " AND status='ENABLED';";

    public static void main(String[] args) {

    }

    public PmoUser insert(PmoUser pmoUser){

        Connection conn = new PMO_GET_CONNECTION().getConn();

        return pmoUser;
    }
}
