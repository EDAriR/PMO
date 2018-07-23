package com.syntrontech.pmo;


import java.text.ParseException;
import java.util.Properties;

import com.syntrontech.pmo.scheduler.SyncJob;
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


        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(SyncJob.class)
                .withIdentity("SyncJob", Scheduler.DEFAULT_GROUP)
                .build();


        // "0 0 12 * * ?" 每天中午12点触发

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("helloTrigger", Scheduler.DEFAULT_GROUP)
                .withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression("0 0/5 * * * ?")))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        // 启动调度器
        scheduler.start();


    }

}
