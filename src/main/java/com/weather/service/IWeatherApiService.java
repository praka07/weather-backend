package com.weather.service;

import com.weather.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IWeatherApiService implements WeatherApiService {

    @Value("${open.weather.app.uri}")
    private String weatherApiUri;
    @Value("${appid}")
    private String appId;

    /**
     *
     * @param city
     * @return weather details by city name
     */
    @Override
    @Cacheable(value="weather")
    public ResponseEntity<?> getWeatherByCityName(String city) {
        String weatherApiUrl = weatherApiUri + city + "&appid=" + appId;
        log.info("weatherApiUrl >--> {} ", weatherApiUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet(weatherApiUrl);
        try {
            HttpResponse response = httpClient.execute(getRequest);
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);
            log.info("response from API >--> {} ", apiOutput);
            return new ResponseEntity<>(apiOutput, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException();
        }
    }

}

