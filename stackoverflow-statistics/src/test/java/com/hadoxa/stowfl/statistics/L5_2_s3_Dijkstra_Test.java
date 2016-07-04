package com.hadoxa.stowfl.statistics;

import com.hadoxa.stowfl.statistics.hdx35.stepic.L5_2_s3_Dijkstra;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by badun on 6/23/16.
 */
public class L5_2_s3_Dijkstra_Test {
    private L5_2_s3_Dijkstra dijkstra;

    @Before
    public void init() {
        dijkstra = new L5_2_s3_Dijkstra();
    }

    @Test
    public void test1() throws IOException {
        String in = "4 8\n" +
                "1 2 6\n" +
                "1 3 2\n" +
                "1 4 10\n" +
                "2 4 4\n" +
                "3 1 5\n" +
                "3 2 3\n" +
                "3 4 8\n" +
                "4 2 1\n" +
                "1 4";
        int result = dijkstra.execute(toReader(in));
        Assert.assertEquals(9, result);
    }

    @Test
    public void test2() throws IOException {
        String in = "4 8\n" +
                "1 2 6\n" +
                "1 3 2\n" +
                "1 4 8\n" +
                "2 4 4\n" +
                "3 1 5\n" +
                "3 2 3\n" +
                "3 4 8\n" +
                "4 2 1\n" +
                "1 4";
        int result = dijkstra.execute(toReader(in));
        Assert.assertEquals(8, result);
    }

    @Test
    public void test3() throws IOException {
        String in = "5 5\n" +
                "1 3 4\n" +
                "2 4 1\n" +
                "3 2 3\n" +
                "3 5 2\n" +
                "5 4 6\n" +
                "1 4";
        int result = dijkstra.execute(toReader(in));
        Assert.assertEquals(8, result);
    }

    @Test
    public void test4() throws IOException {
        String in = "2 1\n" +
                "1 2 1\n" +
                "1 2";
        int result = dijkstra.execute(toReader(in));
        Assert.assertEquals(1, result);
    }

    @Test
    public void test5() throws IOException {
        String in = "3 2\n" +
                "1 2 10\n" +
                "2 3 1\n" +
                "1 3";
        int result = dijkstra.execute(toReader(in));
        Assert.assertEquals(11, result);
    }

    @Test
    public void test6() throws IOException {
        String in = "4 2\n" +
                "3 1 5\n" +
                "4 2 1\n" +
                "4 1";
        int result = dijkstra.execute(toReader(in));
        Assert.assertEquals(-1, result);
    }

    private BufferedReader toReader(String in) {
        return new BufferedReader(new StringReader(in));
    }
}

