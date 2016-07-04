package marksarests;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Arrays;

/**
 * Created by Artsiom Badun.
 */
public class MarksArrestsCorrelationJobRunner extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        if (args.length != 3){
            System.err.println ("Usage: <postslocation> <commentslocation> <outputlocation>");
            System.exit(0);
        }
        int exitCode = ToolRunner.run(new MarksArrestsCorrelationJobRunner(), args);
        System.exit(exitCode);
    }

    public int run(String[] args) throws Exception {
        Configuration conf = getConf();

        String[] files = new GenericOptionsParser(conf, args).getRemainingArgs();
        Path marksPath = new Path(files[0]);
        Path arrestsPath = new Path(files[1]);
        Path resultPath = new Path(files[2]);

        FileSystem fs = FileSystem.get(conf);
        if(fs.exists(resultPath)) {
            fs.delete(resultPath, true);
        }

        AverageMarkJob arrestsNumberJob = new AverageMarkJob(conf, marksPath);
        ArrestsNumberJob averageMarkJob = new ArrestsNumberJob(conf, arrestsPath);
        MarksArrestsJoinJob joinJob = new MarksArrestsJoinJob(
                conf,
                new Path[] {
                        averageMarkJob.getTmpDirPath(),
                        arrestsNumberJob.getTmpDirPath()
                },
                resultPath
        );

        try {
            JobControl control = new JobControl("Worklfow");

            ControlledJob step_1_1 = new ControlledJob(arrestsNumberJob.getJob(), null);
            ControlledJob step_1_2 = new ControlledJob(averageMarkJob.getJob(), null);
            ControlledJob step_2 = new ControlledJob(
                    joinJob.getJob(),
                    Arrays.asList(step_1_1, step_1_2)
            );

            control.addJob(step_1_1);
            control.addJob(step_1_2);
            control.addJob(step_2);

            Thread workflowThread = new Thread(control, "Workflow-Thread");
            workflowThread.setDaemon(true);
            workflowThread.start();

            while (!control.allFinished()){
                Thread.sleep(500);
            }

        } finally {
            fs.deleteOnExit(averageMarkJob.getTmpDirPath());
            fs.deleteOnExit(arrestsNumberJob.getTmpDirPath());
        }

        return 0;
    }
}
