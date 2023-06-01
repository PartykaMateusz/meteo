package com.open.meteo.meteo.internal.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class WeatherDataDto {

    private Double averagePrecipitation;

    private LocalTime sunrise;

    private LocalTime sunset;
}
