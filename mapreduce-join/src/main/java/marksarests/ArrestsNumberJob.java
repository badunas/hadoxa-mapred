package marksarests;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

/**
 * Created by Artsiom Badun.
 */
public class ArrestsNumberJob {
    private final Configuration conf;
    private final Path inputPath;

    private Path tmpPath;

    public ArrestsNumberJob(Configuration conf, Path inputPath) {
        this.conf = conf;
        this.inputPath = inputPath;
    }

    public Job getJob() throws IOException {
        Job job = Job.getInstance(conf, "ArrestsNumberJob");
        job.setJarByClass(getClass());

        // configure input source
        TextInputFormat.addInputPath(job, inputPath);
        job.setInputFormatClass(TextInputFormat.class);

        // configure mapper and reducer
        job.setMapperClass(JobMapper.class);
        job.setReducerClass(JobReducer.class);

        // configure output
        SequenceFileOutputFormat.setOutputPath(job, getTmpDirPath());
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        return job;
    }

    public Path getTmpDirPath() throws IOException {
        if (tmpPath == null) {
            FileSystem fs = FileSystem.get(conf);
            String tmpDir = "/tmp/" + getClass().getSimpleName() + "-arrests-tmp";
            tmpPath = new Path(tmpDir);
//            fs.deleteOnExit(tmpPath);
        }
        return tmpPath;
    }

    public static class JobMapper extends Mapper<LongWritable,Text,Text,Text> {

        public void map(LongWritable k, Text value, Context context) throws IOException, InterruptedException {
            String line=value.toString();
            String[] words=line.split(" ");
            context.write(new Text(words[0]), new Text("1"));
        }
    }

    public static class JobCombiner extends Reducer<Text,Text,Text,Text> {

        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for(Text value : values) {
                sum ++;
            }
            context.write(key, new Text(String.valueOf(sum)));
        }
    }

    public static class JobReducer extends Reducer<Text,Text,Text,Text> {

        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for(Text value : values) {
                sum ++;
            }
            context.write(key, new Text("1 " + sum));
        }
    }
}
