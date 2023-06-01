package com.open.meteo.meteo.internal.repository;

import com.open.meteo.meteo.internal.enity.DailyWeather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyWeatherRepository extends JpaRepository<DailyWeather, Long>  {
}
