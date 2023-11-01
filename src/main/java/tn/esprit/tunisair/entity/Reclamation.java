package tn.esprit.tunisair.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Reclamation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeReclamation;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datereclamation = new Date(System.currentTimeMillis());


    @ManyToOne
    @JsonIgnore
    Formation formation;

    @ManyToOne
    @JsonIgnore
    private User user;

}
