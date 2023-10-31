package tn.esprit.tunisair.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {


//@Query("SELECT  s FROM Session s where s.createdAt BETWEEN ?1 and ?2 ")
//    List<Session> findbydate(Date start, Date end);


}
