import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

        //todo hardcoded location
        Elements results = tableText.get(4).parent().children();

        ArrayList models = new ArrayList();
        for (Element result : results) {
            if (result.childNodes().size() == 19) {

                //regex to remove between html tags
                String num = result.childNodes().get(1).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String id = result.childNodes().get(3).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String date = result.childNodes().get(5).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String commonName = result.childNodes().get(7).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String sciName = result.childNodes().get(9).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String count = result.childNodes().get(11).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String location = result.childNodes().get(13).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String county = result.childNodes().get(15).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");


                Model Model = new Model(num, id, date, commonName, sciName, count, location, county);
                System.out.println(Model);
                models.add(Model);
            }

        }
        System.out.println(models.size());
    }

}
