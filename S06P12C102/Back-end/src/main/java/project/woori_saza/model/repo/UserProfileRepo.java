package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.UserAuth;
import project.woori_saza.model.domain.UserProfile;

public interface UserProfileRepo extends JpaRepository<UserProfile,String> {

    UserProfile findByUserAuth(UserAuth userAuth);

    UserProfile findByNickname(String Nickname);


}
