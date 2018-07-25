package com.syntrontech.pmo.JDBC.syncare1JDBC;

import com.syntrontech.pmo.syncare1.model.SystemUserCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SystemUserCardJDBC {

    private static Logger logger = LoggerFactory.getLogger(SystemUserCardJDBC.class);


    private static final String GET_ALL_STMT = "SELECT * FROM system_user_card WHERE sync_status = 'N' ORDER BY id;";
    private static final String UPDATE = "UPDATE card SET sync_status= 'Y' WHERE id=? ;";

    public static void main(String[] args) {

        SystemUserCardJDBC cardJDBC = new SystemUserCardJDBC();

        List<SystemUserCard> cards = null;
        try {
            cards = cardJDBC.getAll();
        } catch (SQLException e) {
        }

        Map<String, List<SystemUserCard>> cardMap = cards.stream()
                .collect(Collectors.groupingBy(SystemUserCard::getSystemUser));
        System.out.println(cardMap);

    }

    public List<SystemUserCard> getAll() throws SQLException {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();

        List<SystemUserCard> cards = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {

            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    SystemUserCard card = new SystemUserCard();

                    card.setId(rs.getInt("id"));
                    card.setCardId(rs.getString("card_id"));
                    card.setCardName(rs.getString("card_name"));
                    card.setSystemUser(rs.getString("user_id"));

//                    System.out.println("cardJDBC ==>" + card);
                    cards.add(card);
                }
            }

        } catch (SQLException e) {
            logger.warn("get All cards fails");
            e.printStackTrace();
            throw e;
        } finally {

            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.warn("conn or pstmt close fail" + pstmt);
                e.printStackTrace();
            }

        }
        return cards;
    }

    public void updateById(int id) {

        Connection conn = new Syncare1_GET_CONNECTION().getConn();

        PreparedStatement pstmt = null;

        try {

            pstmt = conn.prepareStatement(UPDATE);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("update [" + id + "] successful");


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
                System.out.println("conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }
    }
}
