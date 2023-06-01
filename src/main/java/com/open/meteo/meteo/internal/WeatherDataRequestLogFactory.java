package com.open.meteo.meteo.internal;

import com.open.meteo.meteo.internal.dto.WeatherResponse;
import com.open.meteo.meteo.internal.enity.DailyWeather;
import com.open.meteo.meteo.internal.enity.WeatherDataRequestLog;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

class WeatherDataRequestLogFactory {

    private WeatherDataRequestLogFactory() {
    }

    public static WeatherDataRequestLog create(WeatherResponse weatherResponse, List<DailyWeather> dailyWeather) {
        WeatherDataRequestLog requestLog = new WeatherDataRequestLog();
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime now = ZonedDateTime.now(zone);
        requestLog.setRequestDateTime(now.toLocalDateTime());
        requestLog.setLatitude(weatherResponse.getLatitude());
        requestLog.setLongitude(weatherResponse.getLongitude());
        requestLog.setDayInfo(dailyWeather);
        return requestLog;
    }
}
