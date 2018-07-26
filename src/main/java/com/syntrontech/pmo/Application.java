package com.syntrontech.pmo;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

import com.syntrontech.pmo.JDBC.*;
import com.syntrontech.pmo.scheduler.QuartzTest;
import com.syntrontech.pmo.scheduler.SyncJob;
import com.syntrontech.pmo.sync.SendPUTRequest;
import com.syntrontech.pmo.sync.ServiceName;
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

        SendPUTRequest sendPUTRequestApp = new SendPUTRequest();

//                QuartzTest qt = new QuartzTest();
//                qt.startScheduler();

        System.out.println("Start sync syncare1 data fireTime:" + new Date().toInstant());

//        new SyncUnit().syncLocationToUnit();

//        new SyncDevice().syncDevice();

//        new Sync().syncSystemUserToUserAndSubject();

//        new SyncAnswers().syncAnswers();

//        sendPUTRequestApp.sendPUTcRequest();

//        sendPUTRequestApp.sendPUTcRequest(ServiceName.User);

//        new SyncRecord().sync();

        new SyncUserCard().syncCard();

    }

}
