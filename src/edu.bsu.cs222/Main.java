import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        //Ui to be added in iteration 3

//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                DailyBitGUI dailyBitGUI = null;
//                try {
//                    dailyBitGUI = new DailyBitGUI();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                dailyBitGUI.setVisible(true);
//            }
//        });
//    }
        String response;
        InputStream stream = null;
        JSONParser parser = new JSONParser();
        Scanner input = new Scanner(System.in);
        SourceSearch sourceSearch = new SourceSearch();

        System.out.println("Welcome to the Daily Bit.\n\nHere are the top headlines for the day:");

        try {
            sourceSearch.connectToAPI();
            stream = sourceSearch.pullInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        parser.getArticles(stream);
        parser.getTitleList();


        while (true) {
            System.out.println("\nWould you like to narrow your search by a source, catagory, key words, or country? yes or no");
            System.out.println("Press 'q' at any time to quit program");
            response = input.nextLine().toLowerCase();
            if (response.equals("q")){
                break;
            }
            if (response.equals("yes")) {
                System.out.println("Source, Catagory, Key Words, or Country?");
                response = input.nextLine().toLowerCase();

                if (response.equals("q")){
                    break;
                }

                if (response.equals("key words")){
                    System.out.println("Enter Search");
                    response= input.nextLine().toLowerCase();
                    System.out.printf("\nThese are the top %s headlines for today:\n", response);
                    parser = new JSONParser();
                    try {
                        sourceSearch.connectToAPIByKeyWords(response);
                        stream = sourceSearch.pullInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    parser.getArticles(stream);
                    parser.getTitleList();
                    System.out.println("Enter number of article you would like to open");
                    response = input.nextLine();
                    int i = Integer.parseInt(response);
                    parser.getURLContent(i - 1);
                }

                if (response.equals("source")) {
                    System.out.println("Enter the source: ei:ESPN, CNN, IGN");
                    response = input.nextLine().toLowerCase();
                    System.out.printf("\nThese are the top %s headlines for today:\n", response);
                    parser = new JSONParser();
//                sourceSearch.setIdentifier(response);
                    try {
                        sourceSearch.connectToAPIBySource(response);
                        stream = sourceSearch.pullInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    parser.getArticles(stream);
                    parser.getTitleList();
                    System.out.println("Enter number of article you would like to open");
                    response = input.nextLine();
                    int i = Integer.parseInt(response);
                    parser.getURLContent(i - 1);
                }

                if (response.equals("country")) {
                    System.out.println("Enter the Country: ei:us, au, fr");
                    response = input.nextLine().toLowerCase();
                    System.out.printf("\nThese are the top %s headlines for today:\n", response);
                    parser = new JSONParser();
                    try {
                        sourceSearch.connectToAPIByCountry(response);
                        stream = sourceSearch.pullInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    parser.getArticles(stream);
                    parser.getTitleList();
                    System.out.println("Enter number of article you would like to open");
                    response = input.nextLine();
                    int i = Integer.parseInt(response);
                    parser.getURLContent(i - 1);

                }

                if (response.equals("catagory")) {
                    System.out.println("Enter the Catagory: ei: business, entertainment, general, health, science, sports, or technology");
                    response = input.nextLine().toLowerCase();
                    System.out.printf("\nThese are the top %s headlines for today:\n", response);
                    parser = new JSONParser();
                    try {
                        sourceSearch.connectToAPIByCatagory(response);
                        stream = sourceSearch.pullInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    parser.getArticles(stream);
                    parser.getTitleList();
                    System.out.println("Enter number of article you would like to open");
                    response = input.nextLine();
                    int i = Integer.parseInt(response);
                    parser.getURLContent(i - 1);

                }
                else {
                    System.out.println("Input not recognized");
                }
            }
            if (response.equals("no")) {
                System.out.println("Enter number of article you would like to open");
                response = input.nextLine();
                int i = Integer.parseInt(response);
                parser.getURLContent(i - 1);

            }
            else {
                System.out.println("Input not recognized");
            }
            System.out.println("Would you like to continue?");
            response = input.nextLine();
            if (response.equals("yes")){
                parser = new JSONParser();
                try {
                    sourceSearch.connectToAPI();
                    stream = sourceSearch.pullInputStream();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                parser.getArticles(stream);
                parser.getTitleList();
            }

            if (response.equals("no")){
                break;
            }
        }
        input.close();
    }

}
