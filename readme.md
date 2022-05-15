# 基于Hive的用户行为分析系统
## ⚡核心技术栈

- ☕**Java-1.8** :最受欢迎的一个版本。
- 🦓**Hive-3.1** ：  在3.x之前也支持`update`,但是速度太慢，还需要进行分桶，现在`Hive` 支持全新`ACID`，并且底层采用`TEZ引擎` 和内存进行查询，比`Hive-2.*`提高2-50倍。
  `Apache Tez`将`MapReduce`替换为默认的`Hive`执行引擎。不再支持`MapReduce`，并证明了`Tez`的稳定性。通过有向无环图（DAG）和数据传输原语的表达式，在`Tez`下执行`Hive`查询可以提高性能。 
- 🐘**Hadoop-3.2.0**: `Hadoop3.x`以后将会调整方案架构，将`Mapreduce` 基于内存+io+磁盘，共同处理数据。
  其实最大改变的是`HDFS`,`HDFS`通过最近`black`块计算，根据最近计算原则，本地`black`块，加入到内存，先计算，通过IO，共享内存计算区域，最后快速形成计算结果。 
- ☕🔗🦓**HiveJDBC:** 通过`Java`直接访问`Hive`数据库 
- 🐘🔗🦓**HiveServer2**:使远程客户端可以执行对`Hive`的查询并返回结果 
- ...
hadoop jar mr-project-1.0-SNAPSHOT.jar com.nuc.MRDriver /opt/software/学生考试信息-处理.csv /opt/moudle/hadoop/output/exam


## 启动服务所需组件
服务器 + hadoop 3.1.3 hive 3.1.2 mysql8.0+ tmux
启动步骤：
1. 启动前关闭防火墙 systemctl stop firewalld 或者 service firewalld stop  记住要打开安全组
2. 启动hadoop服务 去 hadoop目录下 ./sbin/start-all.sh
3. 启动hive服务 去hive目录下  ./bin/hiveserver2  查看10000端口： sudo lsof -nP -iTCP:10000 -sTCP:LISTEN
4. 写接口测试就行了
## 项目介绍及构成
> 项目主要由 项目上传 数据的预处理 数据的处理 数据的显示 四部分组成
### 项目上传
基于HDFS的上传，实现对数据的显示，上传，删除，以及创建，本部分可视化上传

### 数据的预处理
由于CSV中数据里存在很大的问题，比如数据中有空行或者是回车，导致MapReduce处理时不能处理一行，因此这里使用了用Python处理数据，然后再用MapReduce进行处理

timetamp 转为 date ：select from_unixtime(unix_timestamp(dt),'yyyy-MM-dd') from test;
select from_unixtime(unix_timestamp(dt),'yyyy-MM-dd'),count(distinct from_unixtime(unix_timestamp(dt),'yyyy-MM-dd')) from

select from_unixtime(unix_timestamp(ruleTime),'yyyy-MM-dd'),count(from_unixtime(unix_timestamp(ruleTime),'yyyy-MM-dd')) as count from daily group by ruleTime ;

### 数据的处理
使用HiveSQL进行处理，设计到比较复杂的SQL语句
```sql
创建数据库:
create databse nuc;
use nuc;

create table daily(
    id string,
    studentNo int,
    classNo int,
    ruleTime timestamp,
    motto string,
    workContent string,
    completion double,
    note string,
    addTime timestamp,
    isDaily int                                                        
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY  ','
STORED AS TEXTFILE;
create table exam(
    paperNo string,
    studentNo int,
    subjectiveScore double,
    objectiveScore double,
    score double,
    isRead int,
    isSubmit int,
    isEnter int,
    subjectiveDetails string,
    objectiveDetails string
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY  ','
STORED AS TEXTFILE;

加载hdfs数据进hive数据库：
load data inpath '/opt/moudle/hadoop/output/daily/part-r-00000' into table daily;
load data inpath '/opt/moudle/hadoop/output/exam/part-r-00000' into table exam;
查看表结构：
desc daily;
表的详细属性：
desc formatted daily
查看表所在路径：
describe extended daily
查看部分内容：
select * from daily limit 10;
select * from exam limit 10;
删除表：
drop table daily;



```

### 数据的显示
```sql
显示要求：系统主要实现通过对优逸客实训平台的用户访问数据进行统计分析，
实现统计分析每日访问量、
INSERT INTO TABLE  Time_count 
select from_unixtime(unix_timestamp(ruleTime),'yyyy-MM-dd'),count(from_unixtime(unix_timestamp(ruleTime),'yyyy-MM-dd')) as count from daily group by ruleTime ;
日报功能的每日高峰期
create table case2(hour int, count int)STORED AS TEXTFILE;
insert into table case2 select from_unixtime(unix_timestamp(addTime),'HH'),count(from_unixtime(unix_timestamp(addTime),'HH')) as count from daily group by addTime ;
create table case22(hour int, count int)STORED AS TEXTFILE;
insert into table case22 select hour, count(count) from case2 group by id ;
select hour,count from case22 where count = (select max(count) from case22);
日报每日提交数量、
create table case2(hour int, count int)STORED AS TEXTFILE;
insert into table case2 select from_unixtime(unix_timestamp(addTime),'HH'),count(from_unixtime(unix_timestamp(addTime),'HH')) as count from daily group by addTime ;
select hour, count(count) from case2 group by hour;
学员考试平均时长、

学员考试高峰期等功能
周报与日报比例：
select count(isdaily) from daily where isdaily = 0;
select count(isdaily) from daily where isdaily = 1;
select count(isdaily) from exam 
完成率为100最多的五位学生：
 select studentno ,count(completion) as cnt from daily where completion = 100 group by studentno order by cnt desc limit 5;
提交日报次数最多的班级：
select classno ,count(classno) as cnt from daily where isdaily = 0 group by classno order by cnt desc limit 5;
提交周报次数最多的班级：
select classno ,count(classno) as cnt from daily where isdaily = 1 group by classno order by cnt desc limit 5;
格言精炼的五位同学的格言：
 select studentno ,motto from daily where length(motto) = 5 limit 5;


参加考试与未参加考试比例
select count(issubmit) from exam where issubmit = 0;
select count(issubmit) from exam where issubmit = 1;
select count(issubmit) from exam 


（可在完成基础功能上自由扩展新功能），根据以上功能实现相关数据可视化展示。
将处理完的JSON数据进行回显，显示到数据可视化大屏上

主观成绩前五：select studentno from exam order by subjectivescore desc limit 5;
客观成绩前五：select studentno from exam order by objectivescore desc limit 5;
总成绩前五：select studentno from exam order by score desc limit 5;
参加考试次数最多的五位：
select studentno ,count(studentno) as cnt from exam where issubmit != 0 group by studentno order by cnt desc limit 5;
参加考试人最多的前五场考试：
select paperno ,count(paperno) as cnt from exam group by paperno order by cnt desc limit 5;

```

## 核心代码展示：
hive-site.xml
```xml
  1 <?xml version="1.0" encoding="UTF-8" standalone="no"?>
  2 <?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
  3 <configuration>
  4   <property>
  5     <name>javax.jdo.option.ConnectionURL</name>
  6     <value>jdbc:mysql://localhost:3306/hive?createDatabaseIfNotExist=true</value>
  7     <description>JDBC connect string for a JDBC metastore</description>
  8   </property>
  9   <property>
 10     <name>javax.jdo.option.ConnectionDriverName</name>
 11     <value>com.mysql.cj.jdbc.Driver</value>
 12     <description>Driver class name for a JDBC metastore</description>
 13   </property>
 14   <property>
 15     <name>javax.jdo.option.ConnectionUserName</name>
 16     <value>hive</value>
 17     <description>username to use against metastore database</description>
 18   </property>
 19   <property>
 20     <name>javax.jdo.option.ConnectionPassword</name>
 21     <value>123456</value>
 22     <description>password to use against metastore database</description>
 23   </property>
 24 </configuration>

```
core-site.xml
```xml

<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<!--
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. See accompanying LICENSE file.
-->

<!-- Put site-specific property overrides in this file. -->
<configuration>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>file:/opt/moudle/hadoop-3.1.3/tmp</value>
        <description>Abase for other temporary directories.</description>
    </property>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://hadoop-master:9000</value>
    </property>
    <property>
         <name>dfs.permissions</name>
         <value>false</value>
     </property>

     <property>
         <name>hadoop.proxyuser.root.hosts</name>
         <value>*</value>
     </property>
     <property>
         <name>hadoop.proxyuser.root.groups</name>
         <value>*</value>
     </property>

</configuration>

```
hdfs-site.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<!--
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. See accompanying LICENSE file.
-->

<!-- Put site-specific property overrides in this file. -->
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
    <property>
        <name>dfs.namenode.name.dir</name>
        <value>file:/opt/moudle/hadoop-3.1.3/tmp/dfs/name</value>
    </property>
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>file:/opt/moudle/hadoop-3.1.3/tmp/dfs/data</value>
    </property>

    <property>
        <name>dfs.webhdfs.enabled</name>
        <value>true</value>
    </property>
----
</configuration>

```
yarn-site.xml
```xml
<?xml version="1.0"?>
<!--
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. See accompanying LICENSE file.
-->
<configuration>

<!-- Site specific YARN configuration properties -->
    <property>
        <name>mapred.job.tracker</name>
        <value>localhost:9001</value>
    </property>
</configuration>

```

## 遇到的BUG
1. 遇到Hive JDBC:java.lang.RuntimeException: org.apache.hadoop.ipc.RemoteException(org.apache.hadoop.securi...
解决方案：如上代码：core-site.xml与hdfs-site中添加部分代码即可



