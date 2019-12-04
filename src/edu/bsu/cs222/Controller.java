package edu.bsu.cs222;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.io.InputStream;
import java.util.Scanner;

public class Controller {

    public ListView<String> listView;
    public Button button;

    public void searchButtonClicked(){
        System.out.println("Is this thing working?");
    }

    public void titleCliked(){

    }

    public void initialize(){
        InputStream stream = null;
        JSONParser parser = new JSONParser();
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
        listView.getItems().addAll(parser.TitleList());
    }

}