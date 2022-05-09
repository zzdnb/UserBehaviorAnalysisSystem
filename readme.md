# 项目介绍及构成
> 项目主要由 项目上传 数据的预处理 数据的处理 数据的显示 四部分组成
## 项目上传
基于HDFS的上传，实现对数据的显示，上传，删除，以及创建，本部分可视化上传

## 数据的预处理
由于CSV中数据里存在很大的问题，比如数据中有空行或者是回车，导致MapReduce处理时不能处理一行，因此这里使用了用Python处理数据，然后再用MapReduce进行处理

##数据的处理
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

## 数据的显示
将处理完的JSON数据进行回显，显示到数据可视化大屏上





