package tn.esprit.tunisair.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.tunisair.entity.Stagiaire;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StagiaireDTO {
	private Long id;
	@NotNull(message="it should be not null")
	private String prenom;
	@NotNull(message="it should be not null")
	private String nom;
    @Pattern(regexp="^[0-9]{8}$",message="it should be a number ")
	private String telephone;

	private String email;
	@NotNull(message="it should be not null")
	private String nomUniv;
	@NotNull(message="it should be not null")
	private StageDTO stagedto;

	@NotNull(message="it should be not null")
	private String cin;

	@NotNull(message="it should be not null")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date datenaissance;

	public static Stagiaire toEntity(StagiaireDTO dto) {
		return Stagiaire.builder()


.id(dto.getId())


				.telephone(dto.getTelephone())
				.email(dto.getEmail())
				.nomUniv(dto.getNomUniv())
				.nom(dto.getNom())
				.prenom(dto.getPrenom())
				.stage(StageDTO.toEntity(dto.getStagedto()))
				.cin(dto.getCin())
				.datenaissance(dto.getDatenaissance())
				.build();
	}








	public static StagiaireDTO fromEntity(Stagiaire stage){

		return StagiaireDTO.builder()
				.id(stage.getId())
				.cin(stage.getCin())
				.datenaissance(stage.getDatenaissance())
				.telephone(stage.getTelephone())
				.email(stage.getEmail())
				.nomUniv(stage.getNomUniv())
				.nom(stage.getNom())
				.prenom(stage.getPrenom())
				.stagedto(StageDTO.fromEntity(stage.getStage()))
				.build();
	}

}
