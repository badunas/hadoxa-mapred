package com.hadoxa.stowfl.statistics.hdx35.step4_finalstat;

import com.hadoxa.stowfl.statistics.hdx35.step3_tagcommentjoin.FinalTagCommentStatTuple;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by badun on 5/22/16.
 */
public class FinalTagCommentStatTupleMapper extends Mapper<Text,FinalTagCommentStatTuple,Text,FinalTagCommentStatTuple> {

    @Override
    protected void map(Text key, FinalTagCommentStatTuple value, Context context) throws IOException, InterruptedException {
        super.map(key, value, context);
    }
}
