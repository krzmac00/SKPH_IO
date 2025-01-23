package com.example.skph.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String OrganisationName;

    public Organisation() {
    }
    public Organisation(String OrganisationName) {
        this.OrganisationName = OrganisationName;
    }

    public void appointVolunteer(Volunteer volunteer,Task task) {
        volunteer.addTask(task);
    }
}
