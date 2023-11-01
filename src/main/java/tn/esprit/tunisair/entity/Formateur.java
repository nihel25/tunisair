package tn.esprit.tunisair.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Formateur  implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;

    @Column(unique = true)
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date debut;

    private Boolean disponible;
    @ManyToOne
    @JsonIgnore
    private Specialitee specialitee;

    @OneToMany(mappedBy = "formateur")
    @JsonIgnore
    private List<Formation> formation;


    @OneToMany(mappedBy = "formateur")
    @JsonIgnore
    private List<Session> sessions;


}
