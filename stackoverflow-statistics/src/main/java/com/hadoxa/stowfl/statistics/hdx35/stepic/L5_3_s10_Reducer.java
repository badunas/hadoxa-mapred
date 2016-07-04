package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by badun on 6/24/16.
 */
public class L5_3_s10_Reducer {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String lastKey = null;
        TreeMap<String, List<String>> map = new TreeMap<>();
        List<String> input = new ArrayList<>();
        while((line = reader.readLine()) != null) {
            String[] split = line.split("\t");
            if (lastKey == null) {
                lastKey = split[0];
            }
            if (!lastKey.equals(split[0])) {
                map.put(lastKey, input);
                lastKey = split[0];
                input = new ArrayList<>();
            }
            input.add(line);
        }
        map.put(lastKey, input);
        N = map.size();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            handle(entry.getKey(), entry.getValue());
        }
    }

    private static void handle(String lastKey, List<String> input) {
        String graph = null;
        BigDecimal rank = new BigDecimal(0);
        for (String s : input) {
            String[] split = s.split("\t");
            if (!split[2].equals("{}")) {
                graph = split[2];
            } else {
                rank = rank.add(new BigDecimal(split[1]));
            }
        }
        BigDecimal p1 = new BigDecimal(1).divide(new BigDecimal(N), MathContext.DECIMAL32).multiply(new BigDecimal("0.1"));
        rank = rank.multiply(new BigDecimal("0.9")).add(p1);
        System.out.println(lastKey + "\t" + rank.setScale(3, RoundingMode.HALF_UP).toPlainString() + "\t" + graph);
    }
}
