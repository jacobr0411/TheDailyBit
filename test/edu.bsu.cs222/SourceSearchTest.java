import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class SourceSearchTest {

    @Test
    void connectToAPI() throws IOException {
        SourceSearch sourceSearch = new SourceSearch();
    }

    @Test
    void connectToAPIBySource() {
    }

    @Test
    void connectToAPIByCountry() {
    }

    @Test
    void connectToAPIByCatagory() throws IOException {
        String catagory = "business";
        InputStream stream = null;
        SourceSearch sourceSearch = new SourceSearch();
        JSONParser parser = new JSONParser();
        sourceSearch.connectToAPIByCatagory(catagory);
        stream=sourceSearch.pullInputStream();
        parser.getArticles(stream);
        parser.getTitleList();
    }

    @Test
    void pullInputStream() {
    }
}