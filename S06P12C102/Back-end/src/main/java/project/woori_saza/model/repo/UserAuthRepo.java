package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.UserAuth;

public interface UserAuthRepo extends JpaRepository<UserAuth,String> {


}
