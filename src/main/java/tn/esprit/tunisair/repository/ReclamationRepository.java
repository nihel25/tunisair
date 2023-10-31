package tn.esprit.tunisair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Reclamation;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {


    //  long countByFormation_Id(Long formationId);






}
