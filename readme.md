# 基于Hive的用户行为分析系统
## 启动服务所需组件
服务器 + hadoop 3.1.3 hive 3.1.2 mysql8.0+ tmux
启动步骤：
1.启动前关闭防火墙 systemctl stop firewalld 或者 service firewalld stop  记住要打开安全组
2. 启动hadoop服务 去 hadoop目录下 ./sbin/start-all.sh
3. 启动hive服务 去hive目录下  ./bin/hiveserver2
4. 写接口测试就行了
## 项目介绍及构成
> 项目主要由 项目上传 数据的预处理 数据的处理 数据的显示 四部分组成
### 项目上传
基于HDFS的上传，实现对数据的显示，上传，删除，以及创建，本部分可视化上传

### 数据的预处理
由于CSV中数据里存在很大的问题，比如数据中有空行或者是回车，导致MapReduce处理时不能处理一行，因此这里使用了用Python处理数据，然后再用MapReduce进行处理

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
将处理完的JSON数据进行回显，显示到数据可视化大屏上





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



