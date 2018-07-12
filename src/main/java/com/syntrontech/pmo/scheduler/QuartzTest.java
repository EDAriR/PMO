package com.syntrontech.pmo.scheduler;

import org.apache.log4j.PropertyConfigurator;

public class QuartzTest {

    public static void main(String args[])
    {

//        http://puremonkey2010.blogspot.com/2014/07/java-quartz-scheduler-22.html
//        http://www.quartz-scheduler.org/documentation/
//        try {
//            // Configuration of log4j
//            PropertyConfigurator.configure("log4j.properties");
//
//            // Grab the Scheduler instance from the Factory
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//
//            // and start it off
//            scheduler.start();
//
//            // define the job and tie it to our HelloJob class -> JobBuilder.newJob()
//            JobDetail job = newJob(HelloJob.class)
//                    .withIdentity("job1", "group1")
//                    .build();
//
//            // Trigger the job to run now, and then repeat every 40 seconds -> TriggerBuilder.newTrigger()
//            Trigger trigger = new Trigger()
//                    .withIdentity("trigger1", "group1")
//                    .startNow()
//                    .withSchedule(simpleSchedule()
//                            .withIntervalInSeconds(40)
//                            .repeatForever())
//                    .build();
//
//            // Tell quartz to schedule the job using our trigger
//            scheduler.scheduleJob(job, trigger);
//            Thread.sleep(1000*60*5); // Sleep 5 minutes
//            scheduler.shutdown();
//
//        }
//        catch (SchedulerException se)
//        {
//            se.printStackTrace();
//        }
//        catch (Exception e)
//        {
//
//        }

    }
}
