package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.Auth_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.auth.UserJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SystemUserCardJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SystemUserJDBC;
import com.syntrontech.pmo.auth.model.AccountList;
import com.syntrontech.pmo.syncare1.model.SystemUser;
import com.syntrontech.pmo.syncare1.model.SystemUserCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SyncUserCard {

    private static Logger logger = LoggerFactory.getLogger(SyncUserCard.class);

    public void syncCard() throws SQLException {

        SystemUserJDBC systemUserJDBC = new SystemUserJDBC();

        SystemUserCardJDBC systemUserCardJDBC = new SystemUserCardJDBC();

        List<SystemUserCard> cards = null;
        List<SQLException> exceptions = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        try {
            cards = systemUserCardJDBC.getAll();
        } catch (SQLException e) {
            logger.warn("getAll systemUserCard  fail");
            throw e;
        }

        Connection syncare1conn = new Syncare1_GET_CONNECTION().getConn();
        Connection authconn = new Auth_GET_CONNECTION().getConn();
        try {
        	
        	for (SystemUserCard card : cards) {

                SystemUser user = systemUserJDBC.getSystemUserById(syncare1conn, String.valueOf(card.getSystemUser()));

                if(user == null || user.getUserAccount() == null)
                    errors.add("sync card fail because not found system user card =>" + card);

                UserJDBC userJDBC = new UserJDBC();
                AccountList newCard = userJDBC.InsertAccountList(authconn, user.getUserAccount(), card.getCardId());
                if(newCard != null)
                	systemUserCardJDBC.updateById(card.getId());


            }
        	
        }catch (SQLException e) {
            logger.warn("InsertAccountList  fail");
            exceptions.add(e);
        } finally {

            try {
            	authconn.close();
            	syncare1conn.close();
            } catch (SQLException e) {
                logger.debug("conn or pstmt close fail" + syncare1conn + " || " + authconn);
                e.printStackTrace();
            }

        }
        if(exceptions.size() != 0)
        	exceptions.forEach(e -> System.out.println(e));

        if(errors.size() != 0)
            errors.forEach(e -> System.out.println(e));
        else
            logger.info("sync card successful " + cards.size());
    }

}
