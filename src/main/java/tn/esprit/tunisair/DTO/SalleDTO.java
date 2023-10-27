package tn.esprit.tunisair.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tn.esprit.tunisair.entity.Salle;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalleDTO {
    private Long id;
    @NotNull
    private String nomsalle;
    @NotNull
    private Long nombreplace;
@JsonIgnore
    private  UserDTO userDTO;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    private boolean isSalleDisponible(SalleDTO salleDTO) {
        return salleDTO.isStatut();  // Renvoie true si la salle est disponible (statut est true)
    }
    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }
    @NotNull
    private Boolean statut;
    public static SalleDTO fromEntity(Salle salle){
        return SalleDTO.builder()
                .id(salle.getId())
                .nomsalle(salle.getNomsalle())
                .nombreplace(salle.getNombreplace())
                .statut(salle.getStatut())
                .dateDebut(salle.getDateDebut())
                .dateFin(salle.getDateFin())
                .build();



    }


public static Salle toentity(SalleDTO dto){

        return Salle.builder()
                .id(dto.getId())
                .nomsalle((dto.getNomsalle()))
                .nombreplace(dto.getNombreplace())
                .statut((dto.getStatut()))
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .build();
}












}
