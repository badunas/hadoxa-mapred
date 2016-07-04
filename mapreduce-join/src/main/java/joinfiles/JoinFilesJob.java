package joinfiles;

import joinfiles.map.JoinFileMapper;
import joinfiles.map.SourceFileMapper;
import joinfiles.reduce.MultipleReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by Artsiom Badun.
 */
public class JoinFilesJob extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        if (args.length != 3 ){
            System.err.println ("Usage :<inputlocation1> <inputlocation2> <outputlocation>");
            System.exit(0);
        }
        int res = ToolRunner.run(new Configuration(), new JoinFilesJob(), args);
        System.exit(res);
    }

    public int run(String[] args) throws Exception {
        Configuration c = new Configuration();

        String[] files = new GenericOptionsParser(c, args).getRemainingArgs();
        Path p1=new Path(files[0]);
        Path p2=new Path(files[1]);
        Path p3=new Path(files[2]);

        FileSystem fs = FileSystem.get(c);
        if(fs.exists(p3)){
            fs.delete(p3, true);
        }

        Job job = Job.getInstance(c,"Multiple Job");
        job.setJarByClass(JoinFilesJob.class);
        MultipleInputs.addInputPath(job, p1, TextInputFormat.class, SourceFileMapper.class);
        MultipleInputs.addInputPath(job,p2, TextInputFormat.class, JoinFileMapper.class);
        job.setReducerClass(MultipleReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileOutputFormat.setOutputPath(job, p3);

        boolean success = job.waitForCompletion(true);

        return success ? 0 : 1;
    }
}
