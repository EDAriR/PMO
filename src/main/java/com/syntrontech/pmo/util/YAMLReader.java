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
        Map<String, Map<String, String>> obj = (LinkedHashMap<String, Map<String, String>>) yaml.load(in);
        System.out.println("Loaded object type:" + obj.getClass());

        for (String s:obj.keySet()){
            System.out.println(obj.get(s));
        }
    }

    public void getSetting(){

//        Yaml yaml = new Yaml();
    }
}
