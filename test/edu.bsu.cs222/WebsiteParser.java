import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class WebsiteParser {

    @Test
    public void getArticleContent() throws IOException {
        String url = "https://www.tmz.com/2019/11/02/chicago-photoshop-flinstones-kim-kanye-family-halloween-photo-shoot/";
        Document doc = Jsoup.connect(url).get();
        //Elements elements = doc.getElementsByTag("p");
        //System.out.println(elements.text());
        Assert.assertNotNull(doc);
        System.out.println(doc.getElementsByTag("body").text());
        //System.out.println(doc.getElementsByTag("p").text());
        //System.out.println(doc.outerHtml());
    }
}
