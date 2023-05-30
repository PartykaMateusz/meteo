package com.open.meteo.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Daily {

    private List<LocalDate> time;

    @JsonProperty("precipitation_sum")
    private List<Double> precipitationSum;

    final List<LocalDateTime> sunrise;

    final List<LocalDateTime> sunset;

}
