package project.woori_saza.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.MemberInfo;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.domain.UserProfile;

import java.time.LocalDateTime;

//파티 상세정보에 보여줄 dto
@Data
@NoArgsConstructor
@AllArgsConstructor
 public class PartyDto {

    //article
    @ApiModelProperty(value = "게시판 아이디", example = "1", required = true)
    private Long articleId; //게시판아이디

    //userProfile
    @ApiModelProperty(value = "프로필 아이디", example = "hashwoori", required = true)
    private String profileId; //프로필아이디
    @ApiModelProperty(value = "프로필 주소", example = "광주 문흥동", required = true)
    private String address; //주소
    @ApiModelProperty(value = "프로필 닉네임", example = "박상우아님", required = true)
    private String nickname; //닉네임
    @ApiModelProperty(value = "사용자 가입일", example = "2022-02-01-23:59:59", required = true)
    private LocalDateTime joinDate; //가입일
    @ApiModelProperty(value = "사용자 사진", example = "url", required = true)
    private String pic; //사진
    @ApiModelProperty(value = "사용자 후기 점수", example = "100", required = true)
    private Integer score; //점수

    //party
    @ApiModelProperty(value = "파티 번호", example = "1", required = true)
    private Long id; //파티 번호
    @ApiModelProperty(value = "파티관련 게시글 제목", example = "삼다수 팝니다", required = true)
    private String title; //제목
    @ApiModelProperty(value = "파티 상품", example = "삼다수", required = true)
    private String product; //상품
    @ApiModelProperty(value = "파티 마감 기한일", example = "2022-02-01-23:59:59", required = true)
    private LocalDateTime deadline; //진행기간
    @ApiModelProperty(value = "파티 마감여부", example = "false", required = true)
    private Boolean isClosed; //파티 마감 여부

    //memberinfo
    @ApiModelProperty(value = "파티장 여부", example = "false", required = true)
    private Boolean isBoss; //파티장 여부
 //   private Integer totalcount; //내가 선택한 인분 => front가 알아서 계산할듯
    @ApiModelProperty(value = "해당 파티 내가 선택한 인분", example = "2", required = true)
    private Integer totalamount; // 내가 선택 총 수량
    @ApiModelProperty(value = "해당 파티 내 결제 금액", example = "5000", required = true)
    private Integer price; // 결제금액
    @ApiModelProperty(value = "사용자 구매 확정 여부", example = "false", required = true)
    private Boolean isConfirmed; //파티원 구매확정여부

    public PartyDto(UserProfile userProfile,Party party, MemberInfo memberInfo){
        //==userprofile==//
        articleId=party.getArticle().getId();
        //==userprofile==//
        address=userProfile.getAddress();
        nickname=userProfile.getNickname();
        joinDate=userProfile.getJoinDate();
        pic=userProfile.getPic();
        score=userProfile.getScore();
        //==party==//
        id=party.getId();
        title=party.getArticle().getTitle();
        product=party.getProduct();
        deadline=party.getDeadline();
        isClosed=party.getIsClosed();
        //==memberinfo==//
        profileId=memberInfo.getUserProfile().getId();
        isBoss=memberInfo.getIsBoss();
        totalamount=memberInfo.getAmount();
        isConfirmed=memberInfo.getIsConfirmed();
        price=memberInfo.getPrice();

    }

}