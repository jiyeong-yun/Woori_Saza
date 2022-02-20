package project.woori_saza.model.service;

import project.woori_saza.model.dto.CommentRequestDto;
import project.woori_saza.model.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {

    List<CommentResponseDto> getCommentList(Long articleId);
    List<CommentResponseDto> getMyCommentList(String profileId);
    void insertComment(CommentRequestDto commentRequestDto);
    void updateComment(CommentRequestDto commentRequestDto);
    void deleteComment(Long commentId);

}
