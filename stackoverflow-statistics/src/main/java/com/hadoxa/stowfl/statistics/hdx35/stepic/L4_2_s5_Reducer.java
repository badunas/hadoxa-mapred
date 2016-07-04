package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * Created by badun on 6/18/16.
 */
public class L4_2_s5_Reducer {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String lastKey = null;
        int lastCounter = 0;
        while((line = reader.readLine()) != null) {
            String[] keyValue = line.split("\t");
            String key = keyValue[0];
            if (lastKey == null) {
                lastKey = key;
            }
            if (key.equals(lastKey)) {
                lastKey = key;
                lastCounter++;
            } else {
                if (lastCounter > 1) {
                    System.out.println(lastKey);
                }
                lastKey = key;
                lastCounter = 1;
            }
        }
        if (lastCounter > 1) {
            System.out.println(lastKey);
        }
    }
}
