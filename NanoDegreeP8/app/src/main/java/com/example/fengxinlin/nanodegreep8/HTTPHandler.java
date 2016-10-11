package com.example.fengxinlin.nanodegreep8;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fengxinlin on 10/4/16.
 */
public final class HTTPHandler {
    private static final int RESULT_OK = 200;

    public HTTPHandler() {
    }

    public String getHTTPData(String urlString) {
        String stream = "";
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (urlConnection.getResponseCode() == RESULT_OK) {//success
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                stream = sb.toString();
                urlConnection.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }
}