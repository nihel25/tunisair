package tn.esprit.tunisair.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.repository.FormationRepository;
import tn.esprit.tunisair.dto.FormateurDto;
import tn.esprit.tunisair.entity.Formateur;
import tn.esprit.tunisair.repository.FormateurRepository;
import tn.esprit.tunisair.repository.SpecialiteeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormateurServiceImpl implements FormateurService{



    @Autowired
    FormationRepository formationRepository;
@Autowired
SpecialiteeRepository specialiteeRepository;




    private final FormateurRepository formateurRepository;

    @Autowired
    public FormateurServiceImpl(FormateurRepository formateurRepository) {
        this.formateurRepository = formateurRepository;
    }





    @Override
    public FormateurDto save(FormateurDto formateurDto) {

        Formateur formateur=FormateurDto.toentity(formateurDto);
        formateurRepository.save(formateur);
       return FormateurDto.fromentity(formateur);


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
    public void delete(Long id) {
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
