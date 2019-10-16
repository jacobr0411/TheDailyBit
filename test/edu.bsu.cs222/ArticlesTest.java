import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArticlesTest {

    public Articles articles = new Articles("author", "title","description",
            "url", "urlToImage", "publishedDate");

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