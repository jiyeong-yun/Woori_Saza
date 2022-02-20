package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.Qna;
import project.woori_saza.model.domain.UserProfile;

import java.util.List;

public interface QnaRepo extends JpaRepository<Qna,Long> {

    List<Qna> findByUserProfile(UserProfile userProfile);

}
