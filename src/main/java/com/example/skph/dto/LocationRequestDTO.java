package com.example.skph.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LocationRequestDTO {
    private String name;
    private int locationType;

    //private int pointType;
    //private int areaType;
    private Double latitude;
    private Double longitude;
    private List<List<Double>> Coordinates;
}
