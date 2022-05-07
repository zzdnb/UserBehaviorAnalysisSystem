package com.nuc.utils;

import com.nuc.entity.Daily;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CsvSplitReducer extends Reducer<Text, Daily, Text,Daily> {

    @Override
    protected void reduce(Text key, Iterable<Daily> values, Context context) throws IOException, InterruptedException {

        for (Daily value : values) {
            context.write(key,value);
        }
        System.out.println(1);
    }
}
