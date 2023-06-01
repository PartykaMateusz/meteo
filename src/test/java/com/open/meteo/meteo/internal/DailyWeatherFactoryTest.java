package com.open.meteo.meteo.internal;

import com.open.meteo.meteo.internal.dto.WeatherDataDto;
import com.open.meteo.meteo.internal.enity.DailyWeather;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DailyWeatherFactoryTest {

    @Test
    void testCreate() {
        Map<LocalDate, WeatherDataDto> dailyMap = new TreeMap<>();
        LocalDate date1 = LocalDate.of(2023, 1, 1);
        LocalDate date2 = LocalDate.of(2023, 1, 2);
        WeatherDataDto weatherData1 = new WeatherDataDto();
        WeatherDataDto weatherData2 = new WeatherDataDto();
        weatherData1.setSunrise(LocalTime.of(6, 0));
        weatherData1.setSunset(LocalTime.of(18, 0));
        weatherData1.setAveragePrecipitation(5.0);
        weatherData2.setSunrise(LocalTime.of(6, 30));
        weatherData2.setSunset(LocalTime.of(17, 30));
        weatherData2.setAveragePrecipitation(2.5);
        dailyMap.put(date1, weatherData1);
        dailyMap.put(date2, weatherData2);

        List<DailyWeather> dailyWeathers = DailyWeatherFactory.create(dailyMap);

        assertEquals(2, dailyWeathers.size());

        DailyWeather dailyWeather1 = dailyWeathers.get(0);
        assertEquals(date1, dailyWeather1.getDate());
        assertEquals(LocalTime.of(6, 0), dailyWeather1.getSunrise());
        assertEquals(LocalTime.of(18, 0), dailyWeather1.getSunset());
        assertEquals(5.0, dailyWeather1.getAveragePrecipitation());

        DailyWeather dailyWeather2 = dailyWeathers.get(1);
        assertEquals(date2, dailyWeather2.getDate());
        assertEquals(LocalTime.of(6, 30), dailyWeather2.getSunrise());
        assertEquals(LocalTime.of(17, 30), dailyWeather2.getSunset());
        assertEquals(2.5, dailyWeather2.getAveragePrecipitation());
    }
}