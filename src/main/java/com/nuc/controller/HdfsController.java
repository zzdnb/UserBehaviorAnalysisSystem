package com.nuc.controller;




import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;

/**
 * @author 福尔摩东
 * @date 2022/5/1 19:21
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */
@CrossOrigin
@RestController
@RequestMapping("/hdfs")
@Slf4j
public class HdfsController {

    @RequestMapping
    Object hello() {
        return "hello java web";
    }



    // 上传文件 文件名称若已存在问题未解决
    @RequestMapping("/uploadFile")
    Object uploadFile(@RequestParam("file") MultipartFile file,
                      @RequestParam("dstString") String dstString) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        java.net.URI uri = new URI("hdfs://180.76.243.209:9000"); // core-site.html 中配置的端口
        FileSystem fs = FileSystem.get(uri, configuration, "root");
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        String tempFolder = "/opt/moudle/hadoop/input";
        Path dst = new Path(tempFolder+"/"+fileName);
        Path src = new Path(dstString);

//
        fs.copyFromLocalFile(src, dst);
        fs.close();

        return "Upload file successfully";
    }

    // 下载文件hdfs://127.0.0.1:8020
    @RequestMapping("/downloadFile")
    Object downloadFile(String srcString, String dstString) {
        try {
            Configuration configuration = new Configuration();
            configuration.set("dfs.client.use.datanode.hostname", "true");

            java.net.URI uri = new URI("hdfs://180.76.243.209:9000"); // core-site.html 中配置的端口
            FileSystem fs = FileSystem.get(uri, configuration, "root");

            Path src = new Path((srcString));
            Path dst = new Path((dstString));

            fs.copyToLocalFile(true,dst, src,true);
            fs.close();
        }catch (Exception e){
            e.getMessage();
        }finally {
            return "Download file successfully";
        }



    }

    // 列出该目录下所有文件
    @RequestMapping("/listFile")
    Object listFile(String folder) throws Exception {
        System.out.println(folder);
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        java.net.URI uri = new URI("hdfs://180.76.243.209:9000"); // core-site.html 中配置的端口
        FileSystem fs = FileSystem.get(uri, configuration, "root");

        FileStatus[] statuses = fs.listStatus(new Path(folder));

        List<HashMap<String, String>> table = new ArrayList<HashMap<String, String>>();
        log.info(folder);
        for (FileStatus file : statuses) {
            HashMap<String, String> map = new HashMap<String, String>();
            String isDir = file.isDirectory() ? "Folder" : "File";
            String size = String.valueOf(file.getLen());
            String name = file.getPath().getName();
            map.put("isDir", isDir);
            map.put("size", size);
            map.put("name", name);
            table.add(map);
        }
        return table;
    }

    // 删除文件
    @RequestMapping("/removeFile")
    Object removeFile(String path) throws Exception {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        java.net.URI uri = new URI("hdfs://180.76.243.209:9000"); // core-site.html 中配置的端口
        FileSystem fs = FileSystem.get(uri, configuration, "root");

        fs.delete(new Path((path)), true);
        fs.close();

        return "Remove folder/file successfully - " + path;
    }


}
