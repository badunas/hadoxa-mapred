package joinfiles.reduce;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by Artsiom Badun.
 */
public class MultipleReducer extends Reducer<Text,Text,Text,Text> {
    Text valEmit = new Text();
    String merge = "";

    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        merge = "";
        for(Text value : values) {
            merge += value.toString() + " ";
        }
        valEmit.set(merge);
        context.write(key, valEmit);
    }
}
