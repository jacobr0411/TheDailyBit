import org.junit.Assert;
import org.junit.Test;

import java.net.URL;
import java.net.URLConnection;

public class test {
    @Test
    public void  connectToGoogle() {
        URLConnection urlConnection = null;
        boolean connected=false;
        try {
            URL url = new URL(" https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=36033f4c106f44bd955f13e926095fad");
            urlConnection = url.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (urlConnection != null){
            connected=true;
        }
            Assert.assertEquals(connected,true);
    }

}
