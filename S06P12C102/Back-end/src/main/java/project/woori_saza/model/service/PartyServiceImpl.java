package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.*;
import project.woori_saza.model.dto.PartyDto;
import project.woori_saza.model.dto.PartyResponseDto;
import project.woori_saza.model.repo.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PartyServiceImpl implements PartyService{

    @Autowired
    PartyRepo partyRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    @Autowired
    MemberInfoRepo memberInfoRepo;

    @Autowired
    ArticleRepo articleRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    ZzimRepo zzimRepo;

    @Autowired
    ChatRoomJoinRepo chatRoomJoinRepo;

    @Autowired
    ChatRoomRepo chatRoomRepo;


    //    내 파티리스트 전체 조회
    @Override
    public List<PartyResponseDto> getPartyList(String id) {
        //1.받은 userProfile로 memberinfo찾기
        UserProfile userProfile=userProfileRepo.getById(id); //프로필 1개 나옴
        List<MemberInfo>memberInfos=memberInfoRepo.findAllByUserProfileOrderByPartyDesc(userProfile); //배열 3개일듯

        //2.그 memberInfos에 해당하는 파티들을 찾아서 parties에 넣어주기
        List<PartyResponseDto> parties=new ArrayList<>();


        for(MemberInfo memberInfo:memberInfos){
            Party party=memberInfo.getParty();
            PartyResponseDto partyResponseDto=new PartyResponseDto(party,memberInfo);
            parties.add(partyResponseDto);
        }
        return parties;
    }

    //파티리스트 디테일 리스트 조회
    @Override
    public List<PartyDto> getDetailList(Long id) {
        List<PartyDto>partyDtos=new ArrayList<>();
        //1.해당 파티 찾기
        Party party=partyRepo.getById(id);
        //2.파티에 해당하는 memberinfo 리스트찾기
        List<MemberInfo>memberInfos=memberInfoRepo.findAllByParty(party);
        //3.memberinfo에 해당하는 user정보를 찾고 dto에 넣어주기 + party +memberinfo
        for(MemberInfo memberInfo:memberInfos){
            UserProfile userProfile=memberInfo.getUserProfile();
            PartyDto partyDto=new PartyDto(userProfile,party,memberInfo);
            partyDtos.add(partyDto);
        }
        return partyDtos;
    }

    @Override
    @Transactional
    public void deleteParty(Long partyId) {
        Party party=partyRepo.getById(partyId); //파티 찾고

        List<MemberInfo>memberInfos=memberInfoRepo.findAllByParty(party);
        Article article=articleRepo.findByParty(party);

        for (MemberInfo memberInfo : memberInfos) {

            memberInfoRepo.deleteById(memberInfo.getId());
        }

        //파티원들 파티 삭제 시키기
        List<ChatRoom> chatRoom = chatRoomRepo.findChatRoomByArticleId(article.getId());
        List<ChatRoomJoin> allByChatRoom = chatRoomJoinRepo.findAllByChatRoom(chatRoom.get(0));
        for (ChatRoomJoin chatRoomJoin : allByChatRoom) {
            chatRoomJoinRepo.delete(chatRoomJoin);
        }

        List<Comment> comments=commentRepo.findByArticle(article);
        List<Zzim> Zzims=zzimRepo.findByArticle(article);

        for (Comment comment : comments) {
            commentRepo.deleteById(comment.getId());
        }
        for (Zzim zzim : Zzims) {
            zzimRepo.deleteById(zzim.getZzimId());
        }

        articleRepo.deleteById(article.getId());

        partyRepo.deleteById(partyId);
    }

    @Override
    @Transactional
    public void finishParty(Long partyId) {
        Party party=partyRepo.getById(partyId);
        party.setIsClosed(true);
        partyRepo.save(party);
    }

}
