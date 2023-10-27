package tn.esprit.tunisair.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Demandeclient;

@Repository
public interface DemandeclientRepository extends JpaRepository<Demandeclient , Long> {
}
