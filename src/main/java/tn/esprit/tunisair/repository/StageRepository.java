package tn.esprit.tunisair.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Stage;

@Repository
public interface StageRepository extends JpaRepository<Stage,Long> {



}
