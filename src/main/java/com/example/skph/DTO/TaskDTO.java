////package com.example.skph.DTO;
////
////import java.util.List;
////
////public class TaskDTO {
////    private long id;
////    private String name;
////    private List<DayDTO> days;
//////
////    public TaskDTO(long id, String name, List<DayDTO> days) {
////        this.id = id;
////        this.name = name;
////        this.days = days;
////    }
////
////    public long getId() {
////        return id;
////    }
////
////    public String getName() {
////        return name;
////    }
////
////    public List<DayDTO> getDays() {
////        return days;
////    }
////}
//
//package com.example.skph.DTO;
//import com.example.skph.DTO.DayDTO;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TaskDTO {
//    private Long id;
//    private String resourceName;
//    private List<DayDTO> statusHistory;
//
//    public TaskDTO(Long id, String resourceName) {
//        this.id = id;
//        this.resourceName = resourceName;
//        this.statusHistory = new ArrayList<>();
//    }
//}
//


package com.example.skph.DTO;

import java.util.ArrayList;
import java.util.List;

public class TaskDTO {
    private Long id;
    private String resourceName;
    private List<DayDTO> statusHistory;

    public TaskDTO(Long id, String resourceName) {
        this.id = id;
        this.resourceName = resourceName;
        this.statusHistory = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public List<DayDTO> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<DayDTO> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public void addDay(DayDTO day) {
        this.statusHistory.add(day);
    }
}
