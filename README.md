## DynamoBirdus
Retrieve bird sightings from a website  
Parse the records and save into a DynamoDB  
Read the daily results from the DynamoDB  

Done as a project to learn AWS for assoicate developer exam.  

## Lambda
Simple Java web scraper parsing a table of results for bird sightings.  
Stores the results of the day into DynamoDB or S3.  
Kicked off every night as a cron job via CloudWatch Events.  

## IAM
Correct Roles created for each Service, not sharing Policies.  
IAM Policies should be created using Least Privilege Principle.  

## CloudWatch
Set up to monitor costs with alarms set if costs rise.  
Kick starts the HTML Parser Lambda on a cron job.  

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
GET and POST of a Birds and Locations resources.  

## Cloudformation and SAM
Infrastructure as Code

```
aws cloudformation package --s3-bucket birdus-code-sam --template-file template.yaml --output-template-file generated/output.yaml

aws cloudformation deploy --template-file generated/output.yaml --stack-name birdus-sam --capabilities CAPABILITY_IAM
```

## Cognito
Allow anonymous users to submit bird sightings.  
Use Federated Identities instead of the signup/signin functionality of User Pools.  
Each set of user sightings is held for verification by an admin as batched by user.  

## CodeBuild
Added a buildspec.yml to root directory.  

- Installs Java8 and maven
- Checkouts master branch
- Performs a maven clean packge
- Takes the target jar and publishes to Lambda
- Saves the jar to s3
- Caches to .m2 folder into S3 for faster build times on next runs

Eh why use this instead of nice old Jenkins..  
