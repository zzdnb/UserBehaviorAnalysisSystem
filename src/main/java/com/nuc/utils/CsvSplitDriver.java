package com.nuc.utils;
import com.nuc.entity.Daily;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CsvSplitDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {

        //1 获取job对象?
        Configuration conf = new Configuration();
        conf.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("hadoop.home.dir", "/opt/moudle/hadoop/");
        conf.set("fs.defaultFS","hdfs://180.76.243.209:9000");
        Job job = Job.getInstance(conf);

        //2 关联Driver类
        job.setJarByClass(CsvSplitDriver.class);

        //3 关联Mapper和Reducer类
        job.setMapperClass(CsvSplitMapper.class);
        job.setReducerClass(CsvSplitReducer.class);

        //4 设置Map的输入输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Daily.class);

        //5	设置最终的输入输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Daily.class);

        //6	设置输入输出路径
            //输入路径可以是本机也可以是hdfs


        FileInputFormat.setInputPaths(job, new Path[]{new Path("hdfs://180.76.243.209:9000/opt/moudle/hadoop/input/学生考试信息.csv")});
        FileSystem fs = FileSystem.get(new URI("hdfs://180.76.243.209:9000"), conf, "root");
       //输出路径 一定要加上hdfs://192.168.41.128:9000
        // 输出路径要hdfs中的路径。

        Path pathout = new Path("hdfs://180.76.243.209:9000/opt/moudle/hadoop/output/a");
//        if (fs.exists(pathout)) {
//            fs.delete(pathout, true);
//        }

        FileOutputFormat.setOutputPath(job, pathout);
        //7 提交job
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
