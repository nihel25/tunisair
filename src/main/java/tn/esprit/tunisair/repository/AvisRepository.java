package tn.esprit.tunisair.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Avis;

@Repository
public interface AvisRepository extends JpaRepository<Avis,Long > {
}
