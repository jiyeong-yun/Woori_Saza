package project.woori_saza.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.Qna;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "1:1 문의 관련 모델")
public class QnaDto {

    @ApiModelProperty(value = "문의 번호", example = "null", required = true)
    private Long id;
    @ApiModelProperty(value = "문의 카테고리", example = "category", required = true)
    private String category;
    @ApiModelProperty(value = "문의 내용", example = "content", required = true)
    private String content;
    @ApiModelProperty(value = "문의 제목", example = "title", required = true)
    private String title;
    @ApiModelProperty(value = "답변 내용(댓글)")
    private String comment;
    @ApiModelProperty(value = "문의 날짜 및 시간")
    private LocalDateTime date;
    @ElementCollection
    @ApiModelProperty(value = "문의 사진")
    private List<String> pic=new ArrayList<>();

    @ApiModelProperty(value = "문의자(나) 아이디", example = "hashwoori", required = true)
    private String profileId;

    public QnaDto(Qna qna){
        this.id = qna.getId();
        this.category = qna.getCategory();
        this.content = qna.getContent();
        this.title = qna.getTitle();
        this.date = qna.getDate();
        this.comment = qna.getComment();
        this.pic = qna.getPic();
        this.profileId = qna.getUserProfile().getId();
    }

}
