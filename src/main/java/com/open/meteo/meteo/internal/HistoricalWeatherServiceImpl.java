package com.open.meteo.meteo.internal;

import com.open.meteo.feign.OpenMeteoFeign;
import com.open.meteo.meteo.HistoricalWeatherService;
import com.open.meteo.meteo.internal.dto.WeatherResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class HistoricalWeatherServiceImpl implements HistoricalWeatherService {

    private final OpenMeteoFeign openMeteoFeign;
    private final ResponseMapper responseMapper;

    public HistoricalWeatherServiceImpl(OpenMeteoFeign openMeteoFeign, ResponseMapper responseMapper) {
        this.openMeteoFeign = openMeteoFeign;
        this.responseMapper = responseMapper;
    }

    @Override
    public WeatherResponse getLastWeekWeather(String latitude, String longitude) {
        LocalDate from = LocalDate.now().minusDays(7);
        LocalDate to = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return this.responseMapper
                .map(openMeteoFeign.getHistoricalWeather(latitude, longitude, from.format(formatter), to.format(formatter)));
    }
}
