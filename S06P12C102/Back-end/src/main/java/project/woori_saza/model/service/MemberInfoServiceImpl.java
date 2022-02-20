package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.*;
import project.woori_saza.model.dto.MemberInfoRequestDto;
import project.woori_saza.model.repo.*;

import java.util.List;

@Service
@Transactional
public class MemberInfoServiceImpl implements MemberInfoService{
    @Autowired
    PartyRepo partyRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    @Autowired
    MemberInfoRepo memberInfoRepo;

    @Autowired
    ChatRoomJoinRepo chatRoomJoinRepo;

    @Autowired
    ChatRoomService chatRoomService;

   @Autowired
   ChatRoomRepo chatRoomRepo;

   @Autowired
   ArticleRepo articleRepo;


    //회원이 신청서 작성
    @Override
    @Transactional
    public void insertMemberInfo(MemberInfoRequestDto memberInfoRequestDto) {
        //회원찾기
        UserProfile userProfile = userProfileRepo.getById(memberInfoRequestDto.getProfileId());
        //파티찾기
        Party party=partyRepo.getById(memberInfoRequestDto.getPartyId());
        //에러체크
        if(party.getTotalRecruitMember()<party.getCurrentRecruitMember()+memberInfoRequestDto.getAmount()){
            throw new RuntimeException("파티에 참가할 수 없습니다.");
        }
        //해당 파티 인원수 선택 수량만큼 + 해주기
        party.setCurrentRecruitMember(party.getCurrentRecruitMember()+memberInfoRequestDto.getAmount());

        partyRepo.save(party);
        //memberinfo에 저장
        MemberInfo memberInfo=MemberInfo.builder()
                .userProfile(userProfile)
                .party(party)
                .paidMethod(memberInfoRequestDto.getPaidMethod())
                .price(memberInfoRequestDto.getPrice())
                .amount(memberInfoRequestDto.getAmount())
                .build();
        memberInfoRepo.save(memberInfo);

        // 채팅방 입장
        List<ChatRoom> chatRoomList = chatRoomRepo.findChatRoomByArticleId(party.getArticle().getId());
        ChatRoom chatRoom = chatRoomList.get(0);
        chatRoom.setCount(chatRoom.getCount()+1);
        ChatRoomJoin chatRoomJoin = chatRoomService.createChatRoomJoin(chatRoom, userProfile);
        chatRoomJoinRepo.save(chatRoomJoin);
    }

    //참가 취소
    @Override
    public void deleteMemberInfo(Long partyId,String profileId) {
        UserProfile userProfile=userProfileRepo.getById(profileId);

        List<MemberInfo> memberInfos=memberInfoRepo.findAllByUserProfile(userProfile);
        Party party=partyRepo.getById(partyId);

        for (MemberInfo memberInfo : memberInfos) {
            if (memberInfo.getParty().getId() == partyId) {
                party.setCurrentRecruitMember(party.getCurrentRecruitMember() - memberInfo.getAmount());
                memberInfoRepo.deleteById(memberInfo.getId());
            }
        }


        // 채팅방 퇴장
        Article article = articleRepo.findByParty(party);
        List<ChatRoom> chatRoomList = chatRoomRepo.findChatRoomByArticleId(article.getId());
        chatRoomList.get(0).setCount(chatRoomList.get(0).getCount()-1);
        chatRoomJoinRepo.deleteChatRoomJoinByChatRoomAndUserProfile(chatRoomList.get(0), userProfile);

    }
    //멤버 구매확정여부
    @Override
    public void confirmMemberInfo(Long partyId,String profileId) {
        UserProfile userProfile=userProfileRepo.getById(profileId);

        List<MemberInfo> memberInfos=memberInfoRepo.findAllByUserProfile(userProfile);

        for (MemberInfo memberInfo : memberInfos) {
            if (memberInfo.getParty().getId() == partyId) {
                memberInfo.setIsConfirmed(true);
            }
        }
    }
}
