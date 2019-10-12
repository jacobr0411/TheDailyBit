import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GoogleConnection {
    private static BufferedReader reader = null;
    private URLConnection urlConnection = null;

    public void setUrlConnection(){
        urlConnection = connectToGoogle();
        reader = getBuffer(urlConnection);
    }

    private BufferedReader getBuffer(URLConnection connection) {
        try {
            connection.setRequestProperty("User-BSU", "Revision Tracker / 0.1(jwrosner@bsu.edu)");
            return new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public URLConnection connectToGoogle() {
        URLConnection urlConnection = null;
        try {
            URL url = new URL(" https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=36033f4c106f44bd955f13e926095fad");
            urlConnection = url.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlConnection;
    }

    public InputStream pullInputStream() throws Exception {
        InputStream inputStream = null;

        if (canConnect()) {
            inputStream = urlConnection.getInputStream();
        } else {
            System.out.println("No connection found");
        }
        return inputStream;
    }

    private boolean canConnect() {
        if (urlConnection != null) {
            return true;
        } else {
            return false;
        }
    }
}
