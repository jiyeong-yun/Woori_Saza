package project.woori_saza.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.Review;

@Data
@NoArgsConstructor
@ApiModel(value = "후기 작성 모델", description = "거래한 사람들의 후기를 작성한다.")
public class ReviewRequestDto {

    @ApiModelProperty(value = "후기 내용", example = "content")
    private String content;
    @ApiModelProperty(value = "후기 점수", example = "10")
    private int score;
    @ApiModelProperty(value = "후기 대상자", example = "hashssafy")
    private String toUser;
    @ApiModelProperty(value = "후기 작성자", example = "hashwoori")
    private String fromUser;

    @Builder
    public ReviewRequestDto(String content, int score, String toUser, String fromUser) {
        this.content = content;
        this.score = score;
        this.toUser = toUser;
        this.fromUser = fromUser;
    }

    public Review toEntity() {
        return Review.builder()
                .content(content)
                .score(score)
                .build();
    }

}