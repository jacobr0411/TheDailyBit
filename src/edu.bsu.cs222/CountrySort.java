package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class CountrySort {
    private static URLConnection connection = null;

    CountrySort() throws IOException{
        connectToAPI();
    }

    CountrySort(String countryName) throws IOException{
        connectToAPI(countryName);
    }

    private static void connectToAPI() throws IOException{
        URL url = new URL(" https://newsapi.org/v2/top-headlines?country=us&apiKey=36033f4c106f44bd955f13e926095fad");
        connection = url.openConnection();
    }

    private static void connectToAPI(String country) throws IOException {
        URL url = new URL("https://newsapi.org/v2/top-headlines?country="+country+"&apiKey=36033f4c106f44bd955f13e926095fad");
        connection = url.openConnection();
    }

    InputStream pullInputStream() throws IOException {
        InputStream inputStream = null;
        if (connection != null)
            inputStream = connection.getInputStream();
        return inputStream;
    }
}
