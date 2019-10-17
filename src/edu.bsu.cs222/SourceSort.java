import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class SourceSort {
    private static URLConnection connection = null;

    SourceSort() throws IOException{
        connectToAPI();
    }

    SourceSort(String sourceName) throws IOException{
        connectToAPI(sourceName);
    }

    private static void connectToAPI() throws IOException{
        URL url = new URL(" https://newsapi.org/v2/top-headlines?country=us&apiKey=36033f4c106f44bd955f13e926095fad");
        connection = url.openConnection();
    }

    private static void connectToAPI(String source) throws IOException {
        URL url = new URL("https://newsapi.org/v2/top-headlines?sources="+source+"&apiKey=36033f4c106f44bd955f13e926095fad");
        connection = url.openConnection();
    }

    InputStream pullInputStream() throws IOException {
        InputStream inputStream = null;
        if (connection != null)
            inputStream = connection.getInputStream();
        return inputStream;
    }
}
