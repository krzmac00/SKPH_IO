package com.example.skph.model.victimRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="requester")
public class Requester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Getter
    private Long id;

    @NotNull
    @NonNull
    @Getter
    @Setter
    private String firstName;

    @NotNull
    @NonNull
    @Getter
    @Setter
    private String lastName;

    @OneToMany(mappedBy = "requester", cascade=CascadeType.ALL)
    private List<Request> requests;

    public @NonNull String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public Requester(@NonNull String firstName, @NonNull String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
