package de.gedoplan.demo.cachingrest.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "DEMOUSER")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(min = 3)
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

}
