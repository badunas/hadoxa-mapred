package com.hadoxa.stowfl.statistics.hdx35.stepic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by badun on 6/18/16.
 */
public class L4_2_s9_Reducer {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String lastKey = null;
        List<String> lastCounter = new ArrayList<>();
        while((line = reader.readLine()) != null) {
            String[] keyValue = line.split("\t");
            String key = keyValue[0];
            if (lastKey == null) {
                lastKey = key;
            }
            if (key.equals(lastKey)) {
                lastKey = key;
                lastCounter.add(keyValue[1]);
            } else {
                publishOut(lastKey, lastCounter);
                lastKey = key;
                lastCounter = new ArrayList<>();
                lastCounter.add(keyValue[1]);
            }
        }
        publishOut(lastKey, lastCounter);
    }

    private static void publishOut(String lastKey, List<String> lastCounter) {
        List<String> queries = new ArrayList<>();
        List<String> urls = new ArrayList<>();
        for (String s : lastCounter) {
            String[] strings = s.split(":");
            if (strings[0].equals("query")) {
                queries.add(strings[1]);
            } else if (strings[0].equals("url")) {
                urls.add(strings[1]);
            }
        }
        for (String query : queries) {
            for (String url : urls) {
                System.out.println(lastKey + "\t" + query + "\t" + url);
            }
        }
    }
}
