package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.Auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.cip.CIP_GET_CONNECTION;
import com.syntrontech.pmo.JDBC.cip.DeviceJDBC;
import com.syntrontech.pmo.JDBC.cip.UnitMetaJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.DeviceSyncare1JDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.LocationJDBC;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.cip.model.UnitMeta;
import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.syncare1.model.Device;
import com.syntrontech.pmo.syncare1.model.Location;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Sync {

    public static void main(String[] args) {

        Sync sync = new Sync();
        sync.syncLocationToUnit();
//        sync.syncDevice();
    }

    public void syncLocationToUnit(){

        LocationJDBC locationJDBC = new LocationJDBC();
        UnitJDBC unitJDBC = new UnitJDBC();
        UnitMetaJDBC unitMetaJDBC = new UnitMetaJDBC();

        List<Location> locations = locationJDBC.getAllLocation(new Syncare1_GET_CONNECTION().getConn());

        locations.forEach(l -> {
            if(unitJDBC.getUnitById(l.getId()).getId() == null)
                unitJDBC.insertUnit(convertLocationToUnit(l));
            if(unitMetaJDBC.getUnitMetaById(l.getId()).getUnitId() == null)
                unitMetaJDBC.insertUnitMeta(convertLocationToUnitMeta(l));
        });
//        離島 : [綠島鄉, 蘭嶼鄉]
//        台東市 : [卑南鄉, 台東市]
//        南迴線 : [太麻里鄉, 卑南鄉, 金峰鄉, 大武鄉, 達仁鄉]
//        海線 : [長濱鄉, 東河鄉, 成功鎮]
//        縱谷線 : [關山鎮, 海端鄉, 延平鄉, 鹿野鄉, 池上鄉]
//        海岸線 : [長濱鄉, 成功鎮, 東河鄉]
//        離島 : [1001411, 1001416]
//        台東市 : [1001401, 1001404]
//        南迴線 : [1001414, 1001410, 1001409, 1001415, 1001404]
//        海線 : [1001402, 1001408, 1001407]
//        縱谷線 : [1001412, 1001403, 1001413, 1001405, 1001406]
//        海岸線 : [1001402, 1001408, 1001407]

    }

    private UnitMeta convertLocationToUnitMeta(Location l) {
        return null;
    }

    private Unit convertLocationToUnit(Location l) {
        return null;
    }

    public void syncDevice(){

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
