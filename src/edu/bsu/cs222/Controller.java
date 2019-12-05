package edu.bsu.cs222;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.InputStream;

public class Controller {

    public TextField searchTerm;
    public ChoiceBox<String> sourceSelector;
    public ChoiceBox<String> countrySelector;
    public ChoiceBox<String> catagorySelector;

    InputStream stream = null;
    JSONParser parser = new JSONParser();

    SourceSearch sourceSearch = new SourceSearch();
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

    public void initialize(){

        countrySelector.getItems().addAll("","us","jp");
        catagorySelector.getItems().addAll("","business","entertainment");
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