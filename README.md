## DynamoBirdus
Retrieve bird sightings from a website  
Parse the records and save into a DynamoDB  
Read the daily results from the DynamoDB  

Done as a project to learn AWS for assoicate developer exam.  

## Lambda
Simple Java web scraper parsing a table of results for bird sightings.  
Stores the results of the day into DynamoDB or S3.  

### DynamoDB

Main Table

|partitionKey |Sort Key | other attributes |
|-------------|---------|------------------|
|reference    | date    | commonName, county, location, count|

Future plans to improve the querying for 

|partitionKey |Sort Key | other attributes |
|-------------|---------|------------------|
|date         |county   | commonName, location, count, reference|



