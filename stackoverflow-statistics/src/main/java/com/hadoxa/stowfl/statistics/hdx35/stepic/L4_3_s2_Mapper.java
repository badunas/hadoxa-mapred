package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by badun on 6/18/16.
 */
public class L4_3_s2_Mapper {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = reader.readLine()) != null) {
            int delimIndex = line.indexOf(":");
            String doc = line.substring(0, delimIndex).trim();
            String wordsString = line.substring(delimIndex + 1).trim().replaceAll("[^a-zA-Z0-9 ]", " ").replaceAll(" +", " ");
            String[] words = wordsString.split(" ");
            for (String word : words) {
                if (word.length() > 0) {
                    System.out.println(word + "#" + doc + "\t1");
                }
            }
        }
    }
}
