package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by badun on 6/23/16.
 */
public class L5_2_s5_Mapper {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = reader.readLine()) != null) {
            System.out.println(line);
            String[] split = line.split("\t");
            handle(split[1], parseGraph(split[2]));
        }
    }

    private static String[] parseGraph(String s) {
        if ("{}".equals(s)) {
            return null;
        }
        return s.replaceAll("[{}]", "").replaceAll(",", " ").split(" ");
    }

    private static void handle(String weight, String[] nodeList) {
        if (nodeList == null) {
            return;
        }
        for (String node : nodeList) {
            StringBuilder out = new StringBuilder();
            out.append(node).append("\t");
            if ("INF".equals(weight)) {
                out.append("INF\t{}");
            } else {
                out.append(Integer.parseInt(weight) + 1).append("\t{}");
            }
            System.out.println(out.toString());
        }
    }
}
