// Databricks notebook source
// MAGIC %sql
// MAGIC -- Create 'state' widget
// MAGIC CREATE WIDGET DROPDOWN state DEFAULT "California" CHOICES SELECT * FROM (VALUES ("Alabama"),("Alaska"),("Arizona"),("Arkansas"),("California"),("Colorado"),("Connecticut"),("Delaware"),("Florida"),("Georgia"),("Hawaii"),("Idaho"),("Illinois"),("Indiana"),("Iowa"),("Kansas"),("Kentucky"),("Louisiana"),("Maine"),("Maryland"),("Massachusetts"),("Michigan"),("Minnesota"),("Mississippi"),("Missouri"),("Montana"),("Nebraska"),("Nevada"),("New Hampshire"),("New Jersey"),("New Mexico"),("New York"),("North Carolina"),("North Dakota"),("Ohio"),("Oklahoma"),("Oregon"),("Pennsylvania"),("Rhode Island"),("South Carolina"),("South Dakota"),("Tennessee"),("Texas"),("Utah"),("Vermont"),("Virginia"),("Washington"),("West Virginia"),("Wisconsin"),("Wyoming"));
// MAGIC 
// MAGIC -- create initial year widget with 2010 as default
// MAGIC CREATE WIDGET DROPDOWN initial_year DEFAULT "pop2010" CHOICES SELECT * FROM (VALUES ("pop2010"),("pop2011"),("pop2012"),("pop2013"),("pop2014"),("pop2015"),("pop2016"),("pop2017"),("pop2018"));
// MAGIC 
// MAGIC -- create last year widget with 2019 as default
// MAGIC CREATE WIDGET DROPDOWN last_year DEFAULT "pop2019" CHOICES SELECT * FROM (VALUES ("pop2010"),("pop2011"),("pop2012"),("pop2013"),("pop2014"),("pop2015"),("pop2016"),("pop2017"),("pop2018"),("pop2019"));

// COMMAND ----------

displayHTML(s"""
<!DOCTYPE html>
<html>
<head>
<style>
.heading{
font-size:25px;
}
.heading:hover {
color: #7248C6
}
</style>
</head>
<body>

<div class="heading">Population Growth for Selected Years and State:</div>

</body>
</html>
  """)

// COMMAND ----------

// MAGIC %sql
// MAGIC SELECT state, ROUND(((${last_year} - ${initial_year}) / ${initial_year})*100,2) AS pop_growth from default.dbstates where state = "${state}"

// COMMAND ----------

displayHTML(s"""
<!DOCTYPE html>
<html>
<head>
<style>
.heading{
font-size:25px;
}
.heading:hover {
color: #7248C6
}
</style>
</head>
<body>

<div class="heading">Fun Fact</div>

</body>
</html>
  """)

// COMMAND ----------

// MAGIC %sql
// MAGIC SELECT state, fun_fact from default.dbstates where state = "${state}"

// COMMAND ----------

displayHTML(s"""
<!DOCTYPE html>
<html>
<head>
<style>
.heading{
font-size:25px;
}
.heading:hover {
color: #7248C6;
}
</style>
</head>
<body>

<div class="heading">USA - Total Population Growth (2010-2019)</div>

</body>
</html>
  """)

// COMMAND ----------

// MAGIC %sql
// MAGIC SELECT abbreviation, total_pop_growth FROM default.dbstates limit 50

// COMMAND ----------

displayHTML(s"""
<!DOCTYPE html>
<html>
<head>
<style>
.heading{
font-size:25px;
}
.heading:hover {
color: #7248C6
}
</style>
</head>
<body>

<div class="heading">Population From 2010 to 2019 by State</div>

</body>
</html>
  """)

// COMMAND ----------

// MAGIC %sql
// MAGIC SELECT state, pop2010, pop2011, pop2012, pop2013, pop2014, pop2015, pop2016, pop2017, pop2018, pop2019 FROM default.dbstates where state = "${state}"

// COMMAND ----------

displayHTML(s"""
<!DOCTYPE html>
<html>
<head>
<style>
.heading{
font-size:25px;
}
.heading:hover {
color: #7248C6
}
</style>
</head>
<body>

<div class="heading">Population Growth Per Year By State</div>

</body>
</html>
  """)

// COMMAND ----------

// MAGIC %sql
// MAGIC SELECT growth_10_11, growth_11_12, growth_12_13, growth_13_14, growth_14_15, growth_15_16, growth_16_17, growth_17_18, growth_18_19, state FROM default.dbstates WHERE state = "${state}"
