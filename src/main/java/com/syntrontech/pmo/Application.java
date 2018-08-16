package com.syntrontech.pmo;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;

import com.syntrontech.pmo.JDBC.*;
import com.syntrontech.pmo.scheduler.QuartzTest;
import com.syntrontech.pmo.sync.SendPUTRequest;
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

        new SyncAnswers().syncAnswers();

//        sendPUTRequestApp.sendPUTcRequest(ServiceName.User);

//        new SyncRecord().sync();

        new SyncUserCard().syncCard();

        sendPUTRequestApp.sendPUTRequest();
        
//      QuartzTest qt = new QuartzTest();
//      qt.startScheduler();

    }

}
