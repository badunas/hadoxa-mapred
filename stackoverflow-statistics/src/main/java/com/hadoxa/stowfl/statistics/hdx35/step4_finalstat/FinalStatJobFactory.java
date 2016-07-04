package com.hadoxa.stowfl.statistics.hdx35.step4_finalstat;

import com.hadoxa.stowfl.statistics.hdx35.BaseJobFactory;
import com.hadoxa.stowfl.statistics.hdx35.step1_comments.CommentsStatTuple;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

/**
 * Created by badun on 5/22/16.
 */
public class FinalStatJobFactory extends BaseJobFactory {

    public FinalStatJobFactory(Configuration conf, Path inputPath, Path outputPath) throws IOException {
        super(conf, inputPath, outputPath);
    }

    @Override
    public Job getJob() throws IOException {
        Job job = Job.getInstance(getConf(), getClass().getSimpleName());
        job.setJarByClass(getClass());

        job.setInputFormatClass(SequenceFileInputFormat.class);

        job.setNumReduceTasks(3);

        job.setMapperClass(FinalTagCommentStatTupleMapper.class);
        job.setCombinerClass(FinalTagCommentStatTupleReducer.class);
        job.setReducerClass(FinalTagCommentStatTupleReducer.class);

        job.setOutputFormatClass(SequenceFileOutputFormat.class);
//        SequenceFileOutputFormat.setCompressOutput(job, true);
//        SequenceFileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
//        SequenceFileOutputFormat.setOutputCompressionType(job, SequenceFile.CompressionType.BLOCK);

        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(CommentsStatTuple.class);

        FileInputFormat.addInputPath(job, getInputPath());
        FileOutputFormat.setOutputPath(job, getOutputPath());

        return job;
    }
}
