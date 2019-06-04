
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.ArrayList;

public class Lambda {

    public void handler(Context context) {
        System.out.println("Starting");

        String url = "http://www.irishbirding.com/birds/web?task=PrintableBasicBirdSightingSearch&offset=0";

        Parser parser = new Parser();
        ArrayList results = parser.parse(url);


        if(results.size()>1) {
            DynamoClient dynamoClient = new DynamoClient();
            dynamoClient.listTables();
            dynamoClient.save(results);
        }
        System.out.println("Finished");
    }
}
