package edu.bsu.cs222;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
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

//api does not allow source serching to combine with anything else
        if (!sourceSelector.getValue().isEmpty()&!countrySelector.getValue().isEmpty()|!catagorySelector.getValue().equals("")|!searchTerm.getText().isEmpty()){
            System.out.println("You can't do this");
        }
        //for country and catagory and term search
        else if (!countrySelector.getValue().isEmpty()&!catagorySelector.getValue().equals("")&!searchTerm.getText().isEmpty()){
            try {
                sourceSearch.connectToAPIByCountryAndCatagoryAndKeyWord(countrySelector.getValue(),catagorySelector.getValue(),searchTerm.getText());
                stream = sourceSearch.pullInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            parser.getArticles(stream);
            parser.getTitleList();
            listView.getItems().addAll(parser.TitleList());
        }

        //for country and key search
        else if (!countrySelector.getValue().isEmpty()&!searchTerm.getText().isEmpty()){
            try {
                sourceSearch.connectToAPIByCountryAndKeyWords(countrySelector.getValue(),searchTerm.getText());
                stream = sourceSearch.pullInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            parser.getArticles(stream);
            parser.getTitleList();
            listView.getItems().addAll(parser.TitleList());
        }

        //for country and catagory search
        else if (!countrySelector.getValue().isEmpty()&!catagorySelector.getValue().equals("")){
            try {
                sourceSearch.connectToAPIByCountryAndCatagory(countrySelector.getValue(),catagorySelector.getValue());
                stream = sourceSearch.pullInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            parser.getArticles(stream);
            parser.getTitleList();
            listView.getItems().addAll(parser.TitleList());
        }

        //for catagory and term search
        else if (!catagorySelector.getValue().equals("")&!searchTerm.getText().isEmpty()){
            try {
                sourceSearch.connectToAPIByCatagoryAndKeyWords(catagorySelector.getValue(),searchTerm.getText());
                stream = sourceSearch.pullInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            parser.getArticles(stream);
            parser.getTitleList();
            listView.getItems().addAll(parser.TitleList());
        }

        //for country search
        else if (!countrySelector.getValue().isEmpty()) {
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

        //for catagory selector
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

        //for term search
        else if (!searchTerm.getText().isEmpty()){
            try {
                sourceSearch.connectToAPIByKeyWords(searchTerm.getText().toString()); //toString is redundant but it doesnt work without it
                stream = sourceSearch.pullInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            parser.getArticles(stream);
            parser.getTitleList();
            listView.getItems().addAll(parser.TitleList());
        }

        //for source Search
        else if (!sourceSelector.getValue().isEmpty()){
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