package project.woori_saza.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.Review;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {

    @ApiModelProperty(value = "후기 id", example = "1")
    private Long id;
    @ApiModelProperty(value = "후기 내용", example = "content")
    private String content;
    @ApiModelProperty(value = "후기 작성일", example = "2022-02-01-23:59:59")
    private LocalDateTime date;
    @ApiModelProperty(value = "후기 점수", example = "10")
    private int score;
    @ApiModelProperty(value = "후기 작성자", example = "hashssafy")
    private String fromUser;
    @ApiModelProperty(value = "후기 당사자", example = "hashssafy")
    private String ToUser;

    public ReviewResponseDto(Review review){
        id = review.getId();
        content = review.getContent();
        date = review.getDate();
        score = review.getScore();
        fromUser = review.getFromUser().getNickname();
        ToUser = review.getToUser().getNickname();
    }
}