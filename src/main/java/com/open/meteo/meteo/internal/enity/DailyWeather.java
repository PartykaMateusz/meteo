package com.open.meteo.meteo.internal.enity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "daily_weather")
@Data
@NoArgsConstructor
public class DailyWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Double averagePrecipitation;

    private LocalTime sunrise;

    private LocalTime sunset;
}
