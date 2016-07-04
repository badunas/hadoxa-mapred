package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by badun on 6/18/16.
 */
public class L4_3_s3_Reducer {

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
                lastCounter += Integer.parseInt(keyValue[1]);
            } else {
                publishOut(lastKey, lastCounter);
                lastKey = key;
                lastCounter = 1;
            }
        }
        publishOut(lastKey, lastCounter);
    }

    private static void publishOut(String lastKey, int lastCounter) {
        String[] split = lastKey.split("#");
        System.out.println(split[0] + "\t" + split[1] + "\t" + lastCounter);
    }
}
