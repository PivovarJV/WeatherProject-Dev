package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GeoDTO {
    private String name;
    private double lat;
    private double lon;
    private String country;
    private String state;
}