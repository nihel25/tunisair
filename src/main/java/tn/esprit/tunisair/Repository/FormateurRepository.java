package tn.esprit.tunisair.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Formateur;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long> {





}
