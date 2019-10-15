import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser();
        APIConnection apiConnection = new APIConnection();
        try {
            InputStream stream = apiConnection.pullInputStream();
            parser.connectToGoogle(stream);
            parser.getArticleList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
