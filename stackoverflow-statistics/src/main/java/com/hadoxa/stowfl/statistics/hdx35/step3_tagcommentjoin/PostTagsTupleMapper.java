package com.hadoxa.stowfl.statistics.hdx35.step3_tagcommentjoin;

import com.hadoxa.stowfl.statistics.hdx35.step2_posttags.PostTagsTuple;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by badun on 5/22/16.
 */
public class PostTagsTupleMapper extends Mapper<LongWritable,PostTagsTuple,LongWritable,TagCommentTuple> {
    private TagCommentTuple outValue = new TagCommentTuple();

    @Override
    protected void map(LongWritable key, PostTagsTuple value, Context context) throws IOException, InterruptedException {
        outValue.fill(
                true,
                value.getMinDate(),
                value.getMaxDate(),
                0,
                value.getTags()
        );
        context.write(key, outValue);
    }
}
