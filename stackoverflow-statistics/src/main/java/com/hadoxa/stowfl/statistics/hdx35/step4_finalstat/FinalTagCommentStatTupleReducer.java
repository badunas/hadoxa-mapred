package com.hadoxa.stowfl.statistics.hdx35.step4_finalstat;

import com.hadoxa.stowfl.statistics.hdx35.step3_tagcommentjoin.FinalTagCommentStatTuple;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by badun on 5/22/16.
 */
public class FinalTagCommentStatTupleReducer extends Reducer<Text,FinalTagCommentStatTuple,Text,FinalTagCommentStatTuple> {
    private FinalTagCommentStatTuple outValue = new FinalTagCommentStatTuple();

    @Override
    protected void reduce(Text key, Iterable<FinalTagCommentStatTuple> values, Context context) throws IOException, InterruptedException {
        boolean isFirst = true;
        for (FinalTagCommentStatTuple value : values) {
            if (isFirst) {
                isFirst = false;
                outValue.fill(
                        value.getCommentCount(),
                        value.getCommentMinDate(),
                        value.getCommentMaxDate(),
                        value.getPostMinDate(),
                        value.getPostMaxDate()
                );
            } else {
                outValue.setCommentCount(outValue.getCommentCount() + value.getCommentCount());
                if (value.getCommentMinDate() > outValue.getCommentMinDate()) {
                    outValue.setCommentMinDate(value.getCommentMinDate());
                }
                if (value.getCommentMaxDate() > outValue.getCommentMaxDate()) {
                    outValue.setCommentMaxDate(value.getCommentMaxDate());
                }
                if (value.getPostMinDate() > outValue.getPostMinDate()) {
                    outValue.setPostMinDate(value.getPostMinDate());
                }
                if (value.getPostMaxDate() > outValue.getPostMaxDate()) {
                    outValue.setPostMaxDate(value.getPostMaxDate());
                }
            }
        }
        context.write(key, outValue);
    }
}
