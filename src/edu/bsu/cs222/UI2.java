package edu.bsu.cs222;

import javafx.scene.control.TextArea;

public class UI2 {

    public TextArea textArea;

    public void initialize(){
        textArea.setText(Controller.article);
    }
}
