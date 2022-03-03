# QuickFileSQL



## Description

A data query platform with querying by SQL that support multiple data formats in the file system.



## Feature

Stores data on file system of the operated system.

Specify the data storage location without specifying the data import programs.  Allows any program to store data store data on the specifying the file path.

Supports multiple data forms. Parquet、Arrow file is supported for the first step, and more formats willed be added later such as CSV、Excel etc.

 Provides SQL query, the first step to realize SELECT,GROUP-BY and then add JOIN-query, window function. Insert/Update function is not currently planned.

Provides a JDBC and RESTFul interface.



## Investigation

Collect some components that support it.

Practice and evaluate the components.



## Scheme Evaluation

There are now three schemes and all schemes are being evaluated.

#### Scheme A 

Use the Calcite to parse SQL and implement query code that read and parse the queryed-file .

#### Scheme B

Use the Druid to parse SQL and implement query code that read and parse the queryed-file .

#### Scheme C 

Use the H2 database CSV function.

#### Scheme D

Use the  Apache Drill to directly implement querying by SQL on the parquert file and supporting other file queryed by the Drill plugin feature.



#### Analysis

|  Scheme  |        Advantage        |         Disadvantages         |
| :------: | :---------------------: | :---------------------------: |
| Scheme A | stronger to parsing SQL |        harder to used         |
| Scheme B |     easier to used      |     weaked to parsing SQL     |
| Scheme C |     faster to used.     | harder to develop based on H2 |
| Scheme D |     faster to used.     |            heavier            |

#### Recommendation

I recommend using Scheme D.

It will support many data formats and be used right now.



## Scheme A Implementation

1. Use the Apache Calcite for the SQL parsing-engine.

2. Use the Calcite's add-on for the JDBC driver.

3. Base the SpringMVC/Boot for and design a json type to package SQL query.

4. Configure the data directory or more by Spring-Properties such as `application.yml`.

   

## Scheme C Implementation

Install Apache Drill and test the query statement.

Base the SpringMVC/Boot for and design a json type to package SQL query.

Integrate this Drill  into QuickFileSQL for easier.



## Quick Start



### Step 01 - Configure the QuickFileSQL

```shell
export WORK_HOME=~/work

cd $WORK_HOME

git clone https://gitAddr/quickFileSQL/quickFileSQL

export PATH=$PATH:$WORK_HOME/quickFileSQL/bin
```



### Step 02 - Specify a data path and  Import the data

```shell
export QK=$WORK_HOME/qkData

mkdir $QK

cp foo.parquet $QK
```



### Step 03 - Start QuickFileSQL

```shell
qkfsql $QK &
```

if you see the logs like below:

```shell
start quick file sql success with the dir : ~/work/qkData
```



### Step 04 - Query the file by SQL

```shell
curl -XPOST -H "Content-Type: application/json" 
http://localhost:8080/query 
-d '{"sql" :"select * from foo"}'
```

and then will output info below:

```shell
field1,field2,field3

1,2,3

4,5,6
```



## Usage



### Usage 01 -  Select a table by RESTFul

Example file : `user.csv` 

Example data:

```
id,name,born,sex,email

1,Tom,19880102,male,tom@hsbc.com.hk

2,Kent,19930312,male,ken@hsbc.com.hk

3,Mary,19790130,female,mary@hsbc.com.hk

4,Handsome,19930812,male,handsome@hsbc.com.hk
```

Copy the `user.csv` to $QK

And we type  the SQL:

```sql
select id,name from user where id=3
```

And run query by the command:

```shell
curl -XPOST -H "Content-Type: application/json" 
http://localhost:8080/query 
-d '{"sql" :"select id,name from user where id=3"}'
```

it returned:

```json
{

	"querytime": "3ms",
	
	"cnt": 1,
	
	"data": [
	
		{
	
			"id": 3,
	
			"name": "Mary"
	
		}
	
	]

}
```

