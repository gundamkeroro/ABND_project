package com.example.fengxinlin.nanodegreep7;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fengxinlin on 10/7/16.
 */
public class HTTPHandler {
    static String stream = null;

    private static final int REQUEST_CODE_OK = 200;

    public HTTPHandler() {

    }

    public String GetHTTPData(String inputUrl) {
        try {
            URL url = new URL(inputUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if (httpURLConnection.getResponseCode() == REQUEST_CODE_OK) {
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                stream = stringBuilder.toString();
                httpURLConnection.disconnect();
            } else {
                //************
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return stream;
    }
}
