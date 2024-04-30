package com.dprsnn.UtilPlast.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Vadym Hnatiuk
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 1, max = 30, message = "Name should be between 1 and 30 characters")
    private String name;

    @NotEmpty(message = "Surname should be not empty")
    @Size(min = 1, max = 30, message = "Surname should be between 1 and 30 characters")
    private String surname;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Column(columnDefinition = "text")
    private String email;

    @Column(columnDefinition = "text")
    @NotEmpty(message = "Password should not be empty")
    @Pattern(regexp = "\\S+", message = "Password should not contain spaces")
    private String password;

    @NotEmpty(message = "Phone number should be not empty")
    private String phoneNumber;


    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    private boolean enabled;

    private String role;

    private Long userIconId;
    private LocalDate lastActivity;
    private String resetToken;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "person")
    private List<Request> requestList = new ArrayList<>();
}
