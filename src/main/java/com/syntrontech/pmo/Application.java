package com.syntrontech.pmo;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

import com.syntrontech.pmo.JDBC.Sync;
import com.syntrontech.pmo.JDBC.SyncAnswers;
import com.syntrontech.pmo.JDBC.SyncDevice;
import com.syntrontech.pmo.JDBC.SyncUnit;
import com.syntrontech.pmo.scheduler.QuartzTest;
import com.syntrontech.pmo.scheduler.SyncJob;
import com.syntrontech.pmo.sync.SendPUTRequest;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

public class Application {

    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException, SQLException {

//        System.out.println("args = " + args.length);
        SendPUTRequest sendPUTRequestApp = new SendPUTRequest();
//        if (args.length > 0) {
//            if (args[0].equals(2)) {
//                MysqlAlertTableAddCloumSyncStatus msq = new MysqlAlertTableAddCloumSyncStatus();
//
//                msq.alertTable();
//            }else if(args[0].equals(1)){
//                QuartzTest qt = new QuartzTest();
//                qt.startScheduler();
//            }else if(args[0].equals(3)){
//                sendPUTRequestApp.sendSyncRequest();
//            }
//        }
//        System.out.println(args[0]);

        System.out.println("Start sync syncare1 data fireTime:" + new Date().toInstant());

        new SyncUnit().syncLocationToUnit();
        new SyncDevice().syncDevice();
        new Sync().syncSystemUserToUserAndSubject();
        new SyncAnswers().syncAnswers();
//
        sendPUTRequestApp.sendSyncRequest();

    }

}
