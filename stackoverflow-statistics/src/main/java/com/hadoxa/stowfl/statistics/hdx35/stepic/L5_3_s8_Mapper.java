package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by badun on 6/24/16.
 */
public class L5_3_s8_Mapper {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = reader.readLine()) != null) {
            System.out.println(line);
            String[] split = line.split("\t");
            handle(new BigDecimal(split[1]), split[2]);
        }
    }

    private static void handle(BigDecimal rank, String graph) {
        String[] nodes = parseGraph(graph);
        String nodeRank = rank.divide(new BigDecimal(nodes.length), MathContext.DECIMAL32).setScale(3, BigDecimal.ROUND_HALF_UP).toPlainString();
        for (String node : nodes) {
            System.out.println(node + "\t" + nodeRank + "\t{}");
        }
    }

    private static String[] parseGraph(String s) {
        return s.replaceAll("[{}]", "").replaceAll(",", " ").split(" ");
    }
}
