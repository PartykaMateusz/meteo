package com.open.meteo.meteo;

import com.open.meteo.meteo.internal.dto.WeatherResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/historical-weather")
public class HistoricalWeatherController {

    private final HistoricalWeatherService historicatWeatherService;

    public HistoricalWeatherController(HistoricalWeatherService historicatWeatherService) {
        this.historicatWeatherService = historicatWeatherService;
    }

    @GetMapping("/lastWeek")
    public ResponseEntity<WeatherResponse> getHistoricalWeather(@RequestParam String latitude,
                                                                @RequestParam String longitude){
        return ResponseEntity.ok(historicatWeatherService.getLastWeekWeather(latitude, longitude));
    }
}
