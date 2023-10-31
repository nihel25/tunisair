package tn.esprit.tunisair.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.esprit.tunisair.entity.Certificat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CertificatDTO {

    private Long id;

@NotNull

    private StagiaireDTO stagiairedto;
    @NotNull

    private EncadreurDTO encadreurDTO;



    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datejour = new Date(System.currentTimeMillis());
    public static CertificatDTO fromentity(Certificat certificat){


        return CertificatDTO.builder()
                .id(certificat.getId())
                .datejour(certificat.getDatejour())

                .dateDebut(certificat.getDateDebut())
                .dateFin(certificat.getDateFin())
                .encadreurDTO(EncadreurDTO.fromEntity(certificat.getEncadreur()))
                .stagiairedto(StagiaireDTO.fromEntity(certificat.getStagiaire()))
                .build();
    }


    public static Certificat toentity(CertificatDTO dto){


        return Certificat.builder()
                .id(dto.getId())
                .datejour(dto.getDatejour())

                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .encadreur(EncadreurDTO.toEntity(dto.getEncadreurDTO()))
                .stagiaire(StagiaireDTO.toEntity(dto.getStagiairedto()))
                .build();

    }

}
