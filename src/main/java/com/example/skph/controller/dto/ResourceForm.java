package com.example.skph.controller.dto;

import com.example.skph.model.enums.PhysicalResourceType;
import com.example.skph.model.enums.ResourceStatus;
import com.example.skph.model.enums.ResourceType;
import com.example.skph.model.enums.TransportType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResourceForm {
    private String name;
    private int amount;
    private boolean toGive;
    private ResourceStatus status;
    private ResourceType resourceType;

    private PhysicalResourceType physicalType;

    private BigDecimal financialValue;
    private String currency;

    private String humanRole;
    private boolean availability;

    private int capacity;
    private TransportType transportType;

    private String description;
}
