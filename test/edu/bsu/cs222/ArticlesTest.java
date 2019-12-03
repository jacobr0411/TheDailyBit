package edu.bsu.cs222;

import edu.bsu.cs222.Article;
import org.junit.Assert;
import org.junit.Test;

public class ArticlesTest {

    private Article articles = new Article("author", "title","description",
            "url", "urlToImage", "publishedDate", "content");

    @Test
    public void getAuthor() {
        Assert.assertNotNull(articles.getAuthor());
    }

    @Test
    public void getTitle() {
        Assert.assertNotNull(articles.getTitle());
    }

    @Test
    public void getDescription() {
        Assert.assertNotNull(articles.getDescription());
    }

    @Test
    public void getUrl() {
        Assert.assertNotNull(articles.getAuthor());
    }

    @Test
    public void getUrlToImage() {
        Assert.assertNotNull(articles.getAuthor());
    }

    @Test
    public void getPublishedDate() {
        Assert.assertNotNull(articles.getAuthor());
    }

    @Test
    public void testToString() {

    }
}