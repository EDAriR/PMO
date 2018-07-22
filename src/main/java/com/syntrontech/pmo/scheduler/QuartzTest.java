package com.syntrontech.pmo.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;

public class QuartzTest {

    public static void main(String args[]) throws SchedulerException, InterruptedException, ParseException {

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
        // 关闭调度器
//        scheduler.shutdown(true);

    }
}
