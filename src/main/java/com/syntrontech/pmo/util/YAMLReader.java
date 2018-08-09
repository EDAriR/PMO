package com.syntrontech.pmo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class YAMLReader {

    public static void main(String[] args) throws IOException {

        Yaml yaml = new Yaml();
        InputStream in = YAMLReader.class.getResourceAsStream("/setting.yml");
        Object obj = yaml.load(in);
        System.out.println("Loaded object type:" + obj.getClass());
        System.out.println(obj instanceof LinkedHashMap);

        Map map;
        if(obj instanceof LinkedHashMap) {
            map = (LinkedHashMap) obj;
        }else {
            map = new LinkedHashMap();
        }
        Map pg;
        if(map.get("postgresql") instanceof LinkedHashMap)
            pg = (LinkedHashMap)map.get("postgresql");
        else
            pg = new LinkedHashMap();
        System.out.println(pg.get("driver"));
    }

    public void getSetting(){

//        Yaml yaml = new Yaml();
    }
}
