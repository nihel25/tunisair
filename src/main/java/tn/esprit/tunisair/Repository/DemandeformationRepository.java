package tn.esprit.tunisair.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Demandeformation;

@Repository
public interface DemandeformationRepository extends JpaRepository<Demandeformation ,Long> {


}
