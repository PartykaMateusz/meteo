package com.open.meteo.meteo;

import com.open.meteo.meteo.internal.dto.WeatherResponse;

public interface HistoricalWeatherService {

    WeatherResponse getAndSaveLastWeekWeather(String latitude, String longitude);
}
