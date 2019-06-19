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

|Partition Key |Sort Key | other attributes |
|--------------|---------|------------------|
|reference     | date    | commonName, county, location, count|

Future plans to improve the querying for 

|Partition Key |Sort Key | other attributes |
|--------------|---------|------------------|
|date          |county   | commonName, location, count, reference|

## Api Gateway
Allow certain users perform CRUD operations onto the DynamoDB.  

## Cloudformation and SAM
Infrastructure as Code

## Cognito
Allow anonymous users to submit bird sightings.  
Each set of user sightings is held for verification by an admin as batched by user.  

## CodeBuild
Added a buildspec.yml to root directory.  

- Installs Java8 and maven, 
- Checkouts master branch
- Performs a maven clean packge
- Takes the target jar and publishes to Lambda
- Saves the jar to s3
- Caches to .m2 folder into S3 for faster build times on next runs

Eh why use this instead of nice old Jenkins..  
