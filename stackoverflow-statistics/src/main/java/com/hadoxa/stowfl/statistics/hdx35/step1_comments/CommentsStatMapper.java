package com.hadoxa.stowfl.statistics.hdx35.step1_comments;

import com.google.gson.Gson;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by badun on 5/15/16.
 */
public class CommentsStatMapper extends Mapper<LongWritable,Text,LongWritable,CommentsStatTuple> {
    private static final Gson commentParser = new Gson();
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    private LongWritable outKey = new LongWritable();
    private CommentsStatTuple outValue = new CommentsStatTuple();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        CommentJson comment = commentParser.fromJson(value.toString(), CommentJson.class);
        long commentDate;
        try {
            commentDate = dateFormat.parse(comment.getCreationDate()).getTime();
        } catch (Exception e) {
            commentDate = -1;
        }
        outKey.set(Long.parseLong(comment.getPostId()));
        outValue.fill(1, commentDate, commentDate);
        context.write(outKey, outValue);
    }
}