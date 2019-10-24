package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String response;
        InputStream stream = null;
        Parser parser = new Parser();
        Scanner input = new Scanner(System.in);
        SourceSort sourceSort = new SourceSort();
        CountrySort countrySort = new CountrySort();

        System.out.println("Welcome to the Daily Bit.\n\nHere are the top headlines for the day:");
        try {
            stream = sourceSort.pullInputStream();
            stream = countrySort.pullInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        parser.getArticles(stream);
        parser.getTitleList();
        parser.getContent();
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
                sourceSort = new SourceSort(response);
                try {
                    stream = sourceSort.pullInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                parser.getArticles(stream);
                parser.getTitleList();
            }
            if(response.equals("country")){
                System.out.println("Enter the country: ei: US, FR, RU");
                response = input.nextLine().toLowerCase();
                System.out.printf("\nThese are the top %s headlines for today:\n", response);
                parser = new Parser();
                countrySort = new CountrySort(response);
                try {
                    stream = countrySort.pullInputStream();
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
