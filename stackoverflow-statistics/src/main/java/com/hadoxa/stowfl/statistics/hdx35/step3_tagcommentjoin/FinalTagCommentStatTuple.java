package com.hadoxa.stowfl.statistics.hdx35.step3_tagcommentjoin;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

/**
 * Created by badun on 5/22/16.
 */
public class FinalTagCommentStatTuple {
    private IntWritable commentCount;
    private LongWritable postMinDate;
    private LongWritable postMaxDate;
    private LongWritable commentMinDate;
    private LongWritable commentMaxDate;

    public FinalTagCommentStatTuple() {
        commentCount = new IntWritable();
        postMinDate = new LongWritable();
        postMaxDate = new LongWritable();
        commentMinDate = new LongWritable();
        commentMaxDate = new LongWritable();
    }

    public FinalTagCommentStatTuple(int commentCount, long commentMinDate, long commentMaxDate, long postMinDate, long postMaxDate) {
        this();
        fill(commentCount, commentMinDate, commentMaxDate, postMinDate, postMaxDate);
    }

    public void fill(int commentCount, long commentMinDate, long commentMaxDate, long postMinDate, long postMaxDate) {
        fillFromComment(commentCount, commentMinDate, commentMaxDate);
        fillFromTag(postMinDate, postMaxDate);
    }

    public void fillFromComment(int commentCount, long commentMinDate, long commentMaxDate) {
        this.commentCount.set(commentCount);
        this.commentMinDate.set(commentMinDate);
        this.commentMaxDate.set(commentMaxDate);
    }

    public void fillFromTag(long postMinDate, long postMaxDate) {
        this.postMinDate.set(postMinDate);
        this.postMaxDate.set(postMaxDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FinalTagCommentStatTuple that = (FinalTagCommentStatTuple) o;

        if (commentCount != null ? !commentCount.equals(that.commentCount) : that.commentCount != null) return false;
        if (postMinDate != null ? !postMinDate.equals(that.postMinDate) : that.postMinDate != null) return false;
        if (postMaxDate != null ? !postMaxDate.equals(that.postMaxDate) : that.postMaxDate != null) return false;
        if (commentMinDate != null ? !commentMinDate.equals(that.commentMinDate) : that.commentMinDate != null)
            return false;
        return commentMaxDate != null ? commentMaxDate.equals(that.commentMaxDate) : that.commentMaxDate == null;

    }

    @Override
    public int hashCode() {
        int result = commentCount != null ? commentCount.hashCode() : 0;
        result = 31 * result + (postMinDate != null ? postMinDate.hashCode() : 0);
        result = 31 * result + (postMaxDate != null ? postMaxDate.hashCode() : 0);
        result = 31 * result + (commentMinDate != null ? commentMinDate.hashCode() : 0);
        result = 31 * result + (commentMaxDate != null ? commentMaxDate.hashCode() : 0);
        return result;
    }

    public int getCommentCount() {
        return commentCount.get();
    }

    public long getPostMinDate() {
        return postMinDate.get();
    }

    public long getPostMaxDate() {
        return postMaxDate.get();
    }

    public long getCommentMinDate() {
        return commentMinDate.get();
    }

    public long getCommentMaxDate() {
        return commentMaxDate.get();
    }

    public void setCommentCount(int commentCount) {
        this.commentCount.set(commentCount);
    }

    public void setPostMinDate(long postMinDate) {
        this.postMinDate.set(postMinDate);
    }

    public void setPostMaxDate(long postMaxDate) {
        this.postMaxDate.set(postMaxDate);
    }

    public void setCommentMinDate(long commentMinDate) {
        this.commentMinDate.set(commentMinDate);
    }

    public void setCommentMaxDate(long commentMaxDate) {
        this.commentMaxDate.set(commentMaxDate);
    }
}
