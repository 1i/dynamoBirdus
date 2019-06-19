import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;

import java.util.List;

public class DynamoClient {

    private AmazonDynamoDB dynamodb;
    private DynamoDBMapper mapper;

    public DynamoClient() {

        dynamodb = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1)
                .build();
        mapper = new DynamoDBMapper(dynamodb);
    }

    public void listTables() {
        ListTablesResult listTablesResult = dynamodb.listTables();
        List<String> tableNames = listTablesResult.getTableNames();

        tableNames.forEach(System.out::println);
    }


    public void save(List<Model> models) {
        System.out.println("Saving " + models.size() + " to Dynmo");
        List<DynamoDBMapper.FailedBatch> failedBatches = mapper.batchSave(models);
        System.out.println(failedBatches.size() + " failed inserts");
    }
}
