# Databricks Notebooks Demo  

**What is covered in this demo?**  
This demo will show how to use Azure Databricks Notebooks for an ETL (Extract, Transform and Load) process.  

Let's start with the basics...  

**What is Azure Databricks?**  
Azure Databricks is a collaborative data analytics platform that seamlessly integrates with Azure services such as Azure Active Directory, SQL Data Warehouse, Azure Storage, Power BI and open source libraries.  

**What is a Databricks Notebook?**  
A Databricks Notebook is a web-based interface to a document that contains runnable code, visualizations (Dashboards) and narrative (Documentation such as MD files).  

**What is so cool about Notebooks?**  
Notebooks support 4 different languages that you can switch between in a single Notebook if necessary. The supported languages are Scala, Python, SQL and R.  
Notebooks come with built in version control where you can track and see all the changes made inside your Databricks Workspace but you can also choose to keep your Notebooks in a repo.  
Azure Databricks can be used for small or massive jobs.  

### Pre-requisites  

1. Azure Subscription
2. Azure Databricks Service
3. Azure Blob Storage Account
4. Azure Key Vault

## Notebooks  

## Configurations
In this notebook the mount `/mnt/demo` will be created to set up the connection between Azure Databricks and Azure Blob Storage using secrets from Key Vault.  
This mount will be created in the DBFS (DataBricks File System). Allowing to access the files from the container as if they were stored locally. 

**What is DataBricks File System (DBFS)?**
The Databricks File System (DBFS) is a distributed file system mounted into an Azure Databricks workspace and available on Azure Databricks clusters.  
Allows you to mount cloud object storage locations so that you can map storage credentials to paths in the Azure Databricks workspace.  
You can learn more about DBFS here: https://learn.microsoft.com/en-us/azure/databricks/dbfs/  

## ETL
In this notebook is where the Extractions, Transformation and Loading happens. 

In the `Extract` section population data from the year 2010 to 2019 for all 50 states of the USA will be extracted from the csv file `censusdata.csv` and saved into a DataFrame.  

**What is a DataFrame?**
A DataFrame is a data structure that organizes data into a 2-dimensional table of rows and columns, much like a spreadsheet.  
[Source: What is a DataFrame?](https://www.databricks.com/glossary/what-are-dataframes#:~:text=A%20DataFrame%20is%20a%20data,storing%20and%20working%20with%20data.)

In the `Transform` section data will be transformed. Tranformation includes:  
1 - Removing the columns `Percent_Of_Us and Density`  
2 - Transformed data will be saved into a new DataFrame called `transformedStatesDF`  
3 - A temporary table called `statesTemp` will be registered with the `transformedStatesDF` DataFrame data  
4 - Using a SELECT statement all fields from statesTemp will be retrieved and additionally the population growth for each year for all states as well as the total population growth from 2010 to 2019 will be calculated.   

In the `Load` section a new SQL table `dbstates` will be created.   
The table dbstates will contain all fields from step 4 in the Transform section.  
Data queried in step 4 of **Transform** section will be inserted into the newly created table `dbstates`.  
Verify the data got inserted into table dbstates by using a select * statement.  

## Build_Dashboard  
In this notebook a dashboard with cool visuals will be built using all the data loaded from the ETL notebook.  

