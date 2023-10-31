package tn.esprit.tunisair.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Salle;

@Repository
public  interface SalleRepository extends JpaRepository<Salle,Long> {
}
