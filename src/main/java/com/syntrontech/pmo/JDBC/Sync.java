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

        Connection syncare_conn = new Syncare1_GET_CONNECTION().getConn();
        Connection cip_conn = new CIP_GET_CONNECTION().getConn();

        sync.syncDriver(syncare_conn, cip_conn);
    }

    public void syncDriver(Connection syncare_conn, Connection cip_conn){

        DeviceSyncare1JDBC syncare1DeviceJDBC = new DeviceSyncare1JDBC();

        List<Device> syncare1Devices = syncare1DeviceJDBC.getAllDevice(syncare_conn);

        DeviceJDBC cipDeviceJDBC = new DeviceJDBC();

        syncare1Devices.forEach(sd -> insertCIPDevice(cipDeviceJDBC, cip_conn, sd));



    }

    private void insertCIPDevice(DeviceJDBC cipDeviceJDBC, Connection cip_conn, Device sd) {

        // TODO
        com.syntrontech.pmo.cip.model.Device device = new com.syntrontech.pmo.cip.model.Device();

        device.setId(sd.getSerialNo());
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
