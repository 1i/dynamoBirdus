import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    public static void main(String args[]) throws IOException, URISyntaxException {

        URL resource = Parser.class.getClassLoader().getResource("result.html");
        File file = new File(resource.toURI());
        String todaysDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
        FileWriter fileWriter = new FileWriter("output/"+todaysDate+".txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        //Document document = Jsoup.parse(file, "UTF-8");
        Document document = Jsoup.connect("http://www.irishbirding.com/birds/web?task=PrintableBasicBirdSightingSearch&offset=0").get();

        Elements tableText = document.getElementsByTag("tr");

        //todo hardcoded location
        Elements results = tableText.get(4).parent().children();



        ArrayList models = new ArrayList();
        for (Element result : results) {
            //why 19 again? ..
            if (result.childNodes().size() == 19) {

                //regex to remove between html tags
                String num = result.childNodes().get(1).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String id = result.childNodes().get(3).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String date = result.childNodes().get(5).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                if (date.equals("Today")) {
                    date = todaysDate;
                }
                String commonName = result.childNodes().get(7).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String sciName = result.childNodes().get(9).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String count = result.childNodes().get(11).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String location = result.childNodes().get(13).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");
                String county = result.childNodes().get(15).toString().replaceAll("<(.|\\n)+?>|\n|^\\s*\\r?\\n| &nbsp; ", "");


                Model model = new Model(num, id, date, commonName, sciName, count, location, county);
                printWriter.println(model);
                models.add(model);
            }

        }

        printWriter.close();


        System.out.println(models.size());
    }

}
