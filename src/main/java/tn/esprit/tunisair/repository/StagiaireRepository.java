package tn.esprit.tunisair.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Stagiaire;

@Repository
public interface StagiaireRepository extends JpaRepository<Stagiaire,Long> {




}
