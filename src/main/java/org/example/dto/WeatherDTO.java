package org.example.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class WeatherDTO {
    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private Sys sys;
    private String name;
    private int locationId;

    @Getter
    public static class Main {
        private int temp;
        private int feels_like;
        private int humidity;
    }

    @Getter
    public static class Coord {
        private double lon;
        private double lat;
    }

    @Getter
    public static class Sys {
        private String country;
    }

    @Getter
    public static class Weather {
        private String icon;
    }
}
