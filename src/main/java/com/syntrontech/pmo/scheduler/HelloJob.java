package com.syntrontech.pmo.scheduler;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class HelloJob implements Job {

    /**
     * 实 现 org.quartz.Job 接口，并实现 execute 方法，在此方法执行业务逻辑
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Date fireTime = context.getFireTime();

        System.out.println("qqqqqqqqqqqqqqqq");
    }

}
