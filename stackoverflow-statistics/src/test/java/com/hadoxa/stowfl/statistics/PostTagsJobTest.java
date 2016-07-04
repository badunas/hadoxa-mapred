package com.hadoxa.stowfl.statistics;

import com.hadoxa.stowfl.statistics.hdx35.step2_posttags.PostTagsMapper;
import com.hadoxa.stowfl.statistics.hdx35.step2_posttags.PostTagsTuple;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by badun on 5/16/16.
 */
public class PostTagsJobTest {
    MapDriver<LongWritable,Text,LongWritable,PostTagsTuple> mapDriver;

    @Before
    public void setUp() {
        PostTagsMapper mapper = new PostTagsMapper();
        mapDriver = MapDriver.newMapDriver(mapper);
    }

    @Test
    public void testMapper() throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("Posts.txt");
        String text = IOUtils.lineIterator(resourceAsStream, "utf-8").next();
        mapDriver.withInput(
                new LongWritable(1),
                new Text(text)
        );
        mapDriver.withOutput(
                new LongWritable(35314),
                new PostTagsTuple(
                        "<html><css><css3><internet-explorer-7>",
                        1217531288620L,
                        1217531288620L
                )
        );
        mapDriver.runTest();
    }
}
