package edu.bsu.cs222;

import javafx.beans.binding.ListExpression;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ParseSearchTerms {
    private Scanner x;
    public HashMap<String,String> countries = new HashMap<>();
    public List<String> fullNames = null;


    public void openFile() throws FileNotFoundException {
        File file = new File("Countries.txt");
        Scanner x = new Scanner(file);
    }
    public void readFile(){
        x.useDelimiter(" ");
        while (x.hasNext()){
            String fullName = x.next();
            String abreviation = x.next();
            System.out.printf("%s %s\n",fullName,abreviation);
//            fullNames.add(fullName);
//            countries.put(fullName,abreviation);
        }
    }
    public void closeFile(){
        x.close();
    }

    public String pickTerm(String fullName) throws FileNotFoundException {
        return countries.get(fullName);
    }
    public List<String> getCountries(){
        return fullNames;
    }
}
