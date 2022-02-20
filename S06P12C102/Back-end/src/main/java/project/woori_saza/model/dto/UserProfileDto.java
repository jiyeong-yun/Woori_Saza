package project.woori_saza.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.UserProfile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "사용자 프로필 정보", description = "로그인과 회원가입 등에 사용되는 사용자 프로필 정보")
public class UserProfileDto {

    @ApiModelProperty(value = "회원 가입시에는 서드파티 ID, 정보 전달시에는 프로필 ID", example = "12343123(3rd party ID) or hashedstring(profile ID)")
    private String id;

    @ApiModelProperty(value = "사용자 주소", example = "광주광역시 북구 용봉동")
    private String address;

    @ApiModelProperty(value = "사용자 닉네임", example = "nickname")
    private String nickname;

    @ApiModelProperty(value = "회원 가입 시간", example = "")
    private LocalDateTime joinDate;

    @ApiModelProperty(value = "사용자 사진 경로", example = "ftpServerUrl/pic.jpg")
    private String pic;

    @ApiModelProperty(value = "사용자 평가 점수", example = "100")
    private Integer score;

    @ApiModelProperty(value = "사용자 평가 횟수", example = "5")
    private Integer cnt;

    @ApiModelProperty(value = "관리자 여부", example = "true")
    private Boolean isAdmin;

    public UserProfileDto(UserProfile userProfile){
        this.id = userProfile.getId();
        this.address = userProfile.getAddress();
        this.nickname = userProfile.getNickname();
        this.joinDate = userProfile.getJoinDate();
        this.pic = userProfile.getPic();
        this.score = userProfile.getScore();
        this.cnt = userProfile.getCnt();
        this.isAdmin = userProfile.getUserAuth().getIsAdmin();
    }
}
