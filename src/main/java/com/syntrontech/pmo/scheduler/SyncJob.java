package com.syntrontech.pmo.scheduler;

import java.util.Date;

import com.syntrontech.pmo.JDBC.Sync;
import com.syntrontech.pmo.JDBC.SyncDevice;
import com.syntrontech.pmo.JDBC.SyncUnit;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SyncJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(SyncJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {


        Date fireTime = context.getFireTime();

        logger.info("Start sync syncare1 data fireTime:" + fireTime.toInstant());

        new SyncUnit().syncLocationToUnit();
        new SyncDevice().syncDevice();
        new Sync().syncSystemUserToUserAndSubject();

        System.out.println("sync successful");
    }

}
