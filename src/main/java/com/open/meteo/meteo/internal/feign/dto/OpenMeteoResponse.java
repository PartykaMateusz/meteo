package com.open.meteo.meteo.internal.feign.dto;

import com.open.meteo.meteo.internal.feign.dto.Daily;
import lombok.Data;

@Data
public class OpenMeteoResponse {

    private String latitude;

    private String longitude;

    private Daily daily;
}
