//package com.example.skph.model;
//
//// Reprezentuje użytkownika w systemie.
//import com.example.skph.model.enums.UserRole;
//import com.example.skph.model.users.Organization;
//import jakarta.persistence.*;
//import jakarta.persistence.Entity;
//import lombok.*;
//import lombok.experimental.SuperBuilder;
//
//@Entity
//@Data
//@NoArgsConstructor
//@SuperBuilder
//@Table(name = "user")
//@Inheritance(strategy = InheritanceType.JOINED)
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id; // Identyfikator użytkownika.
//
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String contactNumber;
//
//    @Setter
//    private String username; // Nazwa użytkownika
//    @Getter
//    private String password;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private Organization organization;
//
//    @Enumerated(EnumType.STRING)
//    private UserRole role; // Rola w systemie
//
//
//    public User(String firstName, String lastName, String email, String contactNumber) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.contactNumber = contactNumber;
//    }
//
//    public User(String firstName,
//                String lastName,
//                String username,
//                String password,
//                UserRole role,
//                String email,
//                Organization organization) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.username = username;
//        this.password = password;
//        this.role = role;
//        this.email = email;
//        this.organization = organization;
//    }
//}


package com.example.skph.model;

import com.example.skph.model.enums.UserRole;
import com.example.skph.model.users.Organization;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users") // <- przyjmując, że tak się nazywa tabela
@Data
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;              // PK

    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    // =========================
    //   K O N S T R U K T O R
    // =========================
    public User(String firstName,
                String lastName,
                String username,
                String password,
                UserRole role,
                String email,
                Organization organization) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.organization = organization;
    }
}
