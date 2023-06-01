package com.open.meteo.meteo.internal;

import com.open.meteo.meteo.HistoricalWeatherService;
import com.open.meteo.meteo.internal.dto.WeatherResponse;
import com.open.meteo.meteo.internal.enity.DailyWeather;
import com.open.meteo.meteo.internal.feign.OpenMeteoFeign;
import com.open.meteo.meteo.internal.repository.DailyWeatherRepository;
import com.open.meteo.meteo.internal.repository.WeatherDataRequestLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class HistoricalWeatherServiceImpl implements HistoricalWeatherService {

    private final OpenMeteoFeign openMeteoFeign;

    private final ResponseMapper responseMapper;

    private WeatherDataRequestLogRepository weatherDataRequestLogRepository;

    private DailyWeatherRepository dailyWeatherRepository;

    public HistoricalWeatherServiceImpl(OpenMeteoFeign openMeteoFeign,
                                        ResponseMapper responseMapper,
                                        WeatherDataRequestLogRepository weatherDataRequestLogRepository,
                                        DailyWeatherRepository dailyWeatherRepository) {
        this.openMeteoFeign = openMeteoFeign;
        this.responseMapper = responseMapper;
        this.weatherDataRequestLogRepository = weatherDataRequestLogRepository;
        this.dailyWeatherRepository = dailyWeatherRepository;
    }

    @Override
    public WeatherResponse getAndSaveLastWeekWeather(String latitude, String longitude) {
        LocalDate from = LocalDate.now().minusDays(6);
        LocalDate to = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        WeatherResponse weatherResponse =
                this.responseMapper
                        .map(openMeteoFeign.getHistoricalWeather(latitude, longitude, from.format(formatter), to.format(formatter)));

        List<DailyWeather> dailyWeathers = DailyWeatherFactory.create(weatherResponse.getWeather());
        dailyWeatherRepository.saveAll(dailyWeathers);
        weatherDataRequestLogRepository.save(WeatherDataRequestLogFactory.create(weatherResponse, dailyWeathers));

        return weatherResponse;
    }
}
