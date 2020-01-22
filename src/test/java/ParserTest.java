import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ParserTest {

    Parser parser = new Parser();

    @Test
    public void localHTML() {

        URL resource = Parser.class.getClassLoader().getResource("result.html");

        ArrayList result = parser.parse(resource.toString());

        Assert.assertEquals(29, result.size());
    }


    @Test
    public void remoteHTML() {

        String url = "http://www.irishbirding.com/birds/web?task=PrintableBasicBirdSightingSearch&offset=0";
        ArrayList result = parser.parse(url);
        System.out.println(result.size());
        System.out.println(result.get(0));
        System.out.println(result.get(1));
    }

    @Test
    public void dateFormats(){

        LocalDate yesterday = LocalDate.now().minusDays(1);
        String yesterdate = yesterday.format(DateTimeFormatter.ofPattern("dd MMM yy")).toString();
        String september = "28 Sept 19";

        LocalDate parse = LocalDate.parse(september);
        System.out.println(parse);
        System.out.println(yesterdate);

    }
}
