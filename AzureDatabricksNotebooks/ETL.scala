// Databricks notebook source
// MAGIC %md
// MAGIC #ETL
// MAGIC 
// MAGIC ### ``(Extract Transform Load)``

// COMMAND ----------

// MAGIC %md
// MAGIC ##Run Configurations  
// MAGIC ##### The following command will run the Configurations Notebook.

// COMMAND ----------

// MAGIC %run ./Configurations

// COMMAND ----------

// MAGIC %md
// MAGIC ##Extract
// MAGIC #### Population data from the year 2010 to 2019 for all 50 states of the USA will be extracted from the csv file and saved into a DataFrame.
// MAGIC ##### What is a DataFrame?
// MAGIC * ##### A DataFrame is a data structure that organizes data into a 2-dimensional table of rows and columns, much like a spreadsheet. DataFrames are one of the most common data structures used in modern data analytics because they are a flexible and intuitive way of storing and working with data.
// MAGIC [Source: What is a DataFrame?](https://www.databricks.com/glossary/what-are-dataframes#:~:text=A%20DataFrame%20is%20a%20data,storing%20and%20working%20with%20data.)

// COMMAND ----------

// Read file and store it in the DataFrame 'statesDF'
val statesDF = spark.read.format("csv")
  .option("header","true") // use first line of file as header
  .option("encoding", "UTF-8")
  .option("sep",",")
  .load("/mnt/demo/censusdata.csv")

// Display the data stored in the statesDF DataFrame
statesDF.show()

// COMMAND ----------

// MAGIC %md
// MAGIC ##Transform
// MAGIC #### In this section data will be transformed. 
// MAGIC 1 - The first transformation will be to remove the columns Percent_Of_Us and Density  
// MAGIC 2 - The new transformed data will be saved into a new DataFrame called `transformedStatesDF`  
// MAGIC 3 - Register a temporary table called `statesTemp` with the `transformedStatesDF` DataFrame data  

// COMMAND ----------

// Remove Percent_Of_Us and Density columns and save to transformedStatesDF DataFrame
val transformedStatesDF = statesDF.select("state_id","state", "abbreviation", "capital", "Pop2010","Pop2011", "Pop2012", "Pop2013", "Pop2014", "Pop2015", "Pop2016", "Pop2017", "Pop2018", "Pop2019", "Fun_Fact")
transformedStatesDF.show()

// Register the temporary table
transformedStatesDF.registerTempTable("statesTemp")

// COMMAND ----------

// MAGIC %md
// MAGIC ##### Using the new created temporary table `statesTemp` data:
// MAGIC 4 - Create a select statement to get all fields from `statesTemp` and additionally calculate the population growth for each year for all states as well as the total population growth from 2010 to 2019. 

// COMMAND ----------

// Select all fields from statesTemp, calculate the population per year, calculate the total population growth from 2010-2019
val calculatedData = spark.sql("SELECT state_id, state, abbreviation, capital, pop2010, pop2011, pop2012, pop2013, pop2014, pop2015, pop2016, pop2017, pop2018, pop2019, ROUND(((pop2011 - pop2010)/pop2010)*100,2) AS growth_10_11, ROUND(((pop2012 - pop2011)/pop2011)*100,2) AS growth_11_12, ROUND(((pop2013 - pop2012)/pop2012)*100,2) AS growth_12_13, ROUND(((pop2014 - pop2013)/pop2013)*100,2) AS growth_13_14, ROUND(((pop2015 - pop2014)/pop2014)*100,2) AS growth_14_15, ROUND(((pop2016 - pop2015)/pop2015)*100,2) AS growth_15_16, ROUND(((pop2017 - pop2016)/pop2016)*100,2) AS growth_16_17, ROUND(((pop2018 - pop2017)/pop2017)*100,2) AS growth_17_18, ROUND(((pop2019 - pop2018)/pop2018)*100,2) AS growth_18_19, ROUND(((pop2019 - pop2010)/pop2010)*100,2) AS total_pop_growth, fun_fact FROM statesTemp")

// Display all necessary fields and the new calculated population
calculatedData.show()

// COMMAND ----------

// MAGIC %md
// MAGIC ##Load
// MAGIC #### In this section data will be loaded to the default Databricks DB. 
// MAGIC 1 - Drop table `dbstates` if already exists  
// MAGIC 2 - Create new table `dbstates`.   
// MAGIC The table `dbstates` will contain all fields from step 4 in the **Transform** section

// COMMAND ----------

// MAGIC %sql 
// MAGIC drop table IF EXISTS default.dbstates;
// MAGIC create table IF NOT EXISTS default.dbstates (
// MAGIC state_id int,
// MAGIC state string,
// MAGIC abbreviation string,
// MAGIC capital string,
// MAGIC pop2010 int,
// MAGIC pop2011 int,
// MAGIC pop2012 int,
// MAGIC pop2013 int,
// MAGIC pop2014 int,
// MAGIC pop2015 int,
// MAGIC pop2016 int,
// MAGIC pop2017 int,
// MAGIC pop2018 int,
// MAGIC pop2019 int,
// MAGIC growth_10_11 float,
// MAGIC growth_11_12 float,
// MAGIC growth_12_13 float,
// MAGIC growth_13_14 float,
// MAGIC growth_14_15 float,
// MAGIC growth_15_16 float,
// MAGIC growth_16_17 float,
// MAGIC growth_17_18 float,
// MAGIC growth_18_19 float,
// MAGIC total_pop_growth float,
// MAGIC fun_fact string
// MAGIC );

// COMMAND ----------

// MAGIC %md
// MAGIC ##### Insert data to `dbstates`:
// MAGIC 3 - Insert the data queried in step 4 of **Transform** section into the new created table `dbstates`

// COMMAND ----------

// Insert data
calculatedData.write.insertInto("default.dbstates")

// COMMAND ----------

// MAGIC %md
// MAGIC ##### Verify inserted data in `dbstates`:
// MAGIC 4 - Verify the data got inserted into table `dbstates` by using a select * statement.

// COMMAND ----------

// MAGIC %sql
// MAGIC SELECT * FROM default.dbstates
