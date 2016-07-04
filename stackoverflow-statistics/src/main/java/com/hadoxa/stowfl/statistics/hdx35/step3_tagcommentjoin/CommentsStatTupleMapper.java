package com.hadoxa.stowfl.statistics.hdx35.step3_tagcommentjoin;

import com.hadoxa.stowfl.statistics.hdx35.step1_comments.CommentsStatTuple;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by badun on 5/19/16.
 */
public class CommentsStatTupleMapper extends Mapper<LongWritable,CommentsStatTuple,LongWritable,TagCommentTuple> {
    private TagCommentTuple outValue = new TagCommentTuple();

    @Override
    protected void map(LongWritable key, CommentsStatTuple value, Context context) throws IOException, InterruptedException {
        outValue.fill(
                false,
                value.getMinDate(),
                value.getMaxDate(),
                value.getCommentCount(),
                ""
        );
        context.write(key, outValue);
    }
}
