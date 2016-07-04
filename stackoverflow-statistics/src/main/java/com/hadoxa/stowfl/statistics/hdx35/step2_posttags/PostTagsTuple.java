package com.hadoxa.stowfl.statistics.hdx35.step2_posttags;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by badun on 5/15/16.
 */
public class PostTagsTuple implements Writable {
    private Text tags;
    private LongWritable minDate;
    private LongWritable maxDate;

    public PostTagsTuple() {
        tags = new Text();
        minDate = new LongWritable();
        maxDate = new LongWritable();
    }

    public PostTagsTuple(String tags, long minDate, long maxDate) {
        this();
        fill(tags, minDate, maxDate);
    }

    public void fill(String commentCount, long minDate, long maxDate) {
        this.tags.set(commentCount);
        this.minDate.set(minDate);
        this.maxDate.set(maxDate);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        minDate.write(dataOutput);
        maxDate.write(dataOutput);
        tags.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        minDate.readFields(dataInput);
        maxDate.readFields(dataInput);
        tags.readFields(dataInput);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostTagsTuple that = (PostTagsTuple) o;

        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        if (minDate != null ? !minDate.equals(that.minDate) : that.minDate != null) return false;
        return maxDate != null ? maxDate.equals(that.maxDate) : that.maxDate == null;

    }

    @Override
    public int hashCode() {
        int result = tags != null ? tags.hashCode() : 0;
        result = 31 * result + (minDate != null ? minDate.hashCode() : 0);
        result = 31 * result + (maxDate != null ? maxDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return tags + " " + minDate + " " + maxDate;
    }

    public String getTags() {
        return tags.toString();
    }

    public long getMinDate() {
        return minDate.get();
    }

    public long getMaxDate() {
        return maxDate.get();
    }
}
