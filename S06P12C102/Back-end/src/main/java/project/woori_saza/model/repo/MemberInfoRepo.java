package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.MemberInfo;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.domain.UserProfile;

import java.util.List;

public interface MemberInfoRepo extends JpaRepository<MemberInfo,Long> {

    List<MemberInfo> findAllByUserProfileOrderByPartyDesc(UserProfile userProfile); //userprofile아이디로 찾기
    List<MemberInfo> findAllByUserProfile(UserProfile userProfile);
    List<MemberInfo> findAllByParty(Party party);

}
