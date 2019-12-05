package edu.bsu.cs222;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;

public class Controller{

    public TextField searchTerm;
    public ChoiceBox<String> sourceSelector;
    public ChoiceBox<String> countrySelector;
    public ChoiceBox<String> catagorySelector;
    public Label dateTime;


    InputStream stream = null;
    JSONParser parser = new JSONParser();

    SourceSearch sourceSearch = new SourceSearch();
    public ListView<String> listView;
    public Button button;

    static String article;

    public void searchButtonClicked(){
        stream = null;
        listView.getItems().removeAll(parser.TitleList());
        parser = new JSONParser();
        sourceSearch = new SourceSearch();

        //for country and catagory and term search
          if (!countrySelector.getValue().isEmpty()&!catagorySelector.getValue().equals("")&!searchTerm.getText().isEmpty()){
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
                sourceSearch.connectToAPIByKeyWords(searchTerm.getText()); //toString is redundant but it doesnt work without it
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
                sourceSearch.connectToAPIBySource(sourceSelector.getValue());
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
        article=parser.getURLContent(input);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UI2.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Article");
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }

    public void initialize(){

        countrySelector.getItems().addAll("","us","ar","au","at","be","br","bg","ca","cn","co","cu","cz","eg","fr","de","gr","hk","hu","in","id","ie","il","it","jp","lv","lt","my","mx","ma","nl","nz","ng","no","ph","pl","pt","ro","ru","sa","rs","sg","sk","si","za","kr","se","ch","tw","ua","ve","za");

        catagorySelector.getItems().addAll("","business","entertainment","general","health","science","sports","technology");
        sourceSelector.getItems().addAll("","bbc","cnn","cnbc","msnbc","abc-news","time","crypto-coins-news","bbc-news","cbs-news","techcrunch","entertainment-weekly","buzzfeed","engadget","espn","hacker-news","fortune","fox-sports","fox-news","ign","the-huffington-post","wired","national-geographic","the-new-york-times","the-washington-times","the-washington-post","the-wall-street-journal","usa-today","al-jazeera-english","ansa","ars-technica","ary-news","associated-press","axios","bleacher-report","bloomberg","breitbart-news","mtv-news","business-insider");

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