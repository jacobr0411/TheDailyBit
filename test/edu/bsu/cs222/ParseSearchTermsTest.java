package edu.bsu.cs222;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ParseSearchTermsTest {

    @Test
    void openFile() {
    }

    @Test
    void readFile() throws FileNotFoundException {
        ParseSearchTerms parseSearchTerms = new ParseSearchTerms();
        parseSearchTerms.openFile();
        parseSearchTerms.readFile();
        parseSearchTerms.closeFile();
    }

    @Test
    void closeFile() {
    }

    @Test
    void pickTerm() {

    }

    @Test
    void getCountries() {
    }
}