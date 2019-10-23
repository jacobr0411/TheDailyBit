import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Parser {
    private List<Article> articleList = new ArrayList<>();

    List<Article> getArticleList() {
        return articleList;
    }

    void getArticles(InputStream stream) {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(stream);
        JsonElement rootElement = parser.parse(reader);
        JsonArray articles = rootElement.getAsJsonObject().getAsJsonArray("articles");

        for (JsonElement value : articles) {
            Article article = getValues(value);
            articleList.add(article);
        }
    }

    private Article getValues(JsonElement value) {
        String author = "", title = "", description = "", url = "", urlToImage = "", publishedDate = "";

        for (Map.Entry<String, JsonElement> revisionMap : value.getAsJsonObject().entrySet()) {
            String currentValue = revisionMap.getValue().toString();
            String key = revisionMap.getKey();
            switch (key) {
                case "title":
                    title = currentValue;
                    break;
                case "description":
                    description = currentValue;
                    break;
                case "url":
                    url = currentValue;
                    break;
                case "urltoImage":
                    urlToImage = currentValue;
                    break;
                case "publishedAt":
                    publishedDate = currentValue;
                    break;
            }
        }
        return new Article(author, title, description, url, urlToImage, publishedDate);
    }

    void getTitleList() {
        for (int i = 1; i <= articleList.size(); i++) {
            System.out.printf("%d. \n%s\n\n", i, articleList.get(i - 1).getTitle());
        }
    }

    void getURLContent(int articleNumber){
        System.out.println(articleList.get(articleNumber - 1).getUrl());
    }
}
