package project.woori_saza.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoRequestDto {
    @ApiModelProperty(value = "파티원 선택 수량", example = "1")
    private  Integer amount; //선택 수량
    @ApiModelProperty(value = "파티원 부담 금액", example = "50000")
    private  Integer price; //파티원 부담 금액
    @ApiModelProperty(value = "파티원 결제 방법", example = "카카오페이")
    private String paidMethod;
    @ApiModelProperty(value = "파티 아이디", example = "1")
    private  Long partyId; //파티 아이디
    @ApiModelProperty(value = "프로필 아이디", example = "hashssafy")
    private String profileId; //프로필 아이디

    public MemberInfoRequestDto(int amount,int price,String paidMethod,long partyId,String profileId){
        this.amount=amount;
        this.price=price;
        this.paidMethod=paidMethod;
        this.partyId=partyId;
        this.profileId=profileId;
    }

//    public MemberInfo toEntity(){
//        return MemberInfo.builder()
//                .amount(amount)
//                .price(price)
//                .partyId(partyId)
//                .profileId(profileId)
//    }

}
