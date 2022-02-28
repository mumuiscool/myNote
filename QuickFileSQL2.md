# QuickFileSQL

## Description

A data query platform with querying by SQL that supports multiple data formats.

## Features

1. Stores data base on operating system's filesystem.
2. Specify the data storage location without specifying the data source mode. Allows any program to store data on the specifying file path
3. Supports multiple data forms. CSV  file is supported for the first step, and more formats will be added later such as Parquet、Arrow、Excel.
4. Provide SQL query, the first step to realize select, group-by, and then add join-query, window function. Insert/Update function is not currently planned.
5. Provides a JDBC interface and Restful interface.

## Implementation

1. Use the Apache Calcite for the SQL parsing-engine.
2. Use the Calcite's add-on for the JDBC driver.
3. Base the SpringMVC/Boot for and design a json type to package SQL query.
4. Configure the data directory or more by Spring-Properties such as application.yml.

