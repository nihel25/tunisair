package tn.esprit.tunisair.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Encadreur;

@Repository
public interface EncadreurRepository  extends JpaRepository<Encadreur, Long> {
}
