package project.woori_saza.model.service;

import project.woori_saza.model.dto.MemberInfoRequestDto;

public interface MemberInfoService {

    void insertMemberInfo(MemberInfoRequestDto memberInfoRequestDto);
    void deleteMemberInfo(Long partyId,String profileId);
    void confirmMemberInfo(Long partyId,String profileId);

}
