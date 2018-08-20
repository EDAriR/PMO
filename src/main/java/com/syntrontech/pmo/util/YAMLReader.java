package com.syntrontech.pmo.util;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class YAMLReader {

    public static String getSetting(String key1, String key2) throws YAMLException {
        return getSetting(key1).get(key2);
    }


    public static Map<String, String> getSetting(String key) throws YAMLException {

        Yaml yaml = new Yaml();

        InputStream in = null;
        try {

            System.out.println("dir : " + System.getProperty("user.dir"));

            String dir = System.getProperty("user.dir");

            in = new FileInputStream(dir + "/setting.yml");

//            in = YAMLReader.class.getResourceAsStream("/opt/syncar_ttshb/setting.yml");
        } catch (FileNotFoundException e) {
            in = YAMLReader.class.getResourceAsStream("/setting.yml");

        } catch (YAMLException e) {
            in = YAMLReader.class.getResourceAsStream("/setting.yml");
        }catch (Exception ex){
            ex.printStackTrace();
        }

        Map<String, Map<String, String>> obj = (LinkedHashMap<String, Map<String, String>>) yaml.load(in);

        return obj.get(key);

    }

}
