package joinfiles.map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Artsiom Badun.
 */
public class SourceFileMapper extends Mapper<LongWritable,Text,Text,Text> {
    Text keyEmit = new Text();
    Text valEmit = new Text();

    public void map(LongWritable k, Text value, Context context) throws IOException, InterruptedException {
        String line=value.toString();
        String[] words=line.split(" ");
        keyEmit.set(words[0]);
        valEmit.set(words[1]);
        context.write(keyEmit, valEmit);
    }
}
