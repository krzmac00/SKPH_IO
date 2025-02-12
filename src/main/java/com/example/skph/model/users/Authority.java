//package com.example.skph.model.users;
//
//// Reprezentuje przedstawiciela władz w systemie.
//// Dziedziczy właściwości i metody z klasy bazowej User.
//import jakarta.persistence.*;
//import lombok.NoArgsConstructor;
//
//
//@NoArgsConstructor
//@Entity
//public class Authority extends Organization {
//
//}



package com.example.skph.model.users;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "authority")        // Tabela "authority"
@PrimaryKeyJoinColumn(name = "id")
public class Authority extends Organization {

    // ewentualne pola i metody ...
}
