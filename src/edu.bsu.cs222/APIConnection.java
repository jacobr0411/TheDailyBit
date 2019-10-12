import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class APIConnection {

    private static BufferedReader reader = null;
    private URLConnection connection;

    public APIConnection() {
        connection = connectToWikipedia();
        reader = getBuffer(connection);
    }

    public InputStream pullInputStream() throws Exception {
        InputStream inputStream = null;

        if (canConnect()) {
            inputStream = connection.getInputStream();
        } else {
            System.out.println("No connection found");
        }
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

    private static URLConnection connectToWikipedia() {
        URLConnection urlConnection = null;

        try {
            URL url = new URL(" https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=36033f4c106f44bd955f13e926095fad");
            urlConnection = url.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlConnection;
    }

    private boolean canConnect() {
        if (connection != null) {
            return true;
        } else {
            return false;
        }
    }
}
