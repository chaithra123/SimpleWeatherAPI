package com.weather.controller;

import com.weather.model.Weather;
import com.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
@Validated
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<Weather> getWeather(@PathVariable String city) {
        Weather weather = weatherService.getWeather(city);
        return ResponseEntity.ok(weather);
    }

    @PostMapping
    public ResponseEntity<String> addOrUpdateWeather(@RequestBody Weather weather) {
        weatherService.addOrUpdateWeather(weather);
        return ResponseEntity.status(HttpStatus.CREATED).body("Weather data added/updated successfully.");
    }

    @GetMapping
    public ResponseEntity<List<Weather>> getAllWeather() {
        List<Weather> weatherList = weatherService.getAllWeather();
        return ResponseEntity.ok(weatherList);
    }
}
