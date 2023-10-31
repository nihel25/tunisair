package tn.esprit.tunisair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Personnel;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {

}

