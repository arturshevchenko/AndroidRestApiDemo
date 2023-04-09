package com.example.petstorerestapi;

import android.net.Uri;

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

            // Set request headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Write request body to output stream
            String requestBody = String.format("{" +
                    "\"id\": %d, " +
                    "\"name\": \"%s\", " +
                    "\"status\": \"%s\"" +
                    "}", pet.getId(), pet.getName(), pet.getStatus());

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());
            outputStream.flush();
            outputStream.close();

            // Read response from input stream
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            inputStream.close();

            // Print response
            return response.toString();
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection!=null)
                connection.disconnect();
        }
    }


    public static String getPet(int petID) throws IOException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(BASE_URL + "/" + petID);
            connection = (HttpURLConnection) url.openConnection();

            // Set request method to POST
            connection.setRequestMethod("GET");

            // Set request headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Read response from input stream
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            inputStream.close();

            // Print response
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }
}
