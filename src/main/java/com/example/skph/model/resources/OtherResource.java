package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.example.skph.model.enums.ResourceStatus;


@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "other_resources")
public class OtherResource extends Resource {

    private String description;

    public boolean isAvailable() {
        return getStatus() == ResourceStatus.AVAILABLE;
    }
}

