package com.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.model.Weather;
import com.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getWeather_ShouldReturnWeatherData() throws Exception {
        Weather weather = new Weather();
        weather.setCity("Auckland");
        weather.setTemp("24");
        weather.setUnit("C");
        weather.setDate("2023-12-10");
        weather.setWeather("cloudy");

        when(weatherService.getWeather("Auckland")).thenReturn(weather);

        mockMvc.perform(get("/weather/Auckland"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Auckland"))
                .andExpect(jsonPath("$.temp").value("24"))
                .andExpect(jsonPath("$.unit").value("C"))
                .andExpect(jsonPath("$.weather").value("cloudy"));

        verify(weatherService, times(1)).getWeather("Auckland");
    }

    @Test
    void addOrUpdateWeather_ShouldAddWeatherData() throws Exception {
        Weather weather = new Weather();
        weather.setCity("Auckland");
        weather.setTemp("24");
        weather.setUnit("C");
        weather.setDate("2023-12-10");
        weather.setWeather("cloudy");

        mockMvc.perform(post("/weather")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(weather)))
                .andExpect(status().isCreated());

        verify(weatherService, times(1)).addOrUpdateWeather(any(Weather.class));
    }

    @Test
    void getAllWeather_ShouldReturnAllWeatherData() throws Exception {
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

        when(weatherService.getAllWeather()).thenReturn(Arrays.asList(weather1, weather2));

        mockMvc.perform(get("/weather"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].city").value("Auckland"))
                .andExpect(jsonPath("$[1].city").value("Wellington"));

        verify(weatherService, times(1)).getAllWeather();
    }
}
