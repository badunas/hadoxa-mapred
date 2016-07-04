package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by badun on 6/24/16.
 */
public class L5_2_s6_Reducer {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String lastKey = null;
        List<String> input = new ArrayList<>();
        while((line = reader.readLine()) != null) {
            String[] split = line.split("\t");
            if (lastKey == null) {
                lastKey = split[0];
            }
            if (!lastKey.equals(split[0])) {
                handle(lastKey, input);
                lastKey = split[0];
                input = new ArrayList<>();
            }
            input.add(line);
        }
        handle(lastKey, input);
    }

    private static void handle(String key, List<String> input) {
        String weight = "INF";
        String graph = "{}";
        for (String s : input) {
            String[] split = s.split("\t");
            if (!weight.equals(split[1]) && !split[1].equals("INF")) {
                if (weight.equals("INF")) {
                    weight = split[1];
                } else {
                    int currentWeight = Integer.parseInt(weight);
                    int newWeight = Integer.parseInt(split[1]);
                    if (currentWeight > newWeight) {
                        weight = split[1];
                    }
                }
            }
            if (!graph.equals(split[2]) && !split[2].equals("{}")) {
                graph = split[2];
            }
        }
        System.out.println(key + "\t" + weight + "\t" + graph);
    }
}
