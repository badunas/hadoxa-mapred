package marksarests;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by Artsiom Badun.
 */
public class MarksArrestsJoinJob {
    private final Configuration conf;
    private final Path[] inputPaths;
    private final Path outputPath;

    public MarksArrestsJoinJob(Configuration conf, Path[] inputPaths, Path outputPath) {
        this.conf = conf;
        this.inputPaths = inputPaths;
        this.outputPath = outputPath;
    }

    public Job getJob() throws IOException {
        Job job = Job.getInstance(conf, "MarksArrestsJoinJob");
        job.setJarByClass(getClass());

        // configure input source
        MultipleInputs.addInputPath(job, inputPaths[0], SequenceFileInputFormat.class, JobMapper.class);
        MultipleInputs.addInputPath(job, inputPaths[1], SequenceFileInputFormat.class, JobMapper.class);

        // configure mapper and reducer
        job.setMapperClass(JobMapper.class);
        job.setReducerClass(JobReducer.class);

        // configure output
        TextOutputFormat.setOutputPath(job, outputPath);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        return job;
    }

    public static class JobMapper extends Mapper<Text,Text,Text,Text> {

        @Override
        protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            super.map(key, value, context);
        }
    }

    public static class JobCombiner extends Reducer<Text,Text,Text,Text> {

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            super.reduce(key, values, context);
        }
    }

    public static class JobReducer extends Reducer<Text,Text,Text,Text> {

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            String avgMark = null;
            String arrestNum = null;
            for (Text array : values) {
                String[] ints = array.toString().split(" ");
                if ("0".equals(ints[0])) {
                    avgMark = ints[1];
                } else {
                    arrestNum = ints[1];
                }
            }
            String result = "avg mark = " + avgMark + ", arrest num = " + arrestNum;
            context.write(key, new Text(result));
        }
    }
}
