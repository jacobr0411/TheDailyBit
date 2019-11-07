import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

class SourceSearch {
    private URLConnection connection = null;

    SourceSearch() {
    } //Default Constructor

    void connectToAPI() throws IOException {
        URL url = new URL(" https://newsapi.org/v2/top-headlines?country=us&apiKey=36033f4c106f44bd955f13e926095fad");
        connection = url.openConnection();
    }

    void connectToAPIBySource(String source) throws IOException {
        source = source.strip();
        URL url = new URL("https://newsapi.org/v2/top-headlines?sources=" + source + "&apiKey=36033f4c106f44bd955f13e926095fad");
        connection = url.openConnection();
    }

    void connectToAPIByCountry(String country) throws IOException {
        country = country.strip();
        URL url = new URL("https://newsapi.org/v2/top-headlines?country=" + country + "&apiKey=36033f4c106f44bd955f13e926095fad");
        connection = url.openConnection();
    }

    void connectToAPIByCatagory(String catagory) throws IOException {
        catagory = catagory.strip();
        URL url = new URL("https://newsapi.org/v2/top-headlines?country=us&category=" + catagory + "&apiKey=36033f4c106f44bd955f13e926095fad");
        connection = url.openConnection();
    }

    void connectToAPIByKeyWords(String key) throws IOException {
        key = key.strip();
        URL url = new URL("https://newsapi.org/v2/top-headlines?q="+key+"&apiKey=36033f4c106f44bd955f13e926095fad");
        connection = url.openConnection();
    }

    InputStream pullInputStream() throws IOException {
        InputStream inputStream = null;
        if (connection != null)
            inputStream = connection.getInputStream();
        return inputStream;
    }
}
