package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by badun on 6/18/16.
 */
public class L4_1_s15_Mapper {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = reader.readLine()) != null) {
            String[] strings = line.split(" ");
            for (String s1 : strings) {
                Map<String, Integer> map = new TreeMap<>();
                for (String s2 : strings) {
                    if (!s1.equals(s2)) {
                        Integer counter = map.get(s2);
                        if (counter != null) {
                            counter++;
                            map.put(s2, counter);
                        } else {
                            map.put(s2, 1);
                        }
                    }
                }
                StringBuilder stripe = new StringBuilder();
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    if (stripe.length() >  0) {
                        stripe.append(",");
                    }
                    stripe.append(entry.getKey()).append(":").append(entry.getValue());
                }
                System.out.println(s1 + "\t" + stripe.toString());
            }
        }
    }
}
