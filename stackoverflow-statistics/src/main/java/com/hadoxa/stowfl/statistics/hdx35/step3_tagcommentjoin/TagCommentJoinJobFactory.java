package com.hadoxa.stowfl.statistics.hdx35.step3_tagcommentjoin;

import com.hadoxa.stowfl.statistics.hdx35.BaseJobFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

/**
 * Created by badun on 5/22/16.
 */
public class TagCommentJoinJobFactory extends BaseJobFactory {
    private Path extraInputPath;

    public TagCommentJoinJobFactory(Configuration conf, Path inputPath, Path extraInputPath, Path outputPath) throws IOException {
        super(conf, inputPath, outputPath);
        this.extraInputPath = extraInputPath;
    }

    @Override
    public Job getJob() throws IOException {
        Job job = Job.getInstance(getConf(), getClass().getSimpleName());
        job.setJarByClass(getClass());

        job.setInputFormatClass(SequenceFileInputFormat.class);

        MultipleInputs.addInputPath(job, getInputPath(), SequenceFileInputFormat.class, CommentsStatTupleMapper.class);
        MultipleInputs.addInputPath(job, getExtraInputPath(), SequenceFileInputFormat.class, PostTagsTupleMapper.class);

        job.setReducerClass(TagCommentJoinReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FinalTagCommentStatTuple.class);

        job.setOutputFormatClass(SequenceFileOutputFormat.class);
//        SequenceFileOutputFormat.setCompressOutput(job, true);
//        SequenceFileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
//        SequenceFileOutputFormat.setOutputCompressionType(job, SequenceFile.CompressionType.BLOCK);

        SequenceFileOutputFormat.setOutputPath(job, getOutputPath());

        return job;
    }

    public Path getExtraInputPath() {
        return extraInputPath;
    }
}
