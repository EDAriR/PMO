package com.syntrontech.pmo.scheduler;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class SyncJob implements Job {

//    private static Logger logger = LoggerFactory.getLogger(SyncJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {


        Date fireTime = context.getFireTime();

        System.out.println("Start sync syncare1 data fireTime:" + fireTime.toInstant());

//        new SyncUnit().syncLocationToUnit();
//        new SyncDevice().syncDevice();
//        new Sync().syncSystemUserToUserAndSubject();
//
//        SendPUTRequest sendPUTRequestApp = new SendPUTRequest();
//        sendPUTRequestApp.sendSyncRequest();

        System.out.println("sync successful");
    }

}
