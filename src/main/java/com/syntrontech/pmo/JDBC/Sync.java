package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.cip.CIP_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.cip.DeviceJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.DeviceSyncare1JDBC;
import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.syncare1.model.Device;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Sync {

    public static void main(String[] args) {

        Sync sync = new Sync();

        sync.syncDriver();
    }

    public void syncDriver(){

        DeviceSyncare1JDBC syncare1DeviceJDBC = new DeviceSyncare1JDBC();

        List<Device> syncare1Devices = syncare1DeviceJDBC.getAllDevice(new Syncare1_GET_CONNECTION().getConn());

        DeviceJDBC cipDeviceJDBC = new DeviceJDBC();

        syncare1Devices.forEach(sd -> {
            insertCIPDevice(cipDeviceJDBC, new CIP_GET_CONNECTION().getConn(), sd);
            syncare1DeviceJDBC.updateDevice(new Syncare1_GET_CONNECTION().getConn(), sd.getSerialNo());
        });

        System.out.println("sync devices total :" + syncare1Devices.size() + " successful");

    }

    private void insertCIPDevice(DeviceJDBC cipDeviceJDBC, Connection cip_conn, Device sd) {

        // TODO
        com.syntrontech.pmo.cip.model.Device device = new com.syntrontech.pmo.cip.model.Device();

        String serialNo = sd.getSerialNo();
        device.setId(serialNo);
        device.setName(sd.getName());
        device.setSerialNumber(sd.getSerialNo());
        device.setUnitId(sd.getLocation());
        device.setUnitName(sd.getSyntronLocationId());
        device.setTenantId("DEFAULT_TENANT");
        Date date = new Date();
        device.setCreateTime(new Date());
        device.setCreateBy("systemAdmin");
        device.setUpdateTime(date);
        device.setUpdateBy("systemAdmin");
        device.setStatus(ModelStatus.ENABLED);

        cipDeviceJDBC.insertDevice(cip_conn, device);

    }
}
