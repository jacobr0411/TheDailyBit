package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.CommonExtractors;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;
import de.l3s.boilerpipe.sax.HTMLFetcher;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONParser {

    private List<Article> articleList = new ArrayList<>();
    private ArrayList<String> titleList = new ArrayList<>();


    void getArticles(InputStream stream) {
        JsonParser parser = new JsonParser();
        Reader reader;
        reader = new InputStreamReader(stream);
        JsonElement rootElement = parser.parse(reader);
        JsonArray articles = rootElement.getAsJsonObject().getAsJsonArray("articles");

        for (JsonElement value : articles) {
            Article article = getValues(value);
            articleList.add(article);

        }
    }

    private Article getValues(JsonElement value) {
        String author = "", title = "", description = "", url = "", urlToImage = "", publishedDate = "", content = "";

        for (Map.Entry<String, JsonElement> revisionMap : value.getAsJsonObject().entrySet()) {
            String currentValue = revisionMap.getValue().toString();
            String key = revisionMap.getKey();
            switch (key){
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
                case "content":
                    content = currentValue;
            }
        }
        return new Article(author, title, description, url, urlToImage, publishedDate, content);
    }

    void getTitleList() {
        for (int i = 1; i <= articleList.size(); i++) {
            System.out.printf("%d. \n%s\n\n", i, articleList.get(i - 1).getTitle());
        }
    }

    ArrayList<String> TitleList(){
        for( int i = 1; i <= articleList.size(); i++){
            String title = articleList.get(i -1).getTitle();
            titleList.add(title);
        }
        return titleList;
    }

    public String getURLContent(int articleNumber) throws Exception{
        String url = articleList.get(articleNumber).getUrl().replaceAll("\"","");
        System.out.println(url);
        HTMLDocument htmlDoc = HTMLFetcher.fetch(new URL(url));
        TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();
        String content = CommonExtractors.ARTICLE_EXTRACTOR.getText(doc);
        System.out.println(content);
        return content;
    }
}
