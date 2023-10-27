package tn.esprit.tunisair.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Specialitee;


@Repository
public interface specialiteeRepository extends JpaRepository<Specialitee,Long> {
}
