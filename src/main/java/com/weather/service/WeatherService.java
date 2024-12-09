package com.weather.service;

import com.weather.exception.CityNotFoundException;
import com.weather.model.Weather;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WeatherService {

    private final Map<String, Weather> weatherData = new ConcurrentHashMap<>();

    public Weather getWeather(String city) {
        Weather weather = weatherData.get(city.toLowerCase());
        if (weather == null) {
            throw new CityNotFoundException("City not found: " + city);
        }
        return weather;
    }

    public void addOrUpdateWeather(Weather weather) {
        weatherData.put(weather.getCity().toLowerCase(), weather);
    }

    public List<Weather> getAllWeather() {
        return new ArrayList<>(weatherData.values());
    }
}
