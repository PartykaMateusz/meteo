package com.open.meteo.meteo.internal.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class Daily {

    private List<LocalDate> time;

    @JsonProperty("precipitation_sum")
    private List<Double> precipitationSum;

    final List<LocalTime> sunrise;

    final List<LocalTime> sunset;

}
