package com.open.meteo.meteo.internal;

import com.open.meteo.meteo.internal.dto.WeatherDataDto;
import com.open.meteo.meteo.internal.enity.DailyWeather;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DailyWeatherFactory {

    private DailyWeatherFactory() {
    }

    public static List<DailyWeather> create(@NonNull final Map<LocalDate, WeatherDataDto> dailyMap) {
        List<DailyWeather> dailyWeathers = new ArrayList<>();

        for (Map.Entry<LocalDate, WeatherDataDto> entry : dailyMap.entrySet()) {
            LocalDate date = entry.getKey();
            WeatherDataDto weatherData = entry.getValue();

            DailyWeather dailyWeather = new DailyWeather();
            dailyWeather.setDate(date);
            dailyWeather.setSunset(weatherData.getSunset());
            dailyWeather.setSunrise(weatherData.getSunrise());
            dailyWeather.setAveragePrecipitation(weatherData.getAveragePrecipitation());
            dailyWeathers.add(dailyWeather);
        }

        return dailyWeathers;

    }
}
