package edu.bsu.cs222;

import javafx.application.Platform;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {

    public TextField searchTerm;
    public ChoiceBox<String> sourceSelector;
    public ChoiceBox<String> countrySelector;
    public ChoiceBox<String> catagorySelector;
    public Label dateTime;

    private InputStream stream = null;
    private JSONParser parser = new JSONParser();

    private SourceSearch sourceSearch = new SourceSearch();
    public ListView<String> listView;
    public Button button;

    public void searchButtonClicked(){
        stream = null;
        listView.getItems().removeAll(parser.TitleList());
        parser = new JSONParser();
        sourceSearch = new SourceSearch();

//        listView = new ListView<>();
//        listView.refresh();
//        if (!countrySelector.getValue().isEmpty()&!catagorySelector.getValue().isEmpty()){
//
//        }
        if (!countrySelector.getValue().isEmpty()) {
            try {
                sourceSearch.connectToAPIByCountry(countrySelector.getValue());
                stream = sourceSearch.pullInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            parser.getArticles(stream);
            parser.getTitleList();
            listView.getItems().addAll(parser.TitleList());
        }

        else if (!catagorySelector.getValue().equals("")){
            try {
                sourceSearch.connectToAPIByCatagory(catagorySelector.getValue());
                stream = sourceSearch.pullInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            parser.getArticles(stream);
            parser.getTitleList();
            listView.getItems().addAll(parser.TitleList());
        }

        else if (!sourceSelector.getValue().isEmpty()&countrySelector.getValue().isEmpty()|catagorySelector.getValue().isEmpty()){
            try {
                sourceSearch.connectToAPIByKeyWords(sourceSelector.getValue());
                stream = sourceSearch.pullInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            parser.getArticles(stream);
            parser.getTitleList();
            listView.getItems().addAll(parser.TitleList());
        }
        else
            System.out.println("No input");
    }

    public void titleCliked() throws Exception {
        int input = listView.getSelectionModel().getSelectedIndex();
        parser.getURLContent(input);
    }

    public void initialize() throws FileNotFoundException {
        Thread timerThread = new Thread(() -> {
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
           while (true) {
               try {
                   Thread.sleep(1000); //1 second
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               final String time = simpleDateFormat.format(new Date());
               Platform.runLater(() -> {
                   System.out.println(time);
                   dateTime.setText(time);
               });
           }
        });

        timerThread.start();
        ParseSearchTerms countries = new ParseSearchTerms();

        countries.openFile();
        countries.readFile();

        countrySelector.getItems().addAll(countries.getCountries());
        countries.closeFile();
        catagorySelector.getItems().addAll("","business","entertainment","general","health","science","sports","technology");
        sourceSelector.getItems().addAll("","bbc","cnn");

        sourceSelector.setValue("");
        countrySelector.setValue("");
        catagorySelector.setValue("");

        System.out.println("Welcome to the Daily Bit.\n\nHere are the top headlines for the day:");

        try {
            sourceSearch.connectToAPI();
            stream = sourceSearch.pullInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        parser.getArticles(stream);
        parser.getTitleList();
        listView.getItems().addAll(parser.TitleList());
    }

}