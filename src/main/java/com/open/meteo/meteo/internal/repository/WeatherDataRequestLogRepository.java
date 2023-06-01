package com.open.meteo.meteo.internal.repository;

import com.open.meteo.meteo.internal.enity.WeatherDataRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRequestLogRepository extends JpaRepository<WeatherDataRequestLog, Long> {
}
