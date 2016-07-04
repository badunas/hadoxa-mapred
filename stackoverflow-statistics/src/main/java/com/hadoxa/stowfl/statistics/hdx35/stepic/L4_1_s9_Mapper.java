package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by badun on 6/18/16.
 */
public class L4_1_s9_Mapper {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = reader.readLine()) != null) {
            String[] strings = line.split("\t");
            String number = strings[0];
            String[] categories = strings[1].split(",");
            for (String category : categories) {
                System.out.println(number + "," + category + "\t1");
            }
        }
    }
}
