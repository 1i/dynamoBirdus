## DynamoBirdus
Retrieve bird sightings from a website  
Parse the records and save into a DynamoDB  
Read the daily results from the DynamoDB  

### DynamoDB

Main Table

|partitionKey |Sort Key | other attributes
|-------------|---------|
|reference    | date    | commonName ;county ;location ;count

Future plans to improve the querying for 

|partitionKey |Sort Key | other attributes
|-------------|---------|
|date         |location | commonName ;county ;location ;count ; reference



