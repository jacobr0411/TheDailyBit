import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to the Daily Bit\n\n Here are the top headlines for the day");

        Parser parser = new Parser();
        APIConnection apiConnection = new APIConnection();

        try {
            InputStream stream = apiConnection.pullInputStream();
            parser.connectToGoogle(stream);
            parser.getTitleList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Would you like to narrow your search by a source? ei:fox, cnn, bbc");
        Scanner scanner = new Scanner(System.in);
        String response =scanner.nextLine();
        try{
            SourceSort sourceSort = new SourceSort(response);
            InputStream stream = sourceSort.pullBySource(response);
            parser.connectToGoogle(stream);
            parser.getTitleList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
