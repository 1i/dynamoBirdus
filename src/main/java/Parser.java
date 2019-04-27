import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Parser {

    public static void main(String args[]) throws IOException, URISyntaxException {

        URL resource = Parser.class.getClassLoader().getResource("result.html");


        File file = new File(resource.toURI());
        Document document = Jsoup.parse(file, "UTF-8");
        Elements tableText = document.getElementsByTag("tr");

        String header = "<tr> \n" +
                " <td><strong>No.</strong></td> \n" +
                " <td><strong>Ref.</strong></td> \n" +
                " <td><strong>Date</strong></td> \n" +
                " <td><strong>Common Name</strong></td> \n" +
                " <td><strong>Scientific Name</strong></td> \n" +
                " <th><strong>Number</strong></th> \n" +
                " <td><strong>Location</strong></td> \n" +
                " <td><strong>County</strong></td> \n" +
                " <th><strong>Photos</strong></th> \n" +
                "</tr>";

        Elements results = tableText.get(4).parent().children();
        System.out.println(tableText.size());

        String[] split = results.toString().trim().split("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ");

        //todo
        ArrayList formatted = new ArrayList();
        for (int i = 0; i < split.length; i++) {
            if (split[i].matches("(IB)\\d+") ||split[i].length()>1) {
                formatted.add(split[i]);
            }
        }
        ArrayList models = new ArrayList();
        for (int i = 0; i < formatted.size()-8; i +=8) {
            String num = formatted.get(i).toString();
            String id = formatted.get(i + 1).toString();
            String date = formatted.get(i + 2).toString();
            String commonName = formatted.get(i + 3).toString();
            String sciName = formatted.get(i + 4).toString();
            String count = formatted.get(i + 5).toString();
            String location = formatted.get(i + 6).toString();
            String county = formatted.get(i + 7).toString();

            Model Model = new Model(num, id, date, commonName, sciName, count, location, county);
            System.out.println(Model);
            models.add(Model);
        }

        System.out.println("Model size "+ models.size());

        Model result = (Model) models.get(1);
        System.out.println(result);


//        int start = tableText.indexOf("No. Ref. Date Common Name Scientific Name Number Location County Photos");
//        String substring = tableText.substring(start);
//
//        Pattern compile = Pattern.compile("IB\\d\\d\\d\\d\\d\\d");
//        Matcher matcher = compile.matcher(substring);
//        matcher.matches();
//        matcher.groupCount();
//        String[] birds = substring.split("IB");
//        System.out.println(birds.length);
//        String[] byNumber = birds[1].split("\\s\\d\\s");
//        System.out.println(substring);
//        List<Node> nodes = document.childNodes();

    }

}
