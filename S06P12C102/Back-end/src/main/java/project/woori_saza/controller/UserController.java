package project.woori_saza.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.woori_saza.model.dto.UserProfileDto;
import project.woori_saza.model.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api("사용자 컨트롤러")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "로그인", notes = "로그인을 시도한다, 성공시 프로필 정보를 반환하고 실패시 null을 반환한다", response = Map.class)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @ApiParam(value = "서드파티 로그인의 결과로 나온 ID", example = "{\"authid\":\"123456789\"}") Map<String, String> body) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            UserProfileDto userProfileDto = userService.login(body.get("authid")); // thirdPartyId
            result.put("profile", userProfileDto);
            result.put("success", true);

            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);

        }

        return new ResponseEntity<Map<String, Object>>(result, status);
    }



    @ApiOperation(value = "회원가입")
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserProfileDto userProfileDto) throws Exception {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            userProfileDto = userService.register(userProfileDto);
            result.put("profile", userProfileDto);
            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @ApiOperation(value = "회원 사진 업로드")
    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> register(@RequestPart(required = false) MultipartFile uploadFile) throws Exception {

        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            String url=userService.upload(uploadFile);
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



    @ApiOperation(value = "회원정보 수정", notes = "회원 정보를 수정한다. 닉네임, 주소, 프로필 사진을 변경할 수 있다.")
    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@RequestBody UserProfileDto userProfileDto){
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            userProfileDto = userService.update(userProfileDto);
            result.put("profile", userProfileDto);
            result.put("success", true);

            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);

        }
        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody UserProfileDto userProfileDto){
        Map<String, Object> result = new HashMap<>();
        HttpStatus httpStatus = null;
        try {
            userService.delete(userProfileDto);
            httpStatus = HttpStatus.OK;
            result.put("success", true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);
        }
        return new ResponseEntity<Map<String, Object>>(result, httpStatus);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Map<String, Object>> getProfileInfo(@PathVariable @ApiParam(value = "프로필 정보") String profileId) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;
        try {
            UserProfileDto userProfileDto = userService.getUserProfileInfo(profileId);
            result.put("profile", userProfileDto);
            result.put("success", true);

            status = HttpStatus.OK;
        } catch (RuntimeException e) {
            e.printStackTrace();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            result.put("success", false);

        }

        return new ResponseEntity<Map<String, Object>>(result, status);
    }
}
