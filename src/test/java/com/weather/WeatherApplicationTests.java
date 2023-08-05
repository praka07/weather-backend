package com.weather;

import com.weather.service.IWeatherApiService;
import com.weather.service.WeatherApiService;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
class WeatherApplicationTests {

    @Test
    void contextLoads() {
    }

    @Mock
    private HttpGet getRequest;

    @Mock
    private CloseableHttpClient httpClient;
    @InjectMocks
    IWeatherApiService testWeatherApiService;
    @Bean
    public WeatherApiService weatherApiService() {
        return new IWeatherApiService();
    }
    @Autowired
    private WeatherApiService weatherApiService;

    @Test
    void testGetWeatherByCityName() {
        ResponseEntity<?> response = weatherApiService.getWeatherByCityName("Erode");
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    void testGetWeatherWithoutCityName() {
        ResponseEntity<?> response = weatherApiService.getWeatherByCityName("");
        String expectedResult="{\"cod\":\"400\",\"message\":\"Nothing to geocode\"}";
        assertEquals(expectedResult, response.getBody().toString());

    }

    @Test
    void testGetWeatherByNotAvailableCity() {
        ResponseEntity<?> response = weatherApiService.getWeatherByCityName("sy");
        String expectedResult="{\"cod\":\"404\",\"message\":\"city not found\"}";
        assertEquals(expectedResult, response.getBody().toString());

    }
}
