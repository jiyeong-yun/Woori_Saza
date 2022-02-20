package project.woori_saza.model.service;


import project.woori_saza.model.dto.PartyDto;
import project.woori_saza.model.dto.PartyResponseDto;

import java.util.List;

public interface PartyService {

     List<PartyResponseDto> getPartyList(String id); //내 모든 파티리스트
     List<PartyDto> getDetailList(Long id); //파티 디테일 리스트
     void deleteParty(Long partyId); //삭제
     void finishParty(Long partyId); //파티 마감


}
