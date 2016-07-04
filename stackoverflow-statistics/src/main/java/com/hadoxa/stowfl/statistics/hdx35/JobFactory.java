package com.hadoxa.stowfl.statistics.hdx35;

import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * Created by badun on 5/22/16.
 */
public interface JobFactory {

    Job getJob() throws IOException;

}
