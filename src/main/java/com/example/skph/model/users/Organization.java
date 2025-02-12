//package com.example.skph.model.users;
//
//// Reprezentuje organizację w systemie.
//import com.example.skph.model.Resource;
//import com.example.skph.model.Task;
//import com.example.skph.model.User;
//import com.example.skph.model.enums.ResourceStatus;
//import jakarta.persistence.*;
//import jakarta.persistence.Entity;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.experimental.SuperBuilder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@SuperBuilder
//@Entity
//@Table(name = "organization")
//@DiscriminatorValue("organization")
//public class Organization extends User {
//
//    @OneToMany(mappedBy = "organization")
//    private List<Task> tasks;
//
//    @Getter
//    @OneToMany(mappedBy = "assignedOrganization")
//    private List<Resource> resources = new ArrayList<>();
//
//    @OneToOne(mappedBy = "organization")
//    private User user;
//
//    @Setter
//    @Getter
//    private String name; // Nazwa organizacji.
//
//    private String type; // Typ organizacji (np. NGO, fundacja, rządowa).
//
//    private String contactInfo; // Dane kontaktowe organizacji.
//
//
//}
package com.example.skph.model.users;

import com.example.skph.model.User;
import com.example.skph.model.Resource;
import com.example.skph.model.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "organization")            // Tabela "organization"
@PrimaryKeyJoinColumn(name = "id")       // Klucz główny taki sam jak w "users"
public class Organization extends User {

    // np. lista zadań przypisanych do organizacji
    @OneToMany(mappedBy = "organization")
    private List<Task> tasks;

    // Lista zasobów przypisanych do tej organizacji
    @Getter
    @OneToMany(mappedBy = "assignedOrganization")
    private List<Resource> resources = new ArrayList<>();

    /**
     *  Uwaga: to tworzy odwrotną relację 1:1 do pola
     *  "private Organization organization" w klasie User.
     *  Jeżeli faktycznie tego nie potrzebujesz, usuń to pole.
     */
    @OneToOne(mappedBy = "organization")
    private User user;

    @Setter
    @Getter
    private String name;        // nazwa organizacji
    private String type;        // typ organizacji (NGO, fundacja, itp.)
    private String contactInfo; // dane kontaktowe
}
