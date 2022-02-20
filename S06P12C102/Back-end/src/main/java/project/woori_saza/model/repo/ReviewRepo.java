package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.Review;
import project.woori_saza.model.domain.UserProfile;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    List<Review> findByToUserOrderByDateDesc(UserProfile userProfile);
    List<Review> findByFromUserOrderByDateDesc(UserProfile userProfile);
}