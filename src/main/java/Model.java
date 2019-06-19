import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "birdus")
public class Model {

    @DynamoDBIgnore
    private String resultNumber;
    @DynamoDBHashKey
    private String reference;
    @DynamoDBAttribute
    private String date;
    @DynamoDBAttribute
    private String commonName;
    @DynamoDBAttribute
    private String scientificName;
    @DynamoDBAttribute
    private String count;
    @DynamoDBAttribute
    private String location;
    @DynamoDBAttribute
    private String county;

    public Model(String resultNumber, String reference, String date, String commonName, String scientificName, String count, String location, String county) {
        this.resultNumber = resultNumber;
        this.reference = reference;
        this.date = date;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.count = count;
        this.location = location;
        this.county = county;
    }

    public String getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(String resultNumber) {
        this.resultNumber = resultNumber;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "{" +
                "resultNumber='" + resultNumber + '\'' +
                ", reference='" + reference + '\'' +
                ", date='" + date + '\'' +
                ", commonName='" + commonName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", count='" + count + '\'' +
                ", location='" + location + '\'' +
                ", county='" + county + '\'' +
                '}';
    }
}