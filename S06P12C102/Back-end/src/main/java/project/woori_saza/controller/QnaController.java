package project.woori_saza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.woori_saza.model.dto.QnaDto;
import project.woori_saza.model.service.QnaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/qna")
@Api("1:1문의 컨트롤러")
public class QnaController {

    @Autowired
    QnaService qnaService;

    @ApiOperation(value = "관리자가 보는 모든 1:1문의 리스트", notes = "관리자가 확인하는 문의 리스트")
    @GetMapping("/admin")
    public ResponseEntity<Map<String, Object>> getAllQnaList() {
        Map<String, Object> result = new HashMap<>();
        List<QnaDto> qnaList = null;
        HttpStatus status = null;
        try {
            qnaList = qnaService.getQnaList();
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        result.put("qnaList", qnaList);

        return new ResponseEntity<>(result, status);
    }

    @ApiOperation(value = "자신(회원)이 쓴 1:1문의 리스트", notes = "회원(작성자)가 확인하는 문의 리스트")
    @GetMapping("/{profileId}")
    public ResponseEntity<Map<String, Object>> getMyQnaList(@PathVariable
                                                                @ApiParam(value = "회원 아이디", example = "hashwoori") String profileId) {
        Map<String, Object> result = new HashMap<>();
        List<QnaDto> myQnaList = null;
        HttpStatus status = null;
        try {
            myQnaList = qnaService.getMyQnaList(profileId);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        result.put("myQnaList", myQnaList);

        return new ResponseEntity<>(result, status);
    }

    @ApiOperation(value = "선택한 문의글 내용", notes = "리스트에서 선택한 문의글의 내용")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getQnaDetail(@RequestParam
                                                            @ApiParam(value = "문의글 번호", example = "1") Long qnaId) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        QnaDto qnaDetail = null;
        try {
            qnaDetail = qnaService.getQnaDetail(qnaId);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        result.put("qnaDetail", qnaDetail);

        return new ResponseEntity<>(result, status);
    }

    @ApiOperation(value = "1:1 문의 작성", notes = "회원이 1:1 문의글을 작성한다.")
    @PostMapping
    public ResponseEntity<String> insertQna(@RequestBody @ApiParam(value = "1:1 문의 작성 모델") QnaDto qnaDto) {
        HttpStatus status = null;
        try {
            qnaService.insertQna(qnaDto);
            status = HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(status);
    }


    @ApiOperation(value = "회원 사진 업로드")
    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> register(@RequestPart(required = false) List<MultipartFile> uploadFiles) throws Exception {

        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            List<String> url=qnaService.upload(uploadFiles);
            status = HttpStatus.OK;
            result.put("url",url);
            result.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }



    @ApiOperation(value = "1:1 문의 수정", notes = "회원이 자신이 작성한 1:1 문의를 수정한다.")
    @PutMapping
    public ResponseEntity<String> updateQna(@RequestBody @ApiParam(value = "댓글 수정 모델") QnaDto qnaDto) {
        HttpStatus status = null;
        try {
            qnaService.updateQna(qnaDto);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(status);
    }

    @ApiOperation(value = "1:1 문의 삭제", notes = "작성자나 관리자는 문의를 삭제할 수 있다.")
    @DeleteMapping("/{qnaId}")
    public ResponseEntity<String> deleteQna(@PathVariable @ApiParam(value = "게시글 번호") Long qnaId) {
        HttpStatus status = null;
        try {
            qnaService.deleteQna(qnaId);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(status);
    }

    @ApiOperation(value = "관리자 답변 작성", notes = "관리자는 회원이 작성한 문의글에 답변을 작성한다.")
    @PutMapping("/admin")
    public ResponseEntity<String> insertQnaComment(@RequestBody @ApiParam(value = "1:1 문의 작성 모델") QnaDto qnaDto) {
        HttpStatus status = null;
        try {
            qnaService.insertQnaComment(qnaDto);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(status);
    }

}
