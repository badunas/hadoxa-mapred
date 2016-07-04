package com.hadoxa.stowfl.statistics.hdx35.step3_tagcommentjoin;

import org.apache.hadoop.io.*;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by badun on 5/22/16.
 */
public class TagCommentTuple implements Writable {
    private BooleanWritable isTag;
    private LongWritable minDate;
    private LongWritable maxDate;
    private IntWritable commentCount;
    private Text tags;

    public TagCommentTuple() {
        isTag = new BooleanWritable();
        minDate = new LongWritable();
        maxDate = new LongWritable();
        commentCount = new IntWritable();
        tags = new Text();
    }

    public TagCommentTuple(boolean isTag, long minDate, long maxDate, int commentCount, String tags) {
        this();
        fill(isTag, minDate, maxDate, commentCount, tags);
    }

    public void fill(boolean isTag, long minDate, long maxDate, int commentCount, String tag) {
        this.isTag.set(isTag);
        this.minDate.set(minDate);
        this.maxDate.set(maxDate);
        this.tags.set(tag);
        this.commentCount.set(commentCount);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        isTag.write(dataOutput);
        minDate.write(dataOutput);
        maxDate.write(dataOutput);
        commentCount.write(dataOutput);
        tags.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        isTag.readFields(dataInput);
        minDate.readFields(dataInput);
        maxDate.readFields(dataInput);
        commentCount.readFields(dataInput);
        tags.readFields(dataInput);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagCommentTuple that = (TagCommentTuple) o;

        if (isTag != null ? !isTag.equals(that.isTag) : that.isTag != null) return false;
        if (minDate != null ? !minDate.equals(that.minDate) : that.minDate != null) return false;
        if (maxDate != null ? !maxDate.equals(that.maxDate) : that.maxDate != null) return false;
        if (commentCount != null ? !commentCount.equals(that.commentCount) : that.commentCount != null) return false;
        return tags != null ? tags.equals(that.tags) : that.tags == null;

    }

    @Override
    public int hashCode() {
        int result = isTag != null ? isTag.hashCode() : 0;
        result = 31 * result + (minDate != null ? minDate.hashCode() : 0);
        result = 31 * result + (maxDate != null ? maxDate.hashCode() : 0);
        result = 31 * result + (commentCount != null ? commentCount.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    public boolean isTag() {
        return isTag.get();
    }

    public long getMinDate() {
        return minDate.get();
    }

    public long getMaxDate() {
        return maxDate.get();
    }

    public int getCommentCount() {
        return commentCount.get();
    }

    public String getTags() {
        return tags.toString();
    }
}
