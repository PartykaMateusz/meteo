package com.open.meteo.meteo.internal;

import com.open.meteo.feign.Daily;
import com.open.meteo.feign.OpenMeteoResponse;
import com.open.meteo.meteo.internal.dto.WeatherDataDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ResponseMapperTest {

    @Mock
    private OpenMeteoResponse openMeteoResponse;

    @Mock
    private Daily daily;

    private ResponseMapper responseMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        responseMapper = new ResponseMapper();

        List<LocalDate> timeList = new ArrayList<>();
        List<Double> precipitationSumList = new ArrayList<>();
        List<LocalTime> sunriseList = new ArrayList<>();
        List<LocalTime> sunsetList = new ArrayList<>();

        timeList.add(LocalDate.of(2023, 6, 1));
        timeList.add(LocalDate.of(2023, 6, 2));

        precipitationSumList.add(10.0);
        precipitationSumList.add(5.0);

        sunriseList.add(LocalTime.of(6,0));
        sunriseList.add(LocalTime.of(5,45));

        sunsetList.add(LocalTime.of(20,0));
        sunsetList.add(LocalTime.of(20,15));

        when(daily.getTime()).thenReturn(timeList);
        when(daily.getPrecipitationSum()).thenReturn(precipitationSumList);
        when(daily.getSunrise()).thenReturn(sunriseList);
        when(daily.getSunset()).thenReturn(sunsetList);
        when(openMeteoResponse.getDaily()).thenReturn(daily);
        when(openMeteoResponse.getLatitude()).thenReturn("50.00");
        when(openMeteoResponse.getLongitude()).thenReturn("20.00");
    }

    @Test
    void testMapWeather() {
        Map<LocalDate, WeatherDataDto> weather = responseMapper.mapWeather(daily);

        assertEquals(2, weather.size());

        WeatherDataDto weatherDataDto1 = weather.get(LocalDate.of(2023, 6, 1));
        assertEquals(0.42, weatherDataDto1.getAveragePrecipitation());

        WeatherDataDto weatherDataDto2 = weather.get(LocalDate.of(2023, 6, 2));
        assertEquals(0.21, weatherDataDto2.getAveragePrecipitation());
    }

    @Test
    void testRound() {
        double roundedValue = responseMapper.round(3.14159);
        assertEquals(3.14, roundedValue);
    }

    @Test
    void testMap() {
        responseMapper.map(openMeteoResponse);
    }

}
