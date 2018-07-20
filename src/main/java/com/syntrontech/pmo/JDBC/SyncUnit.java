package com.syntrontech.pmo.JDBC;

import com.syntrontech.pmo.JDBC.auth.UnitJDBC;
import com.syntrontech.pmo.JDBC.cip.UnitMetaJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.LocationJDBC;
import com.syntrontech.pmo.JDBC.syncare1JDBC.Syncare1_GET_CONNECTION;
import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.cip.model.UnitMeta;
import com.syntrontech.pmo.model.common.ModelMgmtStatus;
import com.syntrontech.pmo.model.common.ModelStatus;
import com.syntrontech.pmo.syncare1.model.Location;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncUnit {

    public void syncLocationToUnit(){

        LocationJDBC locationJDBC = new LocationJDBC();
        UnitJDBC unitJDBC = new UnitJDBC();
        UnitMetaJDBC unitMetaJDBC = new UnitMetaJDBC();

        List<Location> locations = locationJDBC.getAllLocation(new Syncare1_GET_CONNECTION().getConn());

        System.out.println(locations.size());
        locations.forEach(l -> {
            System.out.println(l);
            if (l.getId().equals("") || l.getId() == null){

            }else{

                if(unitJDBC.getUnitById(l.getId()).getId() == null)
                    unitJDBC.insertUnit(convertLocationToUnit(l));
                if(unitMetaJDBC.getUnitMetaById(l.getId()).getUnitId() == null)
                    unitMetaJDBC.insertUnitMeta(convertLocationToUnitMeta(l));

                locationJDBC.updateLocation(new Syncare1_GET_CONNECTION().getConn(), l.getId());
            }
        });

        System.out.println("sync unit : " + locations.size() + " successful");

    }

    private UnitMeta convertLocationToUnitMeta(Location location) {

        UnitJDBC unitJDBC = new UnitJDBC();
        UnitMeta unitMeta = new UnitMeta();


        String unitId = location.getId();
        unitMeta.setUnitId(unitId);
        unitMeta.setUnitName(location.getName());

        if(unitId.length() > 7) {
            Unit parentUnit = unitJDBC.getUnitById(unitId.substring(0, 7));
            unitMeta.setUnitParentId(parentUnit.getId());
            unitMeta.setUnitParentName(parentUnit.getName());
        }

        unitMeta.setUnitStatus(ModelMgmtStatus.ENABLED); // ModelMgmtStatus
        unitMeta.setTenantId("DEFAULT_TENANT");

        if (unitId.length() > 6 )
            unitMeta.setCategory(getCategory(unitId.substring(0, 7)));
        else
            unitMeta.setCategory("台東市");
        unitMeta.setContact(location.getContact());
        unitMeta.setAddress(location.getAddress());
        unitMeta.setHomePhone(location.getPhone());
        unitMeta.setMobilePhone(location.getPhone());
        Date date = new Date();
        unitMeta.setCreateTime(date); // Date
        unitMeta.setCreateBy("TTSHB");
        unitMeta.setUpdateTime(date); // Date
        unitMeta.setUpdateBy("TTSHB");
        return unitMeta;
    }

    String getCategory(String category){

        Map<String, String> map = new HashMap<>();
        //        離島 : [1001411, 1001416]
        //        離島 : [綠島鄉, 蘭嶼鄉]
        map.put("1001411","離島");
        map.put("1001416","離島");

        //        台東市 : [1001401, 1001404]
        //        台東市 : [卑南鄉, 台東市]
        map.put("1001401","台東市");

        //        南迴線 : [太麻里鄉, 卑南鄉, 金峰鄉, 大武鄉, 達仁鄉]
        //        南迴線 : [1001414, 1001410, 1001409, 1001415, 1001404]
        map.put("1001404","南迴線");
        map.put("1001410","南迴線");
        map.put("1001409","南迴線");
        map.put("1001415","南迴線");
        map.put("1001404","南迴線");

        //        海線 : [長濱鄉, 東河鄉, 成功鎮]
        //        海線 : [1001402, 1001408, 1001407]
        map.put("1001402","海線");
        map.put("1001408","海線");
        map.put("1001407","海線");

        //        縱谷線 : [關山鎮, 海端鄉, 延平鄉, 鹿野鄉, 池上鄉]
        //        縱谷線 : [1001412, 1001403, 1001413, 1001405, 1001406]
        map.put("1001412","縱谷線");
        map.put("1001403","縱谷線");
        map.put("1001413","縱谷線");
        map.put("1001405","縱谷線");
        map.put("1001406","縱谷線");

        return  map.get(category);

    }

    private Unit convertLocationToUnit(Location location) {

        UnitJDBC unitJDBC = new UnitJDBC();
        Unit unit = new Unit();

        String unitId = location.getId();
        unit.setId(unitId);
        unit.setName(location.getName());
        if(unitId.length() > 7){

            Unit parentUnit = unitJDBC.getUnitById(unitId.substring(0, 7));
            unit.setParentId(parentUnit.getId());
            unit.setParentName(parentUnit.getName());

        }
        unit.setTenantId("TTSHB");
        unit.setMeta(null);
        unit.setCreateTime(new Date());
        unit.setCreateBy("TTSHB");
        unit.setUpdateTime(new Date());
        unit.setUpdateBy("TTSHB");
        unit.setStatus(ModelStatus.ENABLED);

        return unit;
    }
}
