package com.lelek.cv;

import java.util.LinkedHashMap;
import java.util.Map;

public class PropertiesMap {
    public static Map<String, String> properties = new LinkedHashMap<>();

    public static void addToMapProperties(String name, String value){
        properties.put(name, value);
    }

    public static Map<String, String> getFields(){
        return properties;
    }
}
