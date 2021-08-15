package edu.bsu.cs222;

import edu.bsu.cs222.Article;
import edu.bsu.cs222.JSONParser;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    @Test
    public void getArticles() {
        InputStream input = getClass().getClassLoader().getResourceAsStream("sample.json");
        JSONParser parser = new JSONParser();
        parser.getArticles(input);
    }

    @Test
    public void getTitleList() {
        List<Article> articleList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        parser.getTitleList();
    }
}