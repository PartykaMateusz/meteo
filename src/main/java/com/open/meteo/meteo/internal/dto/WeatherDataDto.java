package com.open.meteo.meteo.internal.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherDataDto {

    private Double meanPrecipitation ;

    private LocalDateTime sunrise;

    private LocalDateTime sunset;
}
