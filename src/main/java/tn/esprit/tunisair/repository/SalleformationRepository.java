package tn.esprit.tunisair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Salleformation;

@Repository
public interface SalleformationRepository extends JpaRepository<Salleformation,Long> {
}
