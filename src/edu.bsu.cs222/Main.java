import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String response;
        InputStream stream = null;
        Parser parser = new Parser();
        Scanner input = new Scanner(System.in);
        SourceSort sourceSort = new SourceSort();
        CountrySort countrySort = new CountrySort();

        System.out.println("Welcome to the Daily Bit.\n\nHere are the top headlines for the day:");
        try {
            sourceSearch.connectToAPI();
            stream = sourceSearch.pullInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        parser.getArticles(stream);
        parser.getTitleList();
        parser.getURLContent(2);

        System.out.println("\nWould you like to narrow your search by a source or country?");
        response = input.nextLine().toLowerCase();
        if(response.equals("yes")) {
            System.out.println("Source or Country?");
            response = input.nextLine().toLowerCase();
            if(response.equals("source")){
                System.out.println("Enter the source: ei:ESPN, CNN, IGN");
                response = input.nextLine().toLowerCase();
                System.out.printf("\nThese are the top %s headlines for today:\n", response);
                parser = new Parser();
            sourceSearch.setIdentifier(response);
                try {
                sourceSearch.connectToAPIBySource();
                stream = sourceSearch.pullInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                parser.getArticles(stream);
                parser.getTitleList();
            }

        System.out.println("\nWould you like to sort by Country?");
        response = input.nextLine().toLowerCase();
        if(response.equals("yes")) {
                System.out.println("Enter the country: ei: US, FR, RU");
                response = input.nextLine().toLowerCase();
                System.out.printf("\nThese are the top %s headlines for today:\n", response);
                parser = new Parser();
            sourceSearch.setIdentifier(response);
                try {
                sourceSearch.connectToAPIByCountry();
                stream = sourceSearch.pullInputStream();
                }  catch (IOException e) {
                    e.printStackTrace();
                }
                parser.getArticles(stream);
                parser.getTitleList();
            }
        }
        input.close();

    }

}
