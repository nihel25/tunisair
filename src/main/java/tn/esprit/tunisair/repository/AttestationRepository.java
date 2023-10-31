package tn.esprit.tunisair.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Attestation;

@Repository
public interface AttestationRepository extends JpaRepository<Attestation,Long> {
}
