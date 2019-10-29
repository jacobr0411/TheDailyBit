import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebsiteParser {

    void getArticleContent() throws IOException {
        Document doc = Jsoup.connect("http://google.com").get();
        Elements elements = doc.getElementsByTag("p");

    }

}
