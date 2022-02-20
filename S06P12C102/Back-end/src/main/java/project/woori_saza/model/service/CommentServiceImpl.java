package project.woori_saza.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Comment;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.CommentRequestDto;
import project.woori_saza.model.dto.CommentResponseDto;
import project.woori_saza.model.repo.ArticleRepo;
import project.woori_saza.model.repo.CommentRepo;
import project.woori_saza.model.repo.UserProfileRepo;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    ArticleRepo articleRepo;
    @Autowired
    UserProfileRepo userProfileRepo;

    /**
     * 파티 내 댓글 리스트 조회
     */
    @Override
    public List<CommentResponseDto> getCommentList(Long articleId) {
        Article article = articleRepo.getById(articleId);
        List<Comment> comments = commentRepo.findByArticle(article);
        return comments.stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 내가 쓴 댓글 리스트 조회
     */
    @Override
    public List<CommentResponseDto> getMyCommentList(String profileId) {
        UserProfile user = userProfileRepo.getById(profileId);
        List<Comment> comments = commentRepo.findByUserProfileOrderByCreateAtDesc(user);
        return comments.stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 댓글 작성
     */
    @Override
    @Transactional
    public void insertComment(CommentRequestDto commentRequestDto) {
        Article article = articleRepo.getById(commentRequestDto.getArticleId());
        UserProfile user = userProfileRepo.getById(commentRequestDto.getProfileId());
        Comment comment = new Comment();
        comment.setContent(commentRequestDto.getContent());
        comment.setCreateAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime());
        comment.setArticle(article);
        comment.setUserProfile(user);
        commentRepo.save(comment);
    }

    /**
     * 댓글 수정
     */
    @Override
    @Transactional
    public void updateComment(CommentRequestDto commentRequestDto) {
        Comment comment = commentRepo.getById(commentRequestDto.getId());
        comment.setContent(commentRequestDto.getContent());
        comment.setCreateAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime());
        commentRepo.save(comment);
    }

    /**
     * 댓글 삭제
     */
    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepo.deleteById(commentId);
    }
}
