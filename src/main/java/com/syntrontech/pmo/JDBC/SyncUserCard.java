package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.UserJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SystemUserCardJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.SystemUserJDBC;
import com.syntrontech.pmo.auth.model.AccountList;
import com.syntrontech.pmo.syncare1.model.SystemUser;
import com.syntrontech.pmo.syncare1.model.SystemUserCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


        for (SystemUserCard card : cards) {

            SystemUser user = systemUserJDBC.getSystemUserById(String.valueOf(card.getSystemUser()));

            if(user == null || user.getUserAccount() == null)
                errors.add("sync card fail because not found system user card =>" + card);

            UserJDBC userJDBC = new UserJDBC();
            try {
                AccountList newCard = userJDBC.InsertAccountList(user.getUserAccount(), card.getCardId());
                if(newCard != null)
                    systemUserCardJDBC.updateById(card.getId());

            } catch (SQLException e) {

                logger.warn("InsertAccountList  fail");
                exceptions.add( e);
                errors.add("sync fail card: " + card + "" + e.getMessage());
            }

        }

        if(errors.size() != 0)
            errors.forEach(e -> System.out.println(e));
        else
            logger.info("sync card successful " + cards.size());
    }

}
