package com.syntrontech.pmo;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;

import com.syntrontech.pmo.JDBC.*;
import com.syntrontech.pmo.scheduler.QuartzTest;
import com.syntrontech.pmo.sync.SendPUTRequest;
import com.syntrontech.pmo.sync.ServiceName;
import org.quartz.*;

public class Application {

    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException, SQLException {

        SendPUTRequest sendPUTRequestApp = new SendPUTRequest();

        System.out.println("Start sync syncare1 data fireTime:" + Calendar.getInstance().getTime());

        // 確認PMO規格 量測資料同步
        // new SyncUnit().syncLocationToUnit();

        // 8/15 討論結果避免髒資料 不做同步
//        new SyncDevice().syncDevice();

        new Sync().syncSystemUserToUserAndSubject();

        Thread t = new Thread(){
            @Override
            public void run() {
                sendPUTRequestApp.sendPUTRequest(ServiceName.User);
                sendPUTRequestApp.sendPUTRequest(ServiceName.Subject);
                sendPUTRequestApp.sendPUTRequest(ServiceName.EmergencyContact);
                super.run();
            }
        };

        t.start();

        new SyncAnswers().syncAnswers();


        Thread t2 = new Thread(){
            @Override
            public void run() {
                sendPUTRequestApp.sendPUTRequest(ServiceName.Biochemistry);
                sendPUTRequestApp.sendPUTRequest(ServiceName.BodyInfo);
                sendPUTRequestApp.sendPUTRequest(ServiceName.Questionnaire);
                super.run();
            }
        };

        t2.start();


        sendPUTRequestApp.sendPUTRequest(ServiceName.BloodPressureHeartBeat);
        sendPUTRequestApp.sendPUTRequest(ServiceName.AbnormalBloodPressure);
        sendPUTRequestApp.sendPUTRequest(ServiceName.AbnormalBloodPressureLog);


//        sendPUTRequestApp.sendPUTcRequest(ServiceName.User);

//        new SyncRecord().sync();

//        new SyncUserCard().syncCard();

//        sendPUTRequestApp.sendPUTRequest();
        
//      QuartzTest qt = new QuartzTest();
//      qt.startScheduler();

    }

}
