package edu.bsu.cs222;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.InputStream;

public class Controller {

    public TextField searchTerm;
    public ChoiceBox sourceSelector;
    public ChoiceBox<String> countrySelector;
    public ChoiceBox catagorySelector;

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

        try {
            sourceSearch.connectToAPIByCountry(countrySelector.getValue().toString());
            stream = sourceSearch.pullInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        parser.getArticles(stream);
        parser.getTitleList();
        listView.getItems().addAll(parser.TitleList());

    }

    public void titleCliked() throws Exception {
        int input = listView.getSelectionModel().getSelectedIndex();
        parser.getURLContent(input);
    }

    public void initialize(){

        countrySelector.getItems().addAll("","us","jp");

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