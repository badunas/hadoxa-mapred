package com.hadoxa.stowfl.statistics;

import com.hadoxa.stowfl.statistics.hdx35.step1_comments.CommentsStatMapper;
import com.hadoxa.stowfl.statistics.hdx35.step1_comments.CommentsStatReducer;
import com.hadoxa.stowfl.statistics.hdx35.step1_comments.CommentsStatTuple;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by badun on 5/10/16.
 */
public class CommentsStatJobTest {
    MapDriver<LongWritable,Text,LongWritable,CommentsStatTuple> mapDriver;
    ReduceDriver<LongWritable,CommentsStatTuple,LongWritable,CommentsStatTuple> reduceDriver;
    MapReduceDriver<LongWritable,Text,LongWritable,CommentsStatTuple,LongWritable,CommentsStatTuple> mapReduceDriver;


    @Before
    public void setUp() {
        CommentsStatMapper mapper = new CommentsStatMapper();
        CommentsStatReducer reducer = new CommentsStatReducer();
        mapDriver = MapDriver.newMapDriver(mapper);
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }

    @Test
    public void testMapper() throws IOException {
        mapDriver.withInput(
                new LongWritable(1),
                new Text(
                        "{\"id\":\"1\",\"postId\":\"35314\",\"score\":\"31\",\"text\":\"Test comment text!\",\"userId\":\"1\",\"creationDate\"=\"2008-09-18T16:07:10.730\"}"
                )
        );
        mapDriver.withOutput(
                new LongWritable(35314),
                new CommentsStatTuple(
                        1,
                        1221743230730L,
                        1221743230730L
                )
        );
        mapDriver.runTest();
    }

    @Test
    public void testReducer() throws IOException {
        List<CommentsStatTuple> values = new ArrayList<>();
        values.add(
                new CommentsStatTuple(
                        1,
                        1221743230000L,
                        1221743230000L
                )
        );
        values.add(
                new CommentsStatTuple(
                        1,
                        1221743230001L,
                        1221743230001L
                )
        );
        reduceDriver.withInput(new LongWritable(35314), values);
        reduceDriver.withOutput(
                new LongWritable(35314),
                new CommentsStatTuple(
                        2,
                        1221743230000L,
                        1221743230001L
                )
         );
        reduceDriver.runTest();
    }

    @Test
    public void testMapReduce() throws IOException {
        mapReduceDriver
                .withInput(
                        new LongWritable(1),
                        new Text(
                                "{\"id\":\"1\",\"postId\":\"35314\",\"score\":\"31\",\"text\":\"Test comment text!\",\"userId\":\"1\",\"creationDate\"=\"2008-09-18T16:07:10.730\"}"
                        )
                )
                .withInput(
                        new LongWritable(1),
                        new Text(
                                "{\"id\":\"1\",\"postId\":\"35314\",\"score\":\"31\",\"text\":\"Test comment text!\",\"userId\":\"1\",\"creationDate\"=\"2008-10-18T16:07:10.730\"}"
                        )
                )
                .withCombiner(new CommentsStatReducer())
                .withOutput(
                        new LongWritable(35314),
                        new CommentsStatTuple(
                                2,
                                1221743230730L,
                                1224335230730L
                        )
        );
        mapReduceDriver.runTest();
    }
}
