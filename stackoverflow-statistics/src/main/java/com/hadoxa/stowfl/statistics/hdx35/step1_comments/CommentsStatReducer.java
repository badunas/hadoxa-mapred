package com.hadoxa.stowfl.statistics.hdx35.step1_comments;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by badun on 5/15/16.
 */
public class CommentsStatReducer extends Reducer<LongWritable,CommentsStatTuple,LongWritable,CommentsStatTuple> {
    private CommentsStatTuple outValue = new CommentsStatTuple();

    @Override
    protected void reduce(LongWritable key, Iterable<CommentsStatTuple> values, Context context) throws IOException, InterruptedException {
        int counter = 0;
        long minDate = 0;
        long maxDate = 0;
        for (CommentsStatTuple value : values) {
            counter += value.getCommentCount() > 0 ? value.getCommentCount() : 1;
            if (minDate == 0 || minDate > value.getMinDate()) {
                minDate = value.getMinDate();
            }
            if (maxDate == 0 || maxDate < value.getMaxDate()) {
                maxDate = value.getMaxDate();
            }
        }
        outValue.fill(counter, minDate, maxDate);
        context.write(key, outValue);
    }
}