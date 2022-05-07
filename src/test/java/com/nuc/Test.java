package com.nuc;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 福尔摩东
 * @date 2022/5/3 12:59
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */

import java.io.IOException;

import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * Hello world!
 */
public class Test {
    public static void main(String[] args) throws Exception, IOException {
        upload();
    }

    public static void upload() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();

        java.net.URI uri = new URI("hdfs://180.76.243.209:9000"); // core-site.html 中配置的端口
        FileSystem fs = FileSystem.get(uri, configuration, "root");

        Path src = new Path("d://aa.txt");
        Path dest = new Path("/opt/moudle/hadoop/input");
        fs.copyFromLocalFile(src, dest);
        FileStatus[] fileStatus = fs.listStatus(dest);
        for (FileStatus file : fileStatus) {
            System.out.println(file.getPath());
        }
        System.out.println("上传成功");
    }

    public static void test()throws Exception{
        Socket socket=new Socket("180.76.243.209",9000);
        System.out.println(socket);//查看网络是否相同，拒绝说明防火墙开了，外界无法访问到网络。关一下就好了
    }

}
