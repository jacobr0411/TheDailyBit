public class Main {

    public static void main(String[] args) throws Exception {
        GoogleConnection googleConnection = new GoogleConnection();
        googleConnection.setUrlConnection();
        googleConnection.pullInputStream();
    }
}
