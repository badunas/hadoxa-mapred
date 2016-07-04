package com.hadoxa.stowfl.statistics.hdx35.step2_posttags;

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
public class PostTagsMapper extends Mapper<LongWritable,Text,LongWritable,PostTagsTuple> {
    public static final String POST_TYPE_QUESTION = "1";

    private static final Gson postParser = new Gson();
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    private LongWritable outKey = new LongWritable();
    private PostTagsTuple outValue = new PostTagsTuple();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        PostJson post = postParser.fromJson(value.toString(), PostJson.class);
        if (!post.getPostTypeId().equals(POST_TYPE_QUESTION) || post.getTags() == null) {
            return;
        }
        long postDate;
        try {
            postDate = dateFormat.parse(post.getCreationDate()).getTime();
        } catch (Exception e) {
            postDate = -1;
        }
        outKey.set(Long.parseLong(post.getId()));
        outValue.fill(post.getTags(), postDate, postDate);
        context.write(outKey, outValue);
    }
}
