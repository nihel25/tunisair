package tn.esprit.tunisair.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tunisair.entity.UserProfile;


@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
}
