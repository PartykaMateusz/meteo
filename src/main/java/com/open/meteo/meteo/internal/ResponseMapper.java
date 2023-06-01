package com.open.meteo.meteo.internal;

import com.open.meteo.feign.Daily;
import com.open.meteo.feign.OpenMeteoResponse;
import com.open.meteo.meteo.internal.dto.WeatherDataDto;
import com.open.meteo.meteo.internal.dto.WeatherResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Component
class ResponseMapper {

    public WeatherResponse map(@NonNull final OpenMeteoResponse openMeteoResponse) {
        final WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setLatitude(openMeteoResponse.getLatitude());
        weatherResponse.setLongitude(openMeteoResponse.getLongitude());
        weatherResponse.setWeather(this.mapWeather(openMeteoResponse.getDaily()));
        return weatherResponse;
    }

    protected Map<LocalDate, WeatherDataDto> mapWeather(@NonNull final Daily daily) {
        Map<LocalDate, WeatherDataDto> weather = new TreeMap<>();
        for (int i = 0; i < daily.getTime().size() ; i++) {
            WeatherDataDto weatherDataDto = new WeatherDataDto();
            weatherDataDto.setAveragePrecipitation(getAveragePrecipitation(daily, i));
            weatherDataDto.setSunrise(daily.getSunrise().get(i));
            weatherDataDto.setSunset(daily.getSunset().get(i));
            weather.put(daily.getTime().get(i), weatherDataDto);
        }
        return weather;
    }

    private Double getAveragePrecipitation(Daily daily, int i) {
        return round(Optional.ofNullable(daily.getPrecipitationSum().get(i)).orElse(0D) / 24);
    }

    protected Double round(double value) {
        return Math.round(value * 100) / 100.00;
    }

}
