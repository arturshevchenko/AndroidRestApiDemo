package com.example.petstorerestapi;

import android.net.Uri;
import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {

    final static String BASE_URL = "https://petstore.swagger.io/v2/pet";

    public static URL buildUrl() {

        Uri urlStr = Uri.parse(BASE_URL)
                .buildUpon().build();
        URL url = null;
        try {
            url = new URL(urlStr.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String createPet(PetModel pet) throws IOException {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(BASE_URL);
            connection = (HttpURLConnection) url.openConnection();

            // Set request method to POST
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Set request headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Write request body to output stream
            String requestBody = String.format("{" +
                    "\"id\": %d, " +
                    "\"name\": \"%s\", " +
                    "\"status\": \"%s\"" +
                    "}", pet.getId(), pet.getPetName(), pet.getStatus());

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());
            outputStream.flush();
            outputStream.close();

            // Send request and Read response from input stream
            int responseCode = connection.getResponseCode();

            InputStream inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }
            reader.close();

            String responseBody = response.toString();
            // log request and response details
            Log.d("APIS_LOGS", "API request:\nURL: " + url +
                    "\nMethod: POST\nHeaders: " + connection.getHeaderFields().toString() +
                    "\nBody: " + requestBody +
                    "\nResponse code: " + responseCode +
                    "\nResponse body: " + responseBody);

            inputStream.close();
            // Print response
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }


    public static String getPet(int petID) throws IOException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(BASE_URL + "/" + petID);
            connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Set request headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Read response from input stream
            int responseCode = connection.getResponseCode();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }
            reader.close();

            String responseBody = response.toString();
            // log request and response details
            Log.d("APIS_LOGS", "API request:\nURL: " + url +
                    "\nMethod: GET\nHeaders: " + connection.getHeaderFields().toString() +
                    "\nResponse code: " + responseCode +
                    "\nResponse body: " + responseBody);

            inputStream.close();
            // Print response
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }
}
