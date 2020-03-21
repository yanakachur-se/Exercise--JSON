package edu.sda.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sda.client.PokeWebClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokeService {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    PokeWebClient pokeWebClient = new PokeWebClient();

    public String retrieveAllLocations() throws IOException {
       Map locsJson = objectMapper.readValue(pokeWebClient.retrieveLocations(), HashMap.class);
       String locations = extractAllNames(locsJson);
        return checkIfResult(locations);
    }

    public String retrieveAllPokemons() throws IOException {
        Map poksJson = objectMapper.readValue(pokeWebClient.retrieveNames(), HashMap.class);
        String poks = extractAllNames(poksJson);
        return checkIfResult(poks);
    }

    public String retrieveLocation(String location) throws IOException {
        Map locationJson = objectMapper.readValue(pokeWebClient.retrieveLocation(location), HashMap.class);
        String locations = extractLocation(locationJson);
        return checkIfResult(locations);
    }

    public String retrievePokemon(String name) throws IOException {
        Map pokemonJson = objectMapper.readValue(pokeWebClient.retrievePoke(name), HashMap.class);
        String pokemon = extractPokemon(pokemonJson);
        return checkIfResult(pokemon);
    }

    public void closeConnection(){
        pokeWebClient.closeConnection();
    }

    private String extractLocation(Map locationObj){
        String result = "";
        if(locationObj.containsKey("name")){
            result+= "Location is " + locationObj.get("name")+". ";
        }
        if(locationObj.containsKey("names")){
            List<Map> locationNames = (List) locationObj.get("names");
            if(!locationNames.isEmpty()){
                result+=" Names in other languages: ";
            }
            for (Map locationName: locationNames) {
                if (locationName.containsKey("name")) {
                    result += locationName.get("name") + " (";
                    result += ((Map)locationName.get("language")).get("name") + ");  ";
                }
            }
        }
        return result;
    }

    private String extractPokemon(Map pokeObj){
        String result = "";
        if(pokeObj.containsKey("name")){
            result+= "Name is " + pokeObj.get("name")+". ";
        }
        if(pokeObj.containsKey("id")){
            result+= "Id is " + pokeObj.get("id")+". ";
        }
        if(pokeObj.containsKey("weight")){
            result+= "Weight is " + pokeObj.get("weight")+". ";
        }
        if(pokeObj.containsKey("height")){
            result+= "Height is " + pokeObj.get("height")+". ";
        }
        return result;
    }

    private String extractAllNames(Map namesObj){
        String resultString = "";
        if(namesObj.containsKey("results")){
            List<Map> names = (List) namesObj.get("results");
            for (Map name: names) {
                if (name.containsKey("name")) {
                    resultString += name.get("name") + ", ";
                }
            }
        }
        return resultString;
    }

    private String checkIfResult(String result) {
        if (result.isEmpty())
            return "Cannot fetch this data, sorry.";
        else
            return result;
    }
}
