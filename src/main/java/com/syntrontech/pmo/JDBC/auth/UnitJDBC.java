package com.syntrontech.pmo.JDBC.auth;

import com.syntrontech.pmo.auth.model.Unit;
import com.syntrontech.pmo.model.common.ModelStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class UnitJDBC {

    private static Logger logger = LoggerFactory.getLogger(Auth_GET_CONNECTION.class);

    private static final String GET_ALL_STMT = "SELECT * FROM unit WHERE tenant_id='TTSHB' ORDER BY sequence;";
    private static final String INSERT_STMT = "INSERT INTO unit " +
            "(sequence, id, name, parent_id, parent_name, tenant_id, meta, createtime, createby," +
            "updatetime, updateby, status) "
            + "VALUES (nextval('unit_sequence_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String GET_ONE = "SELECT * FROM unit WHERE id=? and tenant_id='TTSHB'" +
            " AND status='ENABLED';";

    private static final String GET_ONE_BY_NAME = "SELECT * FROM unit WHERE name=? and tenant_id='TTSHB'" +
            " AND status='ENABLED';";

    private static final Map<String, String> UNIT_ID_MAP = createMap();


// sequence,
// id, name, parent_id,
// parent_name, tenant_id,
// meta, createtime, createby,
// updatetime, updateby, status

    public static void main(String[] args) throws SQLException {

        UnitJDBC s = new UnitJDBC();

        List<Unit> ss = s.getAllUnits();

        System.out.println("ss size:" + ss.size());

        Unit insertTest = s.insertUnit(s.getTestUnit());

        System.out.println("insert");
        System.out.println(insertTest);
        Unit unit = s.getUnitById(insertTest.getId());
        System.out.println("get one");
        System.out.println(unit);

    }

    public Unit getUnitByName(String name) throws SQLException {

        if(name == null)
            return null;
        name = name.trim();

        Connection conn = new Auth_GET_CONNECTION().getConn();

        PreparedStatement pstmt = conn.prepareStatement(GET_ONE_BY_NAME);

        return getOne(conn, pstmt, name);

    }

    public Unit getUnitById(String id) throws SQLException {

        if(id == null)
            return null;
        id = id.trim();

        id = getNewUnitId(id);

        Connection conn = new Auth_GET_CONNECTION().getConn();

        PreparedStatement pstmt = conn.prepareStatement(GET_ONE);

        return getOne(conn, pstmt, id);
    }

    private String getNewUnitId(String id){

        String newId = UNIT_ID_MAP.get(id);

        if(newId == null || newId.equals(""))
            return id;
        else
            return newId;
    }

    private static Map<String, String> createMap() {

        Map<String, String> idMap = new ConcurrentHashMap<>();

        // 台東市衛生所 其他平板
        idMap.put("100140102599", "100140102501");

        // 關山鎮衛生所 江美枝
        idMap.put("100140300399", "100140300499001");
        // 關山鎮衛生所 江美枝
        idMap.put("100140300499", "100140300499001");

        // 成功鎮衛生所 王麗婷  博愛路山東麓32號
        idMap.put("100140200199", "100140200299001");
        // 成功鎮衛生所 王麗婷 成廣路8-1號
        idMap.put("100140200299", "100140200299002");

        // 100140401099	台東縣卑南鄉衛生所	卑南鄉	太平村和平路132號	陳美琴	089382030
        idMap.put("100140401099", "100140401099001");
        // 100140401299	台東縣卑南鄉衛生所	卑南鄉	東興村東園一街119號	陳四德	0928275059
        idMap.put("100140401299", "100140401299001");
        // 100140400499	台東縣卑南鄉衛生所	卑南鄉	富源103號	吳見鑑	0937731309
        idMap.put("100140400499", "100140401099002");

        // 100140500399	台東縣鹿野鄉衛生所	鹿野鄉	瑞源村8鄰瑞景路二段3號	張清榮	089581249
        idMap.put("100140500399", "100140500399001");
        // 100140500699	台東縣鹿野鄉衛生所	鹿野鄉	鹿野村中華路1段418號	許懷文	089551074
        idMap.put("100140500699", "100140500699001");

        // 100140600199	台東縣池上鄉衛生所	池上鄉	福文村中山路104號	詹筱凡	089862609
        idMap.put("100140600199", "100140600199001");

        // 100140700399	台東縣東河鄉衛生所	東河鄉	東河村10鄰300號	劉曉菁	089896289
        idMap.put("100140700399", "100140700399001");

        // 100140800499	台東縣長濱鄉衛生所	長濱鄉	長濱村中興路7鄰46號	郭馨月	089831022
        idMap.put("100140800499", "100140800499005");

        // 100140900599	台東縣太麻里鄉衛生所	太麻里鄉	泰和村2鄰民權路66號	胡惠雯	089781220
        idMap.put("100140900599", "100140900599002");
        // 100140900899	台東縣太麻里鄉衛生所	太麻里鄉	金崙村1鄰24號	葉薇臻	0912700058
        idMap.put("100140900899", "100140900599003");

        // 100141000399	台東縣大武鄉衛生所	大武鄉	大武村濱海路100號	林怡玲	089791325
        idMap.put("100141000399", "100141000399004");

        // 100141100299	台東縣綠島鄉衛生所	綠島鄉	中寮村61號	陳新傳	08967255
        idMap.put("100141100299", "100141100299001");

        // 100141200499	台東縣海端鄉衛生所	海端鄉	海端鄉加拿6-1之12號	胡寶貴	0925211028
        idMap.put("100141200499", "100141200499001");

        // 100141300599	台東縣延平鄉衛生所	延平鄉	桃源村1鄰11號	林芝心	089561040
        idMap.put("100141300599", "100141300599001");

        // 100141400499	台東縣金峰鄉衛生所	金峰鄉	賓茂村1鄰14號	高于琋	089771176
        idMap.put("100141400499", "100141400499001");

        // 100141400501	臺灣原住民族部落終身學習發展協會-金崙	太麻里鄉	太麻里街253號
        idMap.put("100141400501", "100140900504");

        // 100141500499	台東縣達仁鄉衛生所	達仁鄉	安朔村13鄰復興路122號	許燕雲	089702209
        idMap.put("100141500499", "100141500499001");

        // 100141600199	台東縣蘭嶼鄉衛生所	蘭嶼鄉	東清97號	蔡邑敏	0911357283
        idMap.put("100141600199", "100141600399001");

        // 100141600399	台東縣蘭嶼鄉衛生所	蘭嶼鄉	紅頭村36號	蔡邑敏	089732575
        idMap.put("100141600399", "100141600399002");

        // 100140102502	中華郵政股份有限公司台東郵局
        idMap.put("100140102502", "100140102501");

        // 100140100302	法務部矯正署岩灣技能訓練所
        idMap.put("100140100302", "100140102501");

        // 100140101501	台東天后宮
        idMap.put("100140101501", "100140102501");

        // 100140102302	鼎東客運海線營運區
        idMap.put("100140102302", "100140102501");

        // 100140102503	合作金庫東台東分行
        idMap.put("100140102503", "100140102501");

        // 100140103101	台東縣後備指揮部
        idMap.put("100140103101", "100140102501");

        // 100140103801	交通部公路總局高雄區管理所台東監理站
        idMap.put("100140103801", "100140102501");

        // 100140200103	成功鎮公所
        idMap.put("100140200103", "100140200102");

        // 100140800402	長濱鄉農會
        idMap.put("100140800402", "100140800401");

        // 100140900502	太麻里鄉郵局
        idMap.put("100140900502", "100140900701");

        // 100140900801	金崙互助社
        idMap.put("100140900801", "100140900701");

        // 100141600401	財團法人蘭嶼部落文化基金會	蘭嶼鄉	椰油村12-1號
        idMap.put("100141600401", "100141600301");

        // 100141600499	台東縣蘭嶼鄉衛生所	蘭嶼鄉	椰油村189號	呂吧漫	089732473
        idMap.put("100141600499", "100141600301");

        return Collections.unmodifiableMap(idMap);
    }

    private Unit getOne(Connection conn, PreparedStatement pstmt, String id) throws SQLException {

        Unit unit = new Unit();

        try {

            pstmt.setString(1, id);
            logger.info(pstmt.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    unit.setSequence(rs.getLong("sequence"));
                    unit.setId(rs.getString("id"));
                    unit.setName(rs.getString("name"));
                    unit.setParentId(rs.getString("parent_id"));
                    unit.setParentName(rs.getString("parent_name"));
                    unit.setTenantId(rs.getString("tenant_id"));
                }
            }

        } catch (SQLException e) {
            logger.debug("get Unit  fail " + pstmt);
            throw e;
        } finally {

            try {
                if (pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.info("conn or pstmt close fail " + pstmt);
                e.printStackTrace();
            }

        }
        logger.info("getUnitById successful => " + unit);
        return unit;
    }



    public Unit insertUnit(Unit unit) throws SQLException {

        if(unit == null || unit.getId() == null)
            return null;

        Connection conn = new Auth_GET_CONNECTION().getConn();
        PreparedStatement pstmt =null;

        Unit old = getUnitById(unit.getId());
        if (old != null){
            if (old.getId() != null && !old.getId().equals(""))
                return old;
        }


        try {
            pstmt = conn.prepareStatement(INSERT_STMT);

            // id, name, parent_id,
            pstmt.setString(1, unit.getId().trim());
            pstmt.setString(2, unit.getName().trim());
            pstmt.setString(3, unit.getParentId());
            // parent_name, tenant_id,
            pstmt.setString(4, unit.getParentName());
            pstmt.setString(5, unit.getTenantId());
            // meta, createtime, createby,
            pstmt.setString(6, unit.getMeta());
            pstmt.setTimestamp(7, new java.sql.Timestamp(unit.getCreateTime().getTime()));
            pstmt.setString(8, unit.getCreateBy());
            // updatetime, updateby, status
            pstmt.setTimestamp(9, new java.sql.Timestamp(unit.getUpdateTime().getTime()));
            pstmt.setString(10, unit.getUpdateBy());
            pstmt.setString(11, unit.getStatus().toString());

//            logger.info(pstmt.toString());
            logger.info(" UnitJDBC: " + pstmt.toString());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.debug("insertUnit fail " + pstmt + "||" + conn);
            throw e;
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
                logger.info("conn or pstmt close fail " + pstmt);
                e.printStackTrace();
            }

        }
        logger.info("create unit successful ==> " + unit);
//        System.out.println(Calendar.getInstance().getTime() +" UnitJDBC: " + "create unit successful ==> " + unit);
        return unit;
    }

    public Unit getTestUnit() {

        Unit unit = new Unit();

        unit.setId("JDBCTest66");
        unit.setName("JDBCTest");
        unit.setParentId("1001401");
        unit.setParentName("台東市");
        unit.setTenantId("TTSHB");
        unit.setMeta(null);
        unit.setCreateTime(new Date());
        unit.setCreateBy("TTSHB");
        unit.setUpdateTime(new Date());
        unit.setUpdateBy("TTSHB");
        unit.setStatus(ModelStatus.ENABLED);

        return unit;
    }

    List<Unit> getAllUnits(){

        Connection conn = new Auth_GET_CONNECTION().getConn();
        List<Unit> units = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs;

        try {
            pstmt = conn.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    Unit unit = new Unit();

                    unit.setSequence(rs.getLong("sequence"));
                    unit.setId(rs.getString("id"));
                    unit.setName(rs.getString("name"));
                    unit.setParentId(rs.getString("parent_id"));
                    unit.setParentName(rs.getString("parent_name"));
                    unit.setTenantId(rs.getString("tenant_id"));
                    unit.setMeta(rs.getString("meta"));
                    unit.setCreateTime(rs.getDate("createtime"));
                    unit.setCreateBy(rs.getString("createby"));
                    unit.setUpdateTime(rs.getTimestamp("updatetime"));
                    unit.setUpdateBy(rs.getString("updateby"));
                    unit.setStatus(ModelStatus.valueOf(rs.getString("status")));

//                    logger.info("unit:" + unit);
                    units.add(unit);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if(pstmt != null)
                    pstmt.close();
                conn.close();
            } catch (SQLException e) {
//                logger.debug("conn or pstmt close fail" + conn + " || " + pstmt);
                System.out.println(Calendar.getInstance().getTime() +" UnitJDBC: " + "conn or pstmt close fail" + conn + " || " + pstmt);
                e.printStackTrace();
            }

        }

        return units;
    }

}
