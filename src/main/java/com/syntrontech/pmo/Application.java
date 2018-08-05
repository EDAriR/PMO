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

//                QuartzTest qt = new QuartzTest();
//                qt.startScheduler();

        System.out.println("Start sync syncare1 data fireTime:" + Calendar.getInstance().getTime());

        // 確認PMO規格 量測資料同步
        new SyncUnit().syncLocationToUnit();

        new SyncDevice().syncDevice();

        new Sync().syncSystemUserToUserAndSubject();

        new SyncAnswers().syncAnswers();

//        sendPUTRequestApp.sendPUTcRequest();

//        sendPUTRequestApp.sendPUTcRequest(ServiceName.User);

        new SyncRecord().sync();

        new SyncUserCard().syncCard();

    }

}
