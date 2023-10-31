package tn.esprit.tunisair.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.DTO.FormateurDto;
import tn.esprit.tunisair.Repository.FormateurRepository;
import tn.esprit.tunisair.Repository.FormationRepository;
import tn.esprit.tunisair.Repository.specialiteeRepository;
import tn.esprit.tunisair.entity.Formateur;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormateurServiceImpl implements FormateurService{


    @Autowired
    FormateurRepository formateurRepository;

    @Autowired
    FormationRepository formationRepository;
@Autowired
specialiteeRepository specialiteeRepository;










    @Override
    public FormateurDto save(FormateurDto formateurDto) {

        Formateur formateur=formateurDto.toentity(formateurDto);
        formateurRepository.save(formateur);
        FormateurDto DTOsaved=formateurDto.fromentity(formateur);
        return DTOsaved;

    }





    @Override
    //
    public FormateurDto recherch(Long id) {
        Optional<Formateur> optionalformateur =formateurRepository.findById(id);
        if (optionalformateur.isPresent()) {
            Formateur formateur=optionalformateur.get();
            return FormateurDto.fromentity(formateur);
        }
        else
        {
            return null;
        }}

    @Override
    public FormateurDto addformateur(FormateurDto b) {


        Formateur formateur = FormateurDto.toentity(b);

        Formateur savedformateur= formateurRepository.save(formateur);

        return FormateurDto.fromentity(savedformateur);
    }



















    @Override
    public void delete(Long id) {
        Formateur formateur = formateurRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
        formateurRepository.deleteById(id);
    }




    @Override
    public List<FormateurDto> findAll() {
        return formateurRepository.findAll()
                .stream()
                .map(FormateurDto::fromentity)
                .collect(Collectors.toList());
    }





}
