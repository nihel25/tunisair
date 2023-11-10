package tn.esprit.tunisair.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.tunisair.entity.Specialitee;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialiteeDTO {


    private Long id;
    @NotNull
    private String types;

    public static Specialitee toentity(SpecialiteeDTO dto) {

        if (dto == null) {
            return null; // Ou lancez une exception appropriée selon votre logique métier.
        }
        return Specialitee.builder()


                .id(dto.getId())


                .types(dto.getTypes())

                .build();
    }








    public static SpecialiteeDTO fromEntity(Specialitee sp){
        if (sp == null) {
            return null; // Ou lancez une exception appropriée selon votre logique métier.
        }
        return SpecialiteeDTO.builder()
                .id(sp.getId())

                .types(sp.getTypes())

                .build();
    }





















}
