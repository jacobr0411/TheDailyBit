import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SourceSort {

    private static URLConnection connection;

    public SourceSort(String source) throws IOException {
        BufferedReader reader = null;
        connection = connectWithSource(source);
        reader = getBuffer(connectWithSource(source));
    }

    private static URLConnection connectWithSource(String source) throws IOException {
        URLConnection urlConnection = null;
        URL url = new URL("https://newsapi.org/v2/top-headlines?sources="+source+"-news&apiKey=36033f4c106f44bd955f13e926095fad");
        urlConnection = url.openConnection();
        return urlConnection;
    }
    public InputStream pullBySource(String source) throws IOException {
        APIConnection apiConnection = new APIConnection();
        InputStream inputStream = null;
        if (apiConnection.canConnect())
            inputStream = connection.getInputStream();
        return inputStream;
    }
    private BufferedReader getBuffer(URLConnection connection) {
        try {
            return new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
