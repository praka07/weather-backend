package com.weather.controller;

import com.weather.service.IWeatherApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller
 * @author Prakash
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class WeatherApiController {

    @Autowired
    IWeatherApiService serviceObj;


    /**
     * Returns weather details by city name
     * @author Prakash
     */
    @GetMapping("/weather")
    public ResponseEntity<?> getWeatherByCityName(@RequestParam("city")  String city) {
        return serviceObj.getWeatherByCityName(city);
    }
}
