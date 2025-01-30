package com.example.skph.DTO;//package com.example.skph.DTO;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class RequestDTO {
//    private long id;
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
//    private String address;
//    private List<TaskDTO> tasks;
//
//    public RequestDTO(long id, LocalDateTime startDate, LocalDateTime endDate, String address, List<TaskDTO> tasks) {
//        this.id = id;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.address = address;
//        this.tasks = tasks;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public LocalDateTime getStartDate() {
//        return startDate;
//    }
//
//    public LocalDateTime getEndDate() {
//        return endDate;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public List<TaskDTO> getTasks() {
//        return tasks;
//    }
//}

//package com.example.skph.DTO;
//
//import java.time.LocalDateTime;
//
//public class RequestDTO {
//    private Long requestId;
//    private String requesterFullName;
//    private String address;
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
//
//    // Zasoby i dane związane z zasobami
//    private String resourceName;
//    private int resourceAmount;
//    private boolean resourceToGive;
//
//    public RequestDTO(Long requestId, String requesterFullName, String address, LocalDateTime startDate, LocalDateTime endDate) {
//        this.requestId = requestId;
//        this.requesterFullName = requesterFullName;
//        this.address = address;
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }
//
//    // Gettery i settery
//
//    public Long getRequestId() {
//        return requestId;
//    }
//
//    public void setRequestId(Long requestId) {
//        this.requestId = requestId;
//    }
//
//    public String getRequesterFullName() {
//        return requesterFullName;
//    }
//
//    public void setRequesterFullName(String requesterFullName) {
//        this.requesterFullName = requesterFullName;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public LocalDateTime getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDateTime startDate) {
//        this.startDate = startDate;
//    }
//
//    public LocalDateTime getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDateTime endDate) {
//        this.endDate = endDate;
//    }
//
//    public String getResourceName() {
//        return resourceName;
//    }
//
//    public void setResourceName(String resourceName) {
//        this.resourceName = resourceName;
//    }
//
//    public int getResourceAmount() {
//        return resourceAmount;
//    }
//
//    public void setResourceAmount(int resourceAmount) {
//        this.resourceAmount = resourceAmount;
//    }
//
//    public boolean isResourceToGive() {
//        return resourceToGive;
//    }
//
//    public void setResourceToGive(boolean resourceToGive) {
//        this.resourceToGive = resourceToGive;
//    }
//}



import com.example.skph.DTO.ResourceDTO;
//import com.example.skph.DTO.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private Long id;
    private String requesterFullName;
    private String address;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isAccomplished;
    private List<ResourceDTO> resources; // Nowe pole na zasoby
//    private List<TaskDTO> tasks;  // Nowe pole
}

