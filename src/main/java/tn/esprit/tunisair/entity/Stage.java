package tn.esprit.tunisair.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stage  implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

    private String typeStage;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFinStage;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDebutStage;
    private Long duree;

    private String nomService;



    @ManyToOne
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "stage")
    @JsonIgnore


    private List<Stagiaire> stagiaires;

    @ManyToOne
    private Encadreur encadreur;



    }


