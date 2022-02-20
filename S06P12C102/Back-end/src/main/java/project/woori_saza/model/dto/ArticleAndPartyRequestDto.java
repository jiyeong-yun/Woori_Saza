package project.woori_saza.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.Category;

import javax.persistence.ElementCollection;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "게시글과 파티 정보 생성", description = "게시글과 파티의 상세 정보를 나타낸다")
public class ArticleAndPartyRequestDto {
    // for article
    @ApiModelProperty(value = "작성자 아이디", example = "hashedProfile")
    private String profileId;
    @ApiModelProperty(value = "게시글 제목", example = "title")
    private String title;
    @ApiModelProperty(value = "게시글 내용", example = "content")
    private String content;
    @ApiModelProperty(value = "공동 구매 링크", example = "http://coupang.com/product/1")
    private String link;
    @ApiModelProperty(value = "물품 항목", example = "FOOD")
    private Category category;
    @ElementCollection
    @ApiModelProperty(value = "사진 리스트")
    private List<String> pic;

    // for party
    @ApiModelProperty(value = "파티 만료 기간", example = "2022-02-01-23:59:59")
    private String deadline;
    @ApiModelProperty(value = "상품 이름", example = "product name")
    private String product;

    @Min(value=0)
    @ApiModelProperty(value = "상품 총 가격", example = "10000")
    private Integer totalPrice;
    @Min(value=1)
    @ApiModelProperty(value = "상품 총 개수", example = "24")
    private Integer totalProductCount;
    @Min(value=2)
    @ApiModelProperty(value = "파티 총 인원", example = "4")
    private Integer totalRecruitMember;
    @ApiModelProperty(value = "위약금", example = "50")
    private Integer penalty;

    //for memberinfo
    @Min(value=1)
    @ApiModelProperty(value = "내가 선택한 수량", example = "2")
    private Integer amount;

}
