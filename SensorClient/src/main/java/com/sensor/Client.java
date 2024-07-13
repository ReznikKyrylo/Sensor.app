package com.sensor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        String sensorName = "Sensor 11";

        registerSensor(sensorName);

        Random random = new Random();

        double minTemperature = 0.0;
        double maxTemperature = 45.0;
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            sendMeasurement(random.nextDouble() * maxTemperature,
                    random.nextBoolean(),
                    sensorName);
        }
    }

    private static void registerSensor(String sensorName) {
        final String url = "http://localhost:9091/sensors/registration";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", sensorName);

        makePostRequestWithJSONData(url, jsonData);
    }

    private static void sendMeasurement(double value, boolean raining, String sensorName) {
        final String url = "http://localhost:9091/measurements/add";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", sensorName));

        makePostRequestWithJSONData(url, jsonData);
    }

    private static void makePostRequestWithJSONData(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> response = new HttpEntity<>(jsonData,headers);

        try{
            restTemplate.postForEntity(url, response, String.class);
            System.out.println("Success");
        } catch (HttpClientErrorException e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }

}