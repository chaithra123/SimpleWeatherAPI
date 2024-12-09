package com.weather.service;

import com.weather.exception.CityNotFoundException;
import com.weather.model.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        weatherService = new WeatherService();
    }

    @Test
    void addOrUpdateWeather_ShouldAddWeather() {
        Weather weather = new Weather();
        weather.setCity("Auckland");
        weather.setTemp("24");
        weather.setUnit("C");
        weather.setDate("2023-12-10");
        weather.setWeather("cloudy");

        weatherService.addOrUpdateWeather(weather);

        Weather result = weatherService.getWeather("Auckland");
        assertNotNull(result);
        assertEquals("Auckland", result.getCity());
        assertEquals("24", result.getTemp());
    }

    @Test
    void getWeather_ShouldThrowException_WhenCityNotFound() {
        assertThrows(CityNotFoundException.class, () -> weatherService.getWeather("NonExistentCity"));
    }

    @Test
    void getAllWeather_ShouldReturnAllWeatherData() {
        Weather weather1 = new Weather();
        weather1.setCity("Auckland");
        weather1.setTemp("24");
        weather1.setUnit("C");
        weather1.setDate("2023-12-10");
        weather1.setWeather("cloudy");

        Weather weather2 = new Weather();
        weather2.setCity("Wellington");
        weather2.setTemp("18");
        weather2.setUnit("C");
        weather2.setDate("2023-12-10");
        weather2.setWeather("rainy");

        weatherService.addOrUpdateWeather(weather1);
        weatherService.addOrUpdateWeather(weather2);

        List<Weather> weatherList = weatherService.getAllWeather();
        assertEquals(2, weatherList.size());
    }
}
