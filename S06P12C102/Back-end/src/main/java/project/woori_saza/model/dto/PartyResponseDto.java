package project.woori_saza.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.MemberInfo;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.domain.Tag;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyResponseDto {
    @ApiModelProperty(value = "파티글 제목", example = "삼다수 팝니다", required = true)
    private String title; //제목
    @ApiModelProperty(value = "태그 이름", example = "거래완료", required = true)
    private Tag tag; //태그
    @ApiModelProperty(value = "파티장 유무", example = "false", required = true)
    private Boolean isBoss; //파티장/원 유무
    @ApiModelProperty(value = "파티 아이디", example = "1", required = true)
    private Long id; //파티 아이디
    @ApiModelProperty(value = "파티 마감일", example = "false", required = true)
    private LocalDateTime deadline; //마감일
    @ApiModelProperty(value = "해당 파티 총금액", example = "10000", required = true)
    private Integer totalPrice; //총금액
@ApiModelProperty(value = "파티 총 모집인원수", example = "4", required = true)
    private Integer totalRecruitMember; //총 모집인원수
    @ApiModelProperty(value = "파티 현재인원수", example = "1", required = true)
    private Integer currentRecruitMember; //현재 인원수
    @ApiModelProperty(value = "1인분 금액", example = "2500", required = true)
    private Integer myPrice; // 총 금액/현재 인원수

    public PartyResponseDto(Party party,MemberInfo memberInfo){
        title= party.getArticle() == null ? "원글이 삭제된 파티입니다." : party.getArticle().getTitle();
        //tag=party.getArticle().getTag();
        isBoss=memberInfo.getIsBoss();
        id=party.getId();
        deadline=party.getDeadline();
        totalPrice=party.getTotalPrice();
        totalRecruitMember=party.getTotalRecruitMember();
        currentRecruitMember=party.getCurrentRecruitMember();
        myPrice=party.getTotalPrice()/totalRecruitMember;
    }
}
