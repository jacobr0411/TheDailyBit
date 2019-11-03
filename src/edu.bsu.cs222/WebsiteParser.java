import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebsiteParser {

    void getArticleContent(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        System.out.println(doc.getElementsByTag("p").text());
    }

}
