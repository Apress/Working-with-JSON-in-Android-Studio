package com.radefffactory.jsoncontacts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class NetworkUtils {

    //download online file
    public static String getJsonFromNetwork(String link) {
        String output = "";

        try {
            URL url = new URL(link);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
                output = stringBuilder.toString();
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

}
