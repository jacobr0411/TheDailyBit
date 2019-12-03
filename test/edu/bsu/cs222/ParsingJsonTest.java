package edu.bsu.cs222;

import com.google.gson.*;
import edu.bsu.cs222.Article;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParsingJsonTest {

    @Test
    public void getArticles() {
        JsonParser parser = new JsonParser();
        InputStream input = getClass().getClassLoader().getResourceAsStream("sample.json");
        assert input != null;
        Reader reader = new InputStreamReader(input);
        JsonElement rootElement = parser.parse(reader);
        JsonArray articles = rootElement.getAsJsonObject().getAsJsonArray("articles");
        List<Article> articleList = new ArrayList<>();
        for (JsonElement value : articles) {
            Article revision = getValues(value);
            articleList.add(revision);
        }
        Assert.assertEquals(10, articleList.size());
    }

    private Article getValues(JsonElement value) {
        String author = "", title = "", description = "", url = "", urlToImage = "", publishedDate = "", content = "";

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
        return new Article(author,title, description, url, urlToImage, publishedDate, content);
    }
}
