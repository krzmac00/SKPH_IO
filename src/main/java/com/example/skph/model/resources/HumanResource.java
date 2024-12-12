package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.example.skph.model.enums.ResourceStatus;


@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "human_resources")
public class HumanResource extends Resource {

    private String role;

    private boolean availability;

    public boolean isAvailable() {
        return getStatus() == ResourceStatus.AVAILABLE && availability;
    }

    public void assignToTask() {
        if (isAvailable()) {
            availability = false;
            setStatus(ResourceStatus.IN_USE);
        } else {
            throw new IllegalStateException("Human resource is not available.");
        }
    }
}
