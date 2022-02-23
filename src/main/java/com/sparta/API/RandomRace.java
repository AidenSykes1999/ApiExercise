package com.sparta.API;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class RandomRace {
    public static String key = "results";
    public static String key2 = "name";
    public static JSONArray getArrayObjects(HttpResponse response, String keyName) {

        try {
            keyName = keyName;
            String responseBody = (String) response.body();
            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
            return (JSONArray) jsonObject.get(keyName);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public RandomRace() {


        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://www.dnd5eapi.co/api/races/"))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response =
                null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONArray givenList = getArrayObjects(response, key);
        Random rand = new Random();
        int randomElement = rand.nextInt(givenList.size());
        JSONObject url = (JSONObject) givenList.get(randomElement);
        String raceName = (String) url.get("url");
        String uri = "https://www.dnd5eapi.co" + raceName;

        request = HttpRequest
                .newBuilder()
                .uri(URI.create(uri))
                .build();

        try {
            response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String responseBody = response.body();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(responseBody);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Your character will be a " + jsonObject.get("name"));

    }
}

