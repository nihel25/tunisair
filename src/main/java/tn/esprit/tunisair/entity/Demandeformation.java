package tn.esprit.tunisair.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Demandeformation  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long nbrpersonnelle;

    @ManyToMany
    @JoinTable(
            name = "demande_formation_personnel",
            joinColumns = @JoinColumn(name = "demande_formation_id"),
            inverseJoinColumns = @JoinColumn(name = "personnel_id")
    )
    private List<Personnel> personnel;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateCreation = new Date(System.currentTimeMillis());
private String valid;


    @ManyToOne
    private User user;



    @ManyToOne
    private Formation formation;
}
