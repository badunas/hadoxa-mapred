package com.hadoxa.stowfl.statistics.hdx35;

import com.hadoxa.stowfl.statistics.hdx35.step1_comments.CommentStatJobFactory;
import com.hadoxa.stowfl.statistics.hdx35.step2_posttags.PostTagsJobFactory;
import com.hadoxa.stowfl.statistics.hdx35.step3_tagcommentjoin.TagCommentJoinJobFactory;
import com.hadoxa.stowfl.statistics.hdx35.step4_finalstat.FinalStatJobFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Arrays;

/**
 * Created by badun on 5/22/16.
 */
public class JobsRunner extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new JobsRunner(), args);
        System.exit(exitCode);
    }

    public int run(String[] args) throws Exception {
        Configuration conf = getConf();

        String[] files = new GenericOptionsParser(conf, args).getRemainingArgs();
        String postsIn = files[0];
        String commentsIn = files[1];
        String output = files[2];

        // Paths.
        Path step1_inPath = new Path(commentsIn);
        Path step1_outPath = new Path(commentsIn + "/tmp/out/");
        Path step2_inPath = new Path(postsIn);
        Path step2_outPath = new Path(postsIn + "/tmp/out/");;
        Path step3_inPath = step1_outPath;
        Path step3_extraInPath = step2_outPath;
        Path step3_outPath = new Path(commentsIn + "/final/out/");
        Path step4_inPath = step3_outPath;
        Path step4_outPath = new Path(output);

        // Factories.
        JobFactory step1_jobFactory = new CommentStatJobFactory(conf, step1_inPath, step1_outPath);
        JobFactory step2_jobFactory = new PostTagsJobFactory(conf, step2_inPath, step2_outPath);
        JobFactory step3_jobFactory = new TagCommentJoinJobFactory(conf, step3_inPath, step3_extraInPath, step3_outPath);
        JobFactory step4_jobFactory = new FinalStatJobFactory(conf, step4_inPath, step4_outPath);

        JobControl control = new JobControl("HDX-35");

        ControlledJob step_1 = new ControlledJob(step1_jobFactory.getJob(), null);
        ControlledJob step_2 = new ControlledJob(step2_jobFactory.getJob(), null);
        ControlledJob step_3 = new ControlledJob(step3_jobFactory.getJob(), Arrays.asList(step_1, step_2));
        ControlledJob step_4 = new ControlledJob(step4_jobFactory.getJob(), Arrays.asList(step_3));

        control.addJob(step_1);
        control.addJob(step_2);
        control.addJob(step_3);
        control.addJob(step_4);

        Thread workflowThread = new Thread(control, "HDX-35-Thread");
        workflowThread.setDaemon(true);
        workflowThread.start();

        while (!control.allFinished()){
            Thread.sleep(500);
        }

        return 0;
    }
}
