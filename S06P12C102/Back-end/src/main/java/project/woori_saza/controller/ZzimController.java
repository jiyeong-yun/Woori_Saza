package project.woori_saza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.dto.ZzimDto;
import project.woori_saza.model.service.ZzimService;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/zzim")
@Api("찜 컨트롤러")
public class ZzimController {

    @Autowired
    ZzimService zzimService;

    @ApiOperation(value = "찜 조회", notes = "파라미터 값에 따라 찜 또는 찜 목록을 반환한다", response = Map.class)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getZzim(@RequestParam(required = false) @ApiParam(value = "프로필 아이디") String profileId,
                                                       @RequestParam(required = false) @ApiParam(value = "게시글 번호") String articleId) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            if (profileId != null && articleId != null) {
                result.put("zzim", zzimService.getZzim(profileId, Long.parseLong(articleId)));
            } else if (profileId != null) {
                result.put("zzimList", zzimService.getZzimList(profileId));
            } else {
                result.put("zzimList", zzimService.getZzimList(Long.parseLong(articleId)));
            }
            status = HttpStatus.OK;
            result.put("success", true);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            status = HttpStatus.OK;
            result.put("success", true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);

        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @ApiOperation(value = "찜 등록", notes = "찜을 등록한다", response = Map.class)
    @PostMapping
    public ResponseEntity<Map<String, Object>> insertZzim(@RequestBody @ApiParam(value = "프로필과 게시글에 대한 정보") Map<String, String> body) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            ZzimDto zzim = zzimService.insertZzim(body.get("profileid"), Long.parseLong(body.get("articleid")));
            status = HttpStatus.OK;
            result.put("zzim", zzim);
            result.put("success", true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }

        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @ApiOperation(value = "찜 삭제", notes = "찜을 삭제한다", response = Map.class)
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteZzim(@RequestBody @ApiParam(value = "프로필과 게시글에 대한 정보") Map<String, String> body) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            zzimService.deleteZzim(body.get("profileid"), Long.parseLong(body.get("articleid")));
            status = HttpStatus.OK;
            result.put("success", true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }

        return new ResponseEntity<Map<String, Object>>(result, status);
    }
}
