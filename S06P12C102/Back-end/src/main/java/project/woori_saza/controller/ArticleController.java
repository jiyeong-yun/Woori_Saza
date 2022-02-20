package project.woori_saza.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.woori_saza.model.dto.ArticleAndPartyRequestDto;
import project.woori_saza.model.dto.ArticleResponseDto;
import project.woori_saza.model.service.ArticleService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
@Api("게시글 컨트롤러")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ObjectMapper objectMapper;

    //   @ApiOperation(value="게시글 리스트",notes = "게시글 리스트를 반환다.",response = Map.class)
    @ApiOperation(value = "게시글 리스트 조회", notes = "게시글 리스트를 쿼리스트링으로 받은 옵션에 따라 불러온다", response = Map.class)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getArticleList(@RequestParam(required = false) @ApiParam(value = "카테고리 정보") String category,
                                                              @RequestParam(required = false) @ApiParam(value = "범위 정보") String range,
                                                              @RequestParam(required = false) @ApiParam(value = "검색어") String keyword,
                                                              @RequestParam @ApiParam(value = "프로필 아이디") String profileId) {
        Map<String, Object> result = new HashMap<>();
        List<ArticleResponseDto> articleList = null;
        HttpStatus httpStatus = null;

        try {
            articleList = articleService.getArticleList(profileId, category, range, keyword);
            httpStatus = HttpStatus.OK;
            result.put("success", true);

        } catch (RuntimeException e) {
            e.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);

        }

        result.put("articleList", articleList);

        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

    @ApiOperation(value = "게시글 조회", notes = "게시글 번호에 해당하는 게시글을 불러온다", response = Map.class)
    @GetMapping("/{articleId}")
    public ResponseEntity<Map<String, Object>> getArticle(@PathVariable @ApiParam(value = "게시글 번호", required = true) String articleId) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        ArticleResponseDto article = null;
        try {
            article = articleService.getArticle(Long.parseLong(articleId));
            httpStatus = HttpStatus.OK;
            result.put("success", true);

        } catch (RuntimeException e) {
            e.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);

        }
        result.put("article", article);
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성하고 파티를 자동으로 생성하여 맵핑한다, 성공 여부가 success에 저장된다", response = Map.class)
    @PostMapping
    public ResponseEntity<Map<String, Object>> insertArticle(@Valid @RequestBody @ApiParam(value = "게시글과 파티에 대한 정보", required = true) ArticleAndPartyRequestDto articleAndPartyRequestDto) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try {
            ArticleResponseDto articleResponseDto = articleService.insertArticle(articleAndPartyRequestDto);

            httpStatus = HttpStatus.OK;
            result.put("article", articleResponseDto);
            result.put("success", true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글과 파티 정보를 수정한다. 성공 여부가 success에 저장된다.", response = Map.class)
    @PutMapping("/{articleId}")
    public ResponseEntity<Map<String, Object>> updateArticle(@Valid @RequestBody @ApiParam(value = "게시글과 파티에 대한 정보", required = true) ArticleAndPartyRequestDto articleAndPartyRequestDto,
                                                             @PathVariable @ApiParam(value = "수정할 게시글 아이디", required = true) String articleId) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try {
            ArticleResponseDto articleResponseDto = articleService.updateArticle(articleAndPartyRequestDto, Long.parseLong(articleId));

            httpStatus = HttpStatus.OK;
            result.put("article", articleResponseDto);
            result.put("success", true);
        } catch (RuntimeException  e) {
            e.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글 번호에 해당하는 게시글을 삭제한다. 성공 여부가 success에 저장된다.", response = Map.class)
    @DeleteMapping("/{articleId}")
    public ResponseEntity<Map<String, Object>> deleteArticle(@PathVariable @ApiParam(value = "삭제할 게시글 번호", required = true) String articleId) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try {
            articleService.deleteArticle(Long.parseLong(articleId));

            httpStatus = HttpStatus.OK;
            result.put("success", true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

}
