package com.hadoxa.stowfl.statistics.hdx35.step3_tagcommentjoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by badun on 5/19/16.
 */
public class TagCommentJoinReducer extends Reducer<LongWritable,TagCommentTuple,Text,FinalTagCommentStatTuple> {
    private Text outKey = new Text();
    private FinalTagCommentStatTuple outValue = new FinalTagCommentStatTuple();

    @Override
    protected void reduce(LongWritable key, Iterable<TagCommentTuple> values, Context context) throws IOException, InterruptedException {
        String[] tags = null;
        for (TagCommentTuple value : values) {
            if (value.isTag()) {
                tags = value.getTags().replaceAll("><", " ").replaceAll("[<>]", "").split(" ");
                outValue.fillFromTag(
                        value.getMinDate(),
                        value.getMaxDate()
                );
            } else {
                outValue.fillFromComment(
                        value.getCommentCount(),
                        value.getMinDate(),
                        value.getMaxDate()
                );
            }
        }
        if (tags != null) {
            for (String tag : tags) {
                outKey.set(tag);
                context.write(outKey, outValue);
            }
        }
    }
}
