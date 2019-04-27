public class Model {

    @Override
    public String toString() {
        return "{" +
                "num='" + num + '\'' +
                ", id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", commonName='" + commonName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", count='" + count + '\'' +
                ", location='" + location + '\'' +
                ", county='" + county + '\'' +
                '}';
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Model(String num, String id, String date, String commonName, String scientificName, String count, String location, String county) {
        this.num = num;
        this.id = id;
        this.date = date;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.count = count;
        this.location = location;
        this.county = county;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    private String num;
    private String id;
    private String date;
    private String commonName;
    private String scientificName;
    private String count;
    private String location;
    private String county;
}
