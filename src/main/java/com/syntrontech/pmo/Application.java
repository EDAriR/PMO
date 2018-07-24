package com.syntrontech.pmo;


import java.text.ParseException;
import java.util.Date;
import java.util.Properties;

import com.syntrontech.pmo.JDBC.Sync;
import com.syntrontech.pmo.JDBC.SyncAnswers;
import com.syntrontech.pmo.JDBC.SyncDevice;
import com.syntrontech.pmo.JDBC.SyncUnit;
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

    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException {


//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        Scheduler scheduler = schedulerFactory.getScheduler();
//
//        JobDetail jobDetail = JobBuilder.newJob(SyncJob.class)
//                .withIdentity("SyncJob", Scheduler.DEFAULT_GROUP)
//                .build();
//
//
//        // "0 0 12 * * ?" 每天中午12点触发
//
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("helloTrigger", Scheduler.DEFAULT_GROUP)
//                .withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression("0 0/5 * * * ?")))
//                .build();
//
//        scheduler.scheduleJob(jobDetail, trigger);
//
//        // 启动调度器
//        scheduler.start();

        System.out.println("Start sync syncare1 data fireTime:" + new Date().toInstant());

        new SyncUnit().syncLocationToUnit();
        new SyncDevice().syncDevice();
        new Sync().syncSystemUserToUserAndSubject();
        new SyncAnswers().syncAnswers();
//
        SendPUTRequest sendPUTRequestApp = new SendPUTRequest();
        sendPUTRequestApp.sendSyncRequest();

    }

}
