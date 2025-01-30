package com.example.skph.DTO;
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
    private List<ResourceDTO> resources;
//    private List<TaskDTO> tasks;
}

