package com.syntrontech.pmo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class YAMLReader {

    public static void main(String[] args) throws IOException {

        Yaml yaml = new Yaml();

        try{

            InputStream in = YAMLReader.class.getResourceAsStream("/setting.yml");

            Map<String, Map<String, String[]>> obj = (LinkedHashMap<String, Map<String, String[]>>) yaml.load(in);
            System.out.println("Loaded object type:" + obj.getClass());

            for (String s:obj.keySet()){
                Map<String, String[]> ss = obj.get(s);
                for(String k:ss.keySet()){
                    System.out.println(ss.get(k));
                }
                System.out.println("xxxxxxxxxxxxxxxxxxxxx");
            }


        }catch (YAMLException e){
            System.out.println(e.getClass());
            e.printStackTrace();
        }


    }

    public static String getSetting(String key1, String key2) throws YAMLException{
        return getSetting(key1).get(key2);
    }


    public static Map<String, String> getSetting(String key) throws YAMLException{

        Yaml yaml = new Yaml();

        InputStream in = YAMLReader.class.getResourceAsStream("/setting.yml");

        Map<String, Map<String, String>> obj = (LinkedHashMap<String, Map<String, String>>) yaml.load(in);

        for (String s:obj.keySet()){
//            System.out.println(obj.get(s));
        }

        return obj.get(key);

    }

    public void getSetting() throws YAMLException{

        Yaml yaml = new Yaml();

        InputStream in = YAMLReader.class.getResourceAsStream("/setting.yml");

        Map<String, Map<String, String>> obj = (LinkedHashMap<String, Map<String, String>>) yaml.load(in);

        for (String s:obj.keySet()){
//            System.out.println(obj.get(s));
        }

    }
}
