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
public class Session implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Session(Long id, String reference) {
        this.id = id;
        this.reference = reference;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;

    @ManyToOne

    private Formateur formateur;



    String reference;

    @ManyToOne
    @JsonIgnore
    private Formation formation;
    @ManyToOne
    @JsonIgnore
    private User user;


    @ManyToOne
    @JsonIgnore
    private Salle salle;




    @ManyToMany
    @JoinTable(
            name = "personnelsession",
            joinColumns = @JoinColumn(name = "sessonid"),
            inverseJoinColumns = @JoinColumn(name = "personnelid")
    )
    private List<Personnel> personnel;

    @OneToMany(mappedBy = "session")
    @JsonIgnore
    private List<Materiel> materiels;
}
