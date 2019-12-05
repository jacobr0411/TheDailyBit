package edu.bsu.cs222;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.InputStream;
import java.util.Scanner;

public class Controller {

    public TextField searchTerm;
    InputStream stream = null;
    JSONParser parser = new JSONParser();
    SourceSearch sourceSearch = new SourceSearch();
    public ListView<String> listView;
    public Button button;

    public void searchButtonClicked(){
        System.out.println("Is this thing working?");
    }

    public void titleCliked() throws Exception {
        int input = listView.getSelectionModel().getSelectedIndex();
        parser.getURLContent(input);
    }

    public void initialize(){
//        InputStream stream = null;
//        JSONParser parser = new JSONParser();
//        SourceSearch sourceSearch = new SourceSearch();

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