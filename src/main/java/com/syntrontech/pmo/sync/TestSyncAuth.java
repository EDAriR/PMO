package com.syntrontech.pmo.sync;


public class TestSyncAuth {


    public static void main(String[] args){

        SendPUTRequest sendPUTRequestApp = new SendPUTRequest();
        sendPUTRequestApp.doPut2(ServiceName.Unit.geturl());
        sendPUTRequestApp.doPut2(ServiceName.User.geturl());
    }

}
