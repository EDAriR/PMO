package com.syntrontech.pmo.sync;

public class TestSyncSyncareService {
    public static void main(String[] args){

        SendPUTRequest sendPUTRequestApp = new SendPUTRequest();
        sendPUTRequestApp.doPut2(ServiceName.Device.geturl());
//        sendPUTRequestApp.doPut2(ServiceName.Subject.geturl());
//        sendPUTRequestApp.doPut2(ServiceName.UnitMeta.geturl());
    }
}
