package com.paddy.dynamodb;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.local.shared.access.AmazonDynamoDBLocal;
import com.amazonaws.services.dynamodbv2.model.BatchWriteItemRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.WriteRequest;

import java.util.List;

public class DynamoClient {

    private AmazonDynamoDB dynamodb;

    public void setupDynamodb() {

        System.out.println("setupDynamodb");
        dynamodb = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1)
                .build();
    }

    public void listTables(){
        System.out.println("listTables");
        //dynamodb.
        ListTablesResult listTablesResult = dynamodb.listTables();
        List<String> tableNames = listTablesResult.getTableNames();

        tableNames.forEach(System.out::println);
    }

    public void insert(){
        AmazonDynamoDB dynamodb = null;
        try {
            // Create an in-memory and in-process instance of DynamoDB Local that skips HTTP
            dynamodb = DynamoDBEmbedded.create().amazonDynamoDB();
            // use the DynamoDB API with DynamoDBEmbedded
            dynamodb.listTables();
        } finally {
            // Shutdown the thread pools in DynamoDB Local / Embedded
            if(dynamodb != null) {
                dynamodb.shutdown();
            }
        }
    }


    public void mapper(List<Model> models){
        System.out.println("Mapper");
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();

        DynamoDBMapper mapper = new DynamoDBMapper(dynamodb);

        //mapper.save(models.get(1));
        mapper.batchSave(models);
    }
}
