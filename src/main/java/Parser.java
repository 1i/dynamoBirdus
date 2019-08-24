import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {


    public ArrayList parse(String address) {

        ArrayList models = new ArrayList();
        System.out.println("Requesting website");
        Document document = null;
        int todaysCount = 0;
        int yesterdaysCount = 0;

        try {
            URL url = new URL(address);
            if (url.getProtocol().equals("file")) {
                document = Jsoup.parse(new File(url.toURI()), "UTF-8");
            } else {
                document = Jsoup.connect(address).userAgent("Mozilla").data("name", "birdus").get();
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        if (document == null) {
            return models;
        }
        Elements tableText = document.getElementsByTag("tr");

        //todo hardcoded location
        Elements results = tableText.get(4).parent().children();


        String htmlRegex = "<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ";
        for (Element result : results) {
            //why 19 again? ..
            if (result.childNodes().size() == 19) {

                //regex to remove between html tags
                String num = result.childNodes().get(1).toString().replaceAll(htmlRegex, "");
                String id = result.childNodes().get(3).toString().replaceAll(htmlRegex, "");
                String date = result.childNodes().get(5).toString().replaceAll(htmlRegex, "");
                String commonName = result.childNodes().get(7).toString().replaceAll(htmlRegex, "");
                String scientificName = result.childNodes().get(9).toString().replaceAll(htmlRegex, "");
                String count = result.childNodes().get(11).toString().replaceAll(htmlRegex, "");
                String location = result.childNodes().get(13).toString().replaceAll(htmlRegex, "");
                String county = result.childNodes().get(15).toString().replaceAll(htmlRegex, "");

                if (date.equals("Today")) {
                    date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yy"));
                    Model model = new Model(num, id, date, commonName, scientificName, count, location, county);
                    models.add(model);
                }

               LocalDate yesterday =  LocalDate.now().minusDays(1);
                String yesterdate = yesterday.format(DateTimeFormatter.ofPattern("dd MMM yy")).toString();
                if (date.equals("Today")) {
                    date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yy"));
                    Model model = new Model(num, id, date, commonName, scientificName, count, location, county);
                    todaysCount++;
                    models.add(model);
                }

                if (date.equals(yesterdate)) {
                    date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yy"));
                    Model model = new Model(num, id, date, commonName, scientificName, count, location, county);
                    models.add(model);
                    yesterdaysCount++;
                }

            }

        }
        System.out.println("Today's Record count: " + todaysCount);
        System.out.println("Yesterday's Record count: " + yesterdaysCount);

        return models;
    }

}
