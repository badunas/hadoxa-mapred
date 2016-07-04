package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by badun on 6/18/16.
 */
public class L4_1_s14_Mapper {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = reader.readLine()) != null) {
            String[] strings = line.split(" ");
            for (String s1 : strings) {
                for (String s2 : strings) {
                    if (!s1.equals(s2)) {
                        System.out.println(s1 + "," + s2 + "\t1");
                    }
                }
            }
        }
    }
}
