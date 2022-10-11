// Databricks notebook source
// MAGIC %md
// MAGIC #Configurations

// COMMAND ----------

// MAGIC %md
// MAGIC ##Mount Azure Storage
// MAGIC 1 - Create the mount `/mnt/demo` if it doesn't exist to set up the connection between Azure Databricks and Azure Blob Storage Account. This mount will be created in the DBFS (DataBricks File System).   
// MAGIC The `Databricks File System (DBFS)` is a distributed file system mounted into an Azure Databricks workspace and available on Azure Databricks clusters. Allows you to mount cloud object storage locations so that you can map storage credentials to paths in the Azure Databricks workspace in other words it allows you to access files as if they were stored locally.  
// MAGIC You can learn more about DBFS here: https://learn.microsoft.com/en-us/azure/databricks/dbfs/  

// COMMAND ----------

// DBTITLE 0,Mount Azure Storage
val configs = "fs.azure.account.key.databricksdemostoragezr.blob.core.windows.net"

// Verify if mounting exists
if (!dbutils.fs.mounts.map(mnt => mnt.mountPoint).contains("/mnt/demo"))
{
  // Setup mount if it doesn't exist
  try{
    //This mounting will setup the connection between Azure Databricks and Azure Blob Storage using secrets from Key Vault
       dbutils.fs.mount(
       source = "wasbs://databricks-demo-container@databricksdemostoragezr.blob.core.windows.net/",
       mountPoint = "/mnt/demo",
       extraConfigs = Map(configs -> dbutils.secrets.get(scope = "databricks-demo-secret-scope", key = "storage-key")))
      println("Mount created successfully.")
  }
  catch
  {
    case e:Exception =>
    println("Error: " + e.getMessage)
    dbutils.notebook.exit("Unable to create mount. Stopping process.")  
  }
}
else
{
  println("Mounting already exists.")
}


// COMMAND ----------

// List all files in the mount
dbutils.fs.ls("/mnt/demo")

//dbutils.fs.help()

//dbutils.fs.unmount("/mnt/demo")
