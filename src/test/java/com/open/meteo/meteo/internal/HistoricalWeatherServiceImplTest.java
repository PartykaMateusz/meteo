package com.open.meteo.meteo.internal;

import com.open.meteo.feign.OpenMeteoFeign;
import com.open.meteo.feign.OpenMeteoResponse;
import com.open.meteo.meteo.HistoricalWeatherService;
import com.open.meteo.meteo.internal.dto.WeatherDataDto;
import com.open.meteo.meteo.internal.dto.WeatherResponse;
import com.open.meteo.meteo.internal.repository.DailyWeatherRepository;
import com.open.meteo.meteo.internal.repository.WeatherDataRequestLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class HistoricalWeatherServiceImplTest {

    private HistoricalWeatherService historicalWeatherService;

    @Mock
    private OpenMeteoFeign openMeteoFeign;

    @Mock
    private ResponseMapper responseMapper;

    @Mock
    private WeatherDataRequestLogRepository weatherDataRequestLogRepository;

    @Mock
    private DailyWeatherRepository dailyWeatherRepository;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        historicalWeatherService
                = new HistoricalWeatherServiceImpl(openMeteoFeign, responseMapper, weatherDataRequestLogRepository, dailyWeatherRepository);
    }

    @Test
    void testGetLastWeekWeather() {
        // given
        String latitude = "42.0";
        String longitude = "24.0";
        LocalDate from = LocalDate.now().minusDays(7);
        LocalDate to = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedFrom = from.format(formatter);
        String formattedTo = to.format(formatter);

        OpenMeteoResponse mockOpenMeteoResponse = new OpenMeteoResponse();
        when(openMeteoFeign.getHistoricalWeather(latitude, longitude, formattedFrom, formattedTo))
                .thenReturn(mockOpenMeteoResponse);

        WeatherResponse expectedWeatherResponse = new WeatherResponse();
        expectedWeatherResponse.setLatitude(latitude);
        expectedWeatherResponse.setLongitude(longitude);
        Map<LocalDate, WeatherDataDto> expectedWeatherMap = new TreeMap<>();


        expectedWeatherResponse.setWeather(expectedWeatherMap);
        when(responseMapper.map(mockOpenMeteoResponse)).thenReturn(expectedWeatherResponse);

        WeatherResponse weatherResponse = historicalWeatherService.getAndSaveLastWeekWeather(latitude, longitude);

        assertEquals(latitude, weatherResponse.getLatitude());
        assertEquals(longitude, weatherResponse.getLongitude());
        assertEquals(expectedWeatherMap, weatherResponse.getWeather());
    }

}