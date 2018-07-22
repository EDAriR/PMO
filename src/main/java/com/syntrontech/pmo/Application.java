package com.syntrontech.pmo;


import java.text.ParseException;

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

//
///*
// * 在spring boot中,此為serve啟動的主程式,會執行在此package下的所有程式(可以看成一個package內為一個專案)
// */
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
//@PropertySource(value = "${spring.config.location:classpath:application.yml}")
//@EnableJpaRepositories(
////		basePackages = {
////		"com.syntrontech.pmo.auth.repository.*",
////		"com.syntrontech.pmo.cip.repository.*",
////		"com.syntrontech.pmo.syncare1.repository.*",
////		"com.syntrontech.pmo.solr.*"
////
////}
//)
//@EntityScan(
////		basePackages = {
////		"com.syntrontech.pmo.auth.model.*",
////		"com.syntrontech.pmo.cip.model.*",
////		"com.syntrontech.pmo.syncare1.model.*"
////}
//)
//@EnableTransactionManagement
public class Application {

    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException {

//        http://puremonkey2010.blogspot.com/2014/07/java-quartz-scheduler-22.html
//        http://www.quartz-scheduler.org/documentation/

        // 创建 SchedulerFactory 并获取 Scheduler 对象实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // 通过 JobBuilder 创建 JobDetail 对象实例
        JobDetail jobDetail = JobBuilder.newJob(SyncJob.class)
                .withIdentity("helloJob", Scheduler.DEFAULT_GROUP)
                .build();

        // 通过 TriggerBuilder 创建 Trigger 对象实例，设置每 5 秒调度一次任务
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("helloTrigger", Scheduler.DEFAULT_GROUP)
                .withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression("0/5 * * * * ?")))
                .build();

        // 排定任务
        scheduler.scheduleJob(jobDetail, trigger);

        // 启动调度器
        scheduler.start();
        //
        Thread.sleep(20L * 1000L);
        //  关闭调度器
        scheduler.shutdown(true);


//        new SpringApplicationBuilder()
//                .sources(Application.class)
//                .run(args);
    }

}
