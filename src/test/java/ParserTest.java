import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.net.URL;
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
}
