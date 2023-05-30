package com.open.meteo.feign;

import lombok.Data;

@Data
public class OpenMeteoResponse {

    private String latitude;

    private String longitude;

    private Daily daily;
}
