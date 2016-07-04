package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by badun on 6/18/16.
 */
public class L4_2_s7_Reducer {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String lastKey = null;
        String lastCounter = "";
        String key = null;
        while((line = reader.readLine()) != null) {
            String[] keyValue = line.split("\t");
            key = keyValue[0];
            if (lastKey == null) {
                lastKey = key;
            }
            if (key.equals(lastKey)) {
                lastKey = key;
                String s = keyValue[1];
                lastCounter += keyValue[1];
            } else {
                if (lastCounter.length() < 2) {
                    System.out.println(lastKey);
                }
                lastKey = key;
                lastCounter = keyValue[1];
            }
        }
        if (lastCounter.length() < 2) {
            System.out.println(lastKey);
        }
    }
}
