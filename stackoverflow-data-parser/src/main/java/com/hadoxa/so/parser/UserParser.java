package com.hadoxa.so.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hadoxa.so.parser.common.XmlInputFormat;
import com.hadoxa.so.pojo.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class UserParser extends Mapper<LongWritable, Text, Text, NullWritable> {

    private static final String START_TAG_KEY = "<row";
    private static final String END_TAG_KEY = "/>";

    private static final Logger log = LoggerFactory.getLogger(UserParser.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
        Configuration conf = new Configuration();
        conf.set(XmlInputFormat.START_TAG_KEY, START_TAG_KEY);
        conf.set(XmlInputFormat.END_TAG_KEY, END_TAG_KEY);

        Job job = Job.getInstance(conf, "stackoverflow user data parser");
        job.setJarByClass(UserParser.class);
        job.setMapperClass(UserParser.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.setInputFormatClass(XmlInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setNumReduceTasks(0);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String e = value.toString();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc =  builder.parse(new InputSource(new StringReader(e)));

            Element rootElem = doc.getDocumentElement();

            User user = new User(rootElem.getAttribute("Id"),
                    rootElem.getAttribute("Reputation"),
                    rootElem.getAttribute("CreationDate"),
                    rootElem.getAttribute("DisplayName"),
                    rootElem.getAttribute("EmailHash"),
                    rootElem.getAttribute("LastAccessDate"),
                    rootElem.getAttribute("WebsiteUrl"),
                    rootElem.getAttribute("Location"),
                    rootElem.getAttribute("AboutMe").replaceAll("\\r?\\n", ""),
                    rootElem.getAttribute("Age"),
                    rootElem.getAttribute("Views"),
                    rootElem.getAttribute("UpVotes"),
                    rootElem.getAttribute("DownVotes"));
            ObjectMapper mapper = new ObjectMapper();
            String userJsonString = mapper.writeValueAsString(user);

            Text outPutKey = new Text();
            outPutKey.set(userJsonString);

            context.write(outPutKey, NullWritable.get());
        } catch (ParserConfigurationException | SAXException ex) {
            log.warn("Error while parsing user record", ex);
            context.getCounter("Corrupted Record Counter", "Unparsable record").increment(1L);
        }

    }
}
