# Apache Drill 性能测试



## 测试目标

测试一定量数据时的查询性能，在不同场景下可以达到查询延时与基准延时总吞吐量，并与OLDP的数据库进行比较，分析Drill与OLDP数据库的各场景的优劣性。

 

## Drill用途

通过Drill提供一个统一的数据查询接口，使用SQL便可以聚合不同种类存储系统及不同种类的数据格式。减少在超大规模数据存储系统对接的复杂度。

例如部份数据是CSV文件存在操作系统的原始文件系统，部份数据是存储在HDFS，那么可以通过drill便可以通过SQL直接两个部份的数据进行关联查询。



## 测试计划

1. Select-count

   测试计数性能，可能涉及全文件扫描情况

2. Where-and

   测试条件语句，查看多条件下性能

3. Where-range

   测试范转条件下性能情况

4. Groupby

   测试分组情况

5. OrderBy

   测试排序情况

6. Join

   测试两表关联查询情况

7. Join-Index

   测试带索引下的查询情况，查看OLDP数据下带索引下的性能提升

8. Sub-sql

   测试子查询性能情况



## 测试结果

### 单条运行

| 测试项目         | Drill - Parquet | Mysql InnoDB | 差时 |
| ---------------- | --------------- | ------------ | ---- |
| Select-count     |                 |              |      |
| Where-and        |                 |              |      |
| Where-range      |                 |              |      |
| Where-rang-index |                 |              |      |
| Groupby          |                 |              |      |
| Orderby          |                 |              |      |
| Join             |                 |              |      |
| Join-Index       |                 |              |      |
| Sub-sql          |                 |              |      |

### 并行运行

<未更新>



## 测试总结



## 执行准备

### 测试环境

CPU: 8Core 4.Ghz; 内存:64G; 存储:1T ssd

操作系统: Linux  5.13.0-35

数据库: Mysql 5.6.x

文件系统: Ext4



### 目标数据

数据文件：`mvd.csv`

原始数据结构：CSV

文件大小：30G

列数大小:  6列

行数大小：500，000，000

遍历文件时长：56s

数据样例：

> Title,Genre,Premiere,Runtime,IMDB Score,Language
> Option Week Mind,Horror,"October 05, 2021",139,1.7,Spanish
> For Edge Even,Comedy,"December 15, 2021",97,4.3,Italian
> Us Two Sister,Romance,"May 12, 2020",102,1.6,English
> Claim Happen Time,Horror,"October 17, 2020",138,3.3,English
> Indeed Person Big,Mystery,"October 25, 2020",67,3.0,Hindi
> Page Miss Woman,Romance,"June 19, 2021",114,2.9,Chinese
> Like First Safe,Thriller,"November 29, 2021",129,1.9,Japanese
> Figure Kitchen She,Drama,"March 28, 2020",115,2.9,Spanish
> Ok Nation Doctor,Mystery,"July 18, 2020",55,2.4,Spanish



### 关联数据

用于关联测试使用

原始数据结构：`CSV`



### 转化parquet

将CSV转成parquet文件格式

转换命令如下：

```shell
python3 tr_csv_parquet.py data/movie/mvd2.csv mvd.parquet
```



### 导入数据

导入命令如下：

```mysql
load data local infile '/home/mmxx/work/drill/data/movie/mvd2.csv' into table big_table character set utf8 fields TERMINATED BY ',' LINES TERMINATED BY '\r\n' ignore 1 lines;
```



## 测试过程

### Select-Count

#### 执行SQL：

```sql
select count(1) from big_data;
```

#### Drill执行:

​	执行结果：

​	执行时间:

#### OLDP执行：

​	执行结果：

​	执行时间:



### Where-and

#### 执行SQL：

```sql
select count(1) from big_data where Title like '%Person%';
```

#### Drill执行:

​	执行结果：

​	执行时间:

#### OLDP执行：

​	执行结果：

​	执行时间:



### Where-range

#### 执行SQL：

```sql
select count(1) from big_data where IMDBScore between 10 and 50;
```

#### Drill执行:

​	执行结果：

​	执行时间:

#### OLDP执行：

​	执行结果：

​	执行时间:



### GroupBy

#### 执行SQL：

```sql
select Genre,count(1) from big_data where IMDBScore between 10 and 50 group by Genre;
```

#### Drill执行:

​	执行结果：

​	执行时间:

#### OLDP执行：

​	执行结果：

​	执行时间:



### OrderBy

执行SQL：

```sql
select * from big_data where IMDBScore between 10 and 50 Order by Title limit 10;
```

#### Drill执行:

​	执行结果：

​	执行时间:

#### OLDP执行：

​	执行结果：

​	执行时间:



### Join

#### 执行SQL：

```sql
select bd.*,gen.* from big_data bd, genre_info gen 
where bd.Genre=gen.name and bd.IMDBScore between 10 and 50 
Order by db.Title
limit 10;
```

#### Drill执行:

​	执行结果：

​	执行时间:

#### OLDP执行：

​	执行结果：

​	执行时间:

#### OLDP(带索引)执行：

​	执行结果：

​	执行时间:



### Sub-SQL

#### 执行SQL：

```sql
select * from big_data where IMDBScore between 10 and 50 
and Genre in (select name from genre_info where id between 10 and 50  )
Order by Title limit 10;
```

#### Drill执行:

​	执行结果：

​	执行时间:

#### OLDP执行：

​	执行结果：

​	执行时间:
