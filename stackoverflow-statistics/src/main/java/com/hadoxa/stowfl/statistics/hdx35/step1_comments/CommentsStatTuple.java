package com.hadoxa.stowfl.statistics.hdx35.step1_comments;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by badun on 5/15/16.
 */
public class CommentsStatTuple implements Writable {
    private IntWritable commentCount;
    private LongWritable minDate;
    private LongWritable maxDate;

    public CommentsStatTuple() {
        commentCount = new IntWritable();
        minDate = new LongWritable();
        maxDate = new LongWritable();
    }

    public CommentsStatTuple(int commentCount, long minDate, long maxDate) {
        this();
        fill(commentCount, minDate, maxDate);
    }

    public void fill(int commentCount, long minDate, long maxDate) {
        this.commentCount.set(commentCount);
        this.minDate.set(minDate);
        this.maxDate.set(maxDate);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        commentCount.write(dataOutput);
        minDate.write(dataOutput);
        maxDate.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        commentCount.readFields(dataInput);
        minDate.readFields(dataInput);
        maxDate.readFields(dataInput);
    }

    @Override
    public String toString() {
        return "stat: " + commentCount +
                " " + minDate +
                " " + maxDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentsStatTuple that = (CommentsStatTuple) o;

        if (commentCount != null ? !commentCount.equals(that.commentCount) : that.commentCount != null) return false;
        if (minDate != null ? !minDate.equals(that.minDate) : that.minDate != null) return false;
        return maxDate != null ? maxDate.equals(that.maxDate) : that.maxDate == null;

    }

    @Override
    public int hashCode() {
        int result = commentCount != null ? commentCount.hashCode() : 0;
        result = 31 * result + (minDate != null ? minDate.hashCode() : 0);
        result = 31 * result + (maxDate != null ? maxDate.hashCode() : 0);
        return result;
    }

    public int getCommentCount() {
        return commentCount.get();
    }

    public long getMinDate() {
        return minDate.get();
    }

    public long getMaxDate() {
        return maxDate.get();
    }
}