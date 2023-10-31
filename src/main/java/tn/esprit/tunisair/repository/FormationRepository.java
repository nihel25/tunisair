package tn.esprit.tunisair.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Formation;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Long> {


    boolean existsByRef(String ref);




}
