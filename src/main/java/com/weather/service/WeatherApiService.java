package com.weather.service;

import org.springframework.http.ResponseEntity;

public interface WeatherApiService {
    ResponseEntity<?> getWeatherByCityName(String city);
}
