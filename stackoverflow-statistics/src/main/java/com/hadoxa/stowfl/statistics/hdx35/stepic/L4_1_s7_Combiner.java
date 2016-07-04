package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by badun on 6/18/16.
 */
public class L4_1_s7_Combiner {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String lastKey = null;
        List<Integer> lastItems = new ArrayList<Integer>();
        while((line = reader.readLine()) != null) {
            String[] keyValue = line.split("\t");
            String key = keyValue[0];
            if (lastKey == null) {
                lastKey = key;
            }
            if (key.equals(lastKey)) {
                lastKey = key;
                lastItems.add(parseTime(keyValue[1]));
            } else {
                System.out.println(lastKey + "\t" + calcAvg(lastItems));
                lastKey = key;
                lastItems = new ArrayList<Integer>();
                lastItems.add(parseTime(keyValue[1]));
            }
        }
        System.out.println(lastKey + "\t" + calcAvg(lastItems));
    }

    private static String calcAvg(List<Integer> lastItems) {
        int sum = 0;
        int count = 0;
        for (Integer lastItem : lastItems) {
            count++;
            sum += lastItem;
        }
        return sum + ";" + count;
    }

    private static int parseTime(String in) {
        String[] strings = in.split(";");
        return Integer.parseInt(strings[0]);
    }
}
