package com.shruthiventures.journalapp.service;

import com.shruthiventures.journalapp.api.response.WeatherResponse;
import com.shruthiventures.journalapp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String API_KEY;

    @Autowired
    AppCache appCache;

    @Autowired
    private  RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI=appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString()).replace("<api_key>",API_KEY ).replace("<city>",city);
        ResponseEntity<WeatherResponse> response=restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse body= response.getBody();
        return body;
    }
}
