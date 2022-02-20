package project.woori_saza.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.Comment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "댓글 리턴 관련 모델")
public class CommentResponseDto {

    @ApiModelProperty(value = "댓글 번호", example = "null", required = true)
    private Long id;
    @ApiModelProperty(value = "댓글 내용", example = "testContent", required = true)
    private String content;
    @ApiModelProperty(value = "댓글 작성자 아이디", example = "hashwoori", required = true)
    private String profileId;
    @ApiModelProperty(value = "댓글을 작성한 시간", example = "2022-01-02", required = true)
    private LocalDateTime createAt;
    @ApiModelProperty(value = "댓글을 작성한 게시글 번호", example = "1", required = true)
    private Long articleId;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.profileId = comment.getUserProfile().getId();
        this.createAt = comment.getCreateAt();
        this.articleId = comment.getArticle().getId();
    }
}
