package tn.esprit.tunisair.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Materiel;
import tn.esprit.tunisair.entity.Session;

import java.util.List;

@Repository
public interface MaterielRepository extends JpaRepository<Materiel,Long> {

    List<Materiel> findBySession(Session session);
}
