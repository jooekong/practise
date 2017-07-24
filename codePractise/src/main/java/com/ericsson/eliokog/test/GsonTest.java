package com.ericsson.eliokog.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by eliokog on 2017/6/13.
 */
public class GsonTest {

    public static void main(String[] args) {

        String jsonStr =" {\"data\": [{\n" +
                "    \t\t\"device-model\": \"vedge-cloud\",\n" +
                "    \t\t\"ompPeers\": \"1\",\n" +
                "    \t\t\"statusOrder\": 4,\n" +
                "    \t\t\"device-os\": \"next\",\n" +
                "    \t\t\"timezone\": \"America/Los_Angeles\",\n" +
                "    \t\t\"max-controllers\": \"0\",\n" +
                "    \t\t\"latitude\": \"37.666684\",\n" +
                "    \t\t\"system-ip\": \"1.1.13.1\",\n" +
                "    \t\t\"site-id\": \"300\",\n" +
                "    \t\t\"deviceId\": \"1.1.13.1\",\n" +
                "    \t\t\"reachability\": \"reachable\"\n" +
                "    \t},\n" +
                "    \t{\n" +
                "    \t\t\"device-model\": \"vedge-cloud\",\n" +
                "    \t\t\"ompPeers\": \"1\",\n" +
                "    \t\t\"statusOrder\": 4,\n" +
                "    \t\t\"device-os\": \"next\",\n" +
                "    \t\t\"timezone\": \"America/Los_Angeles\",\n" +
                "    \t\t\"max-controllers\": \"0\",\n" +
                "    \t\t\"latitude\": \"37.666684\",\n" +
                "    \t\t\"system-ip\": \"1.1.11.1\",\n" +
                "    \t\t\"site-id\": \"100\",\n" +
                "    \t\t\"deviceId\": \"1.1.11.1\",\n" +
                "    \t\t\"reachability\": \"reachable\"\n" +
                "    \t}\n" +
                "    ]} ";

        Gson gson = new Gson();
        Type type = new TypeToken< Map<String, List<Map<String, String>>>>() {}.getType();
        Map<String, List<Map<String, String>>> map = gson.fromJson(jsonStr, type);
        map.get("data").stream().map(m -> m.get("device-model")).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println( map);
        upperBound(new ArrayList<Integer>());
    }

    public static void upperBound(List<? extends Number> list) {
        Object obj = list.get(0); // Number是Object的子类，使用Object可以代替Number。
        Number num = list.get(0);
    }
}
