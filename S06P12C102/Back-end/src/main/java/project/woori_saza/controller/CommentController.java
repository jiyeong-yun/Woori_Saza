package project.woori_saza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.dto.CommentRequestDto;
import project.woori_saza.model.dto.CommentResponseDto;
import project.woori_saza.model.service.ArticleService;
import project.woori_saza.model.service.CommentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@Api("댓글 컨트롤러")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    ArticleService articleService;

    @ApiOperation(value = "선택한 게시글 내의 댓글", notes = "선택한 게시글 내의 모든 댓글을 반환한다.")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getCommentList(@RequestParam("articleId") @ApiParam(value = "게시글 번호", required = true) Long articleId) {
        Map<String, Object> result = new HashMap<>();
        List<CommentResponseDto> commentList = null;
        HttpStatus status = null;
        try {
            commentList = commentService.getCommentList(articleId);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        result.put("commentList", commentList);

        return new ResponseEntity<>(result, status);
    }

    @ApiOperation(value = "마이사자 - 댓글 (내가 쓴 댓글)", notes = "마이사자의 '댓글' 창에 내가 쓴 모든 댓글을 반환한다.")
    @GetMapping("/{profileId}")
    public ResponseEntity<Map<String, Object>> getMyCommentList(@PathVariable("profileId")
                                                                @ApiParam(value = "내 프로필아이디", example = "hashwoori", required = true) String profileId) {

        Map<String, Object> result = new HashMap<>();
        List<CommentResponseDto> myCommentList = null;
        HttpStatus status = null;
        try {
            myCommentList = commentService.getMyCommentList(profileId);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        result.put("myCommentList",myCommentList);

        return new ResponseEntity<>(result, status);
    }

    @ApiOperation(value = "댓글 작성", notes = "게시글 내에 댓글을 작성한다.")
    @PostMapping
    public ResponseEntity<String> insertComment(@RequestBody @ApiParam(value = "댓글 작성 모델") CommentRequestDto commentRequestDto) {
        HttpStatus status = null;
        try {
            commentService.insertComment(commentRequestDto);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(status);
    }

    @ApiOperation(value = "댓글 수정", notes = "게시글 내의 내가 쓴 댓글을 수정한다.")
    @PutMapping
    public ResponseEntity<String> updateComment(@RequestBody @ApiParam(value = "댓글 수정 모델") CommentRequestDto commentRequestDto) {
        HttpStatus status = null;
        try {
            commentService.updateComment(commentRequestDto);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(status);
    }

    @ApiOperation(value = "댓글 삭제", notes = "게시글 내의 내가 쓴 댓글을 삭제한다.")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") @ApiParam(value = "댓글 번호", required = true) Long commentId) {
        HttpStatus status = null;
        try {
            commentService.deleteComment(commentId);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(status);
    }

}
