package com.hadoxa.stowfl.statistics.hdx35;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * Created by badun on 5/22/16.
 */
public abstract class BaseJobFactory implements JobFactory {
    private Configuration conf;
    private Path inputPath;
    private Path outputPath;

    public BaseJobFactory(Configuration conf, Path inputPath, Path outputPath) throws IOException {
        this.conf = conf;
        this.inputPath = inputPath;
        this.outputPath = outputPath;

        FileSystem fs = FileSystem.get(conf);
        if(fs.exists(outputPath)){
            fs.delete(outputPath, true);
        }
    }

    public Configuration getConf() {
        return conf;
    }

    public Path getInputPath() {
        return inputPath;
    }

    public Path getOutputPath() {
        return outputPath;
    }
}
