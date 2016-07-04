package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by badun on 6/18/16.
 */
public class L4_1_s12_Reducer {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> set = new HashSet<>();
        String line;
        while((line = reader.readLine()) != null) {
            set.add(line);
        }
        Map<String, Integer> map = new TreeMap<>();
        for (String s : set) {
            String[] strings = s.split("\t");
            Integer counter = map.get(strings[1]);
            if (counter != null) {
                counter++;
                map.put(strings[1], counter);
            } else {
                map.put(strings[1], 1);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }
}
