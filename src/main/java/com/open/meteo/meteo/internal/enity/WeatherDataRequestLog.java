package com.open.meteo.meteo.internal.enity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "weather_data_request_log")
@Data
@NoArgsConstructor
public class WeatherDataRequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime requestDateTime;
    private String latitude;
    private String longitude;

    @OneToMany
    @JoinColumn(name = "weather_data_request_log_id")
    private List<DailyWeather> dayInfo;
}
