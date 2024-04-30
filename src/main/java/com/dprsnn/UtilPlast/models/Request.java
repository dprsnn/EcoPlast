package com.dprsnn.UtilPlast.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String amount;
    private String comment;
    private String requestCode;
    private String status;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Person person;

    @OneToOne
    private PlasticCategory plasticCategory;

    @OneToOne
    private PointAddress pointAdress;

}
