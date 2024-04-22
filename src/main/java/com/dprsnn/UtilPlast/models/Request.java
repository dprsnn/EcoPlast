package com.dprsnn.UtilPlast.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double weight;
    private String phoneNumber;
    private String additionalInfo;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Person person;

    @OneToOne
    private PlasticCategory plasticCategory;

    @OneToOne
    private PointAddress pointAdress;

}
