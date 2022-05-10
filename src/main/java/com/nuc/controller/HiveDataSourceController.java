package com.nuc.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 福尔摩东
 * @date 2022/5/10 11:40
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */


/**
 * 使用 DataSource 操作 Hive
 */
@RestController
public class HiveDataSourceController {

    private static final Logger logger = LoggerFactory.getLogger(HiveDataSourceController.class);

    @Autowired
    @Qualifier("hiveDruidDataSource")
    DataSource druidDataSource;

    /**
     * 测试spring boot是否正常启动
     */
    @RequestMapping("/")
    public String hello() {
        return "hello world";
    }

    /**
     * 列举当前Hive库中的所有数据表
     */
    @RequestMapping("/table/list")
    public List<String> listAllTables() throws SQLException {
        List<String> list = new ArrayList<String>();
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "show tables";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        while (res.next()) {
            list.add(res.getString(1));
        }
        return list;
    }

    /**
     * 查询指定tableName表中的数据
     */
    @RequestMapping("/table/select")
    public List<String> selectFromTable(String tableName) throws SQLException {
        List<String> list = new ArrayList<String>();
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select * from " + tableName;
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        int count = res.getMetaData().getColumnCount();
        String str = null;
        while (res.next()) {
            str = "";
            for (int i = 1; i < count; i++) {
                str += res.getString(i) + " ";
            }
            str += res.getString(count);
            logger.info(str);
            list.add(str);
        }
        return list;
    }

}
