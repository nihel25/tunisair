package tn.esprit.tunisair.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Certificat;

@Repository
public interface CertificatRepository extends JpaRepository<Certificat,Long> {
}
