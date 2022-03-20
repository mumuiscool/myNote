# Apache Drill 性能测试



## 测试目标

测试一定量数据时的查询性能，在不同场景下可以达到查询延时与基准延时总吞吐量，并与OLDP的数据库进行比较，分析Drill与OLDP数据库的各场景的优劣性。

 

## Drill用途

通过Drill提供一个统一的数据查询接口，使用SQL便可以聚合不同种类存储系统及不同种类的数据格式。减少在超大规模数据存储系统对接的复杂度。

例如部份数据是CSV文件存在操作系统的原始文件系统，部份数据是存储在HDFS，那么可以通过drill便可以通过SQL直接两个部份的数据进行关联查询。



## 测试计划

1. Select-count

   测试计数性能

2. Where-and

3. Where-range

4. Groupby

5. OrderBy

6. Join

7. Join-Index

8. Sub-sql



## 测试结果

### 单条运行

| 测试项目         | 执行说明 | Drill - Parquet | Mysql InnoDB | 差时 |
| ---------------- | -------- | --------------- | ------------ | ---- |
| Select-count     |          |                 |              |      |
| Where-and        |          |                 |              |      |
| Where-range      |          |                 |              |      |
| Where-rang-index |          |                 |              |      |
| Groupby          |          |                 |              |      |
| Orderby          |          |                 |              |      |
| Join             |          |                 |              |      |
| Join-Index       |          |                 |              |      |
| Sub-sql          |          |                 |              |      |

### 并行运行

<格式同上...>



## 测试总结



## 执行准备

### 测试环境

CPU 内存 存储

操作系统

数据库

文件系统



### 目标数据

数据文件：raven.csv

原始数据结构：CSV

文件大小：25G

列数大小: x列

行数大小：100，000，000

遍历文件时长：56s

### 关联数据

用于关联测试使用

原始数据结构：CSV



### 转化parquet

将CSV转成parquet文件格式

转换命令如下：

xxxxxxxxxx



### 导入数据

导入格式如下



## 测试方式

### Select-Count

#### Drill执行

执行SQL：



执行结果：



执行时间:

