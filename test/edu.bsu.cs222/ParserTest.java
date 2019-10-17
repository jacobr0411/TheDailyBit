import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    @Test
    public void getArticles() {
        InputStream input = getClass().getClassLoader().getResourceAsStream("sample.json");
        Parser parser = new Parser();
        parser.getArticles(input);
    }

    @Test
    public void getTitleList() {
        List<Article> articleList = new ArrayList<>();
        Parser parser = new Parser();
        parser.getTitleList();
    }
}