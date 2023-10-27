package tn.esprit.tunisair.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Stage;

@Repository
public interface StageRepository extends JpaRepository<Stage,Long> {


    @Query("select count(s) from Stage s ")

    int getnumberstage();

    @Query("select count(r) from Reclamation r join r.formation fr where fr.id =:idformation")

    int getnumberreclamation();



    @Query(value = "select count(s) from Stage s join s.encadreur en where en.id =:id",nativeQuery = true)

    int getnumberstagaireByencadreur();
}
