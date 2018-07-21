package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.cip.CIP_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.cip.DeviceJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.DeviceSyncare1JDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.syncare1.model.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class SyncDevice {

    private static Logger logger = LoggerFactory.getLogger(SyncDevice.class);

    public void syncDevice(){

        DeviceSyncare1JDBC syncare1DeviceJDBC = new DeviceSyncare1JDBC();

        List<Device> syncare1Devices = syncare1DeviceJDBC.getAllDevice(new Syncare1_GET_CONNECTION().getConn());

        DeviceJDBC cipDeviceJDBC = new DeviceJDBC();

        syncare1Devices.forEach(sd -> {
            insertCIPDevice(cipDeviceJDBC, sd);
            syncare1DeviceJDBC.updateDevice(new Syncare1_GET_CONNECTION().getConn(), sd.getSerialNo());
        });

        logger.info("sync devices total :" + syncare1Devices.size() + " successful");

    }

    private void insertCIPDevice(DeviceJDBC cipDeviceJDBC, Device sd) {

        com.syntrontech.pmo.cip.model.Device device = new com.syntrontech.pmo.cip.model.Device();

        String serialNo = sd.getSerialNo();
        device.setId(serialNo);
        device.setName(sd.getName());
        device.setSerialNumber(sd.getSerialNo());
        device.setUnitId(sd.getLocation());
        device.setUnitName(sd.getSyntronLocationId());
        device.setTenantId("TTSHB");
        Date date = new Date();
        device.setCreateTime(new Date());
        device.setCreateBy("TTSHB");
        device.setUpdateTime(date);
        device.setUpdateBy("TTSHB");
        device.setStatus(ModelStatus.ENABLED);

        cipDeviceJDBC.insertDevice(device);

    }
}
