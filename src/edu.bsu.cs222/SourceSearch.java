import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class SourceSearch {
    private URLConnection connection = null;
    private String identifier;

    SourceSearch() { } //Default Constructor

    void setIdentifier(String identifier) {
        this.identifier = identifier.strip();
    }

    void connectToAPI() throws IOException{
        URL url = new URL(" https://newsapi.org/v2/top-headlines?country=us&apiKey=36033f4c106f44bd955f13e926095fad");
        connection = url.openConnection();
    }

    void connectToAPIBySource() throws IOException {
        URL url = new URL("https://newsapi.org/v2/top-headlines?sources=" + identifier + "&apiKey=36033f4c106f44bd955f13e926095fad");
        connection = url.openConnection();
    }

    void connectToAPIByCountry() throws IOException {
        URL url = new URL("https://newsapi.org/v2/top-headlines?country="+ identifier +"&apiKey=36033f4c106f44bd955f13e926095fad");
        connection = url.openConnection();
    }

    InputStream pullInputStream() throws IOException {
        InputStream inputStream = null;
        if (connection != null)
            inputStream = connection.getInputStream();
        return inputStream;
    }
}
