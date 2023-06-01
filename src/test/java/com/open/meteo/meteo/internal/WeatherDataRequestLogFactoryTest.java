package com.open.meteo.meteo.internal;

import com.open.meteo.meteo.internal.dto.WeatherResponse;
import com.open.meteo.meteo.internal.enity.DailyWeather;
import com.open.meteo.meteo.internal.enity.WeatherDataRequestLog;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class WeatherDataRequestLogFactoryTest {

    @Test
    void testCreate() {
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setLongitude("40.0");
        weatherResponse.setLatitude("20.0");
        List<DailyWeather> dailyWeather = new ArrayList<>();

        WeatherDataRequestLog requestLog = WeatherDataRequestLogFactory.create(weatherResponse, dailyWeather);

        assertEquals(weatherResponse.getLatitude(), requestLog.getLatitude());
        assertEquals(weatherResponse.getLongitude(), requestLog.getLongitude());
    }
}