package edu.sda.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class PokeWebClient {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private static final String POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/";
    private static final String LOCATION_URL = "https://pokeapi.co/api/v2/location/";

    public String retrieveNames() {
        String requestURL = POKEMON_URL;
        return sendGet(requestURL);
    }

    public String retrieveLocations() {
        String requestURL = LOCATION_URL;
        return sendGet(requestURL);
    }

    public String retrievePoke(String inputName) {
        String requestURL = POKEMON_URL + inputName;
        return sendGet(requestURL);
    }

    public String retrieveLocation(String inputLocation) {
        String requestURL = LOCATION_URL + inputLocation;
        return sendGet(requestURL);
    }

    private String sendGet(String requestURL) {

        HttpGet request = new HttpGet(requestURL);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
        System.out.println("Empty response from PokeAPI");
        return "";
    }

    public void closeConnection() {
        try {
            httpClient.close();
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
    }
}

