package com.example.skph.dto;

import com.example.skph.model.Location;
import com.example.skph.model.LocationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    private Long id;
    private String name;
    private LocationType locationType;
    private String coordinates;
    private String disasterArea;

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.name = location.getName();
        this.locationType = LocationType.fromValue(location.getLocationType().getValue());
        this.coordinates = location.getCoordinates() != null ? location.getCoordinates().toText() : null;
        this.disasterArea = location.getDisasterArea() != null ? location.getDisasterArea().toText() : null;
    }
}
//package com.example.skph.dto;
//
//import com.example.skph.model.AreaType;
//import com.example.skph.model.Location;
//import com.example.skph.model.LocationType;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class LocationDTO {
//    private Long id;
//    private String name;
//    private LocationType pointType;
//    private AreaType areaType;
//    private String coordinates;
//    private String area;
//
//    public LocationDTO(Location location) {
//        this.id = location.getId();
//        this.name = location.getName();
//        this.pointType = location.getPointType();
//        this.areaType = location.getAreaType();
//        this.coordinates = location.getCoordinates() != null ? location.getCoordinates().toText() : null;
//        this.area = location.getArea() != null ? location.getArea().toText() : null;
//    }
//}