package com.open.meteo.meteo.internal.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class WeatherResponse {

    private String latitude;

    private String longitude;

    private Map<LocalDate, WeatherDataDto> weather;
}
