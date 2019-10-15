import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {
    private List<Articles> articleList = new ArrayList<Articles>();

    public void connectToGoogle(InputStream stream) {

        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(stream);
        JsonElement rootElement = parser.parse(reader);
        JsonArray articles = rootElement.getAsJsonObject().getAsJsonArray("articles");

        for (JsonElement value : articles) {
            Articles revision = getValues(value);
            articleList.add(revision);
        }
    }

    private Articles getValues(JsonElement value) {
        String author = "", title = "", description = "", url = "", urlToImage = "", publishedDate = "";
        Articles article;

        for (Map.Entry<String, JsonElement> revisionMap : value.getAsJsonObject().entrySet()) {
            String currentValue = revisionMap.getValue().getAsString();
            if (revisionMap.getKey().equals("author")) {              //Switch statement would not work
                author = currentValue;                            //because these aren't constant
            }
            if (revisionMap.getKey().equals("title")) {       //variables.
                title = revisionMap.getValue().getAsString();
            }
            if (revisionMap.getKey().equals("description")) {
                description = currentValue;
            }
            if (revisionMap.getKey().equals("url")) {
                url = currentValue;
            }
            if (revisionMap.getKey().equals("urltoImage")) {
                urlToImage = currentValue;
            }
            if (revisionMap.getKey().equals("publishedDate")) {
                publishedDate = currentValue;
            }
        }
        article = new Articles(author, title, description, url, urlToImage, publishedDate);
        return article;
    }

    public void getArticleList() {
        for (Articles article : articleList) {
            System.out.println(article.toString());
        }
    }
}
