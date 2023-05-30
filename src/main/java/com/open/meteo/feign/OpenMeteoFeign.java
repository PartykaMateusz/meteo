package com.open.meteo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@FeignClient(name = "${open-meteo.name}", url = "${open-meteo.url}")
public interface OpenMeteoFeign {


    @GetMapping("/archive?latitude={latitude}&longitude={longitude}&start_date={startDate}&end_date={endDate}&daily=precipitation_sum,sunrise,sunset&timezone=auto")
    OpenMeteoResponse getHistoricalWeather(@PathVariable String latitude,
                                           @PathVariable String longitude,
                                           @PathVariable String startDate,
                                           @PathVariable String endDate);

}
