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
        connection = connectToAPI();
        reader = getBuffer(connection);
    }

//
//    public InputStream pullBySource(String source) throws IOException {
//
//        InputStream inputStream = null;
//        if (canConnect())
//            inputStream = connection.getInputStream();
//        return inputStream;
//    }


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

    private static URLConnection connectToAPI() {
        URLConnection urlConnection = null;

        try {
            URL url = new URL(" https://newsapi.org/v2/top-headlines?country=us&apiKey=36033f4c106f44bd955f13e926095fad");
            urlConnection = url.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlConnection;
    }

//    private static URLConnection connectWithSource(String source) throws IOException {
//        URLConnection urlConnection = null;
//        URL url = new URL("https://newsapi.org/v2/top-headlines?sources="+source+"-news&apiKey=36033f4c106f44bd955f13e926095fad");
//        urlConnection = url.openConnection();
//        return urlConnection;
//    }

    boolean canConnect() {
        if (connection != null) {
            return true;
        } else {
            return false;
        }
    }
}
