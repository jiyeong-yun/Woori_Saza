package project.woori_saza.model.service;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import project.woori_saza.model.domain.*;
import project.woori_saza.model.dto.UserProfileDto;
import project.woori_saza.model.repo.*;
import project.woori_saza.util.GeoLocationUtil;
import project.woori_saza.util.HashEncoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    GeoLocationUtil geoLocationUtil;

    @Autowired
    UserAuthRepo userAuthRepo;

    @Autowired
    UserProfileRepo userProfileRepo;

    @Autowired
    HashEncoder hashEncoder;

    @Autowired
    ArticleRepo articleRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    ChatRoomJoinRepo chatRoomJoinRepo;

    @Autowired
    PartyRepo partyRepo;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public UserProfileDto login(String userAuthId) {
        UserAuth userAuth = userAuthRepo.getById(hashEncoder.encode(userAuthId));
        UserProfile userProfile = userProfileRepo.findByUserAuth(userAuth);
        return userProfile == null ? null : new UserProfileDto(userProfile);
    }

    @Override
    public UserProfileDto register(UserProfileDto userProfileDto) {

        UserAuth userAuth = new UserAuth(hashEncoder.encode(userProfileDto.getId()), false, null);
        userAuth = userAuthRepo.save(userAuth);

        UserProfile userProfile = new UserProfile(userProfileDto);
        userProfile.setId(hashEncoder.encode(userAuth.getId())); // double hashed id
        userProfile.setUserAuth(userAuth);
        userProfile.setJoinDate(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime());
        Double[] lnglat = geoLocationUtil.parseLocationToLngLat(userProfile.getAddress());
        userProfile.setLng(lnglat[0]);
        userProfile.setLat(lnglat[1]);
        userProfile.setScore(0);
        userProfile.setCnt(0);
        userProfile.setPic(userProfileDto.getPic());

        userProfile = userProfileRepo.save(userProfile);

        return new UserProfileDto(userProfile);

    }

    @Override
    public String upload(MultipartFile uploadFile) throws Exception {

            // 파일 정보
            String originFilename = uploadFile.getOriginalFilename(); //파일이름
            String extension = originFilename.substring(originFilename.length()-3); //확장자

            // 사진인지 체크
            if(!(extension.equals("jpg") || extension.equals("png")|| extension.equals("PNG")|| extension.equals("JPG"))){
                throw new FileUploadException("파일 확장자가 jpg나 png가 아닙니다.");
            }
           //파일이름 랜덤으로 만들기
            String url="/profile/";
            String saveFileName =UUID.randomUUID().toString() + originFilename.substring(originFilename.lastIndexOf(".")); //랜덤이름+확장자
            String saveFileName2=url+saveFileName;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            //파일 바이트
            ByteArrayResource fileAsResource = new ByteArrayResource(uploadFile.getBytes()){
                @Override
                public String getFilename() {
                    return saveFileName2;
                }
            };

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("uploadFile", fileAsResource); //파일 바이트 저장
            body.add("parentPath","profile");

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

           String serverUrl = "http://i6c102.p.ssafy.io:3000/upload";

           ResponseEntity<String> response = restTemplate
                    .postForEntity(serverUrl, requestEntity, String.class);

           return "http://i6c102.p.ssafy.io/images"+saveFileName2;

    }

    @Override
    public UserProfileDto update(UserProfileDto userProfileDto) {

        UserProfile user = userProfileRepo.getById(userProfileDto.getId()); // hashwoori
        user.setNickname(userProfileDto.getNickname());
        user.setAddress(userProfileDto.getAddress());
        Double[] lnglat = geoLocationUtil.parseLocationToLngLat(user.getAddress());
        user.setLng(lnglat[0]);
        user.setLat(lnglat[1]);
        user.setPic(userProfileDto.getPic());
        user = userProfileRepo.save(user);

        return new UserProfileDto(user);
    }

    @Override
    public void delete(UserProfileDto userProfileDto) {
        UserProfile user = userProfileRepo.getById(userProfileDto.getId()); // hashwoori

        /* 회원과 관련된 글 목록과 댓글의 연관관계 끊기, 나머지는 삭제 */
        List<Article> articleList = articleRepo.findByUserProfileIsNotNullAndUserProfileOrderByCreatedAtDesc(user);
        for (Article article : articleList) {

            article.setUserProfile(null);
            articleRepo.save(article);
        }

        List<Comment> commentList = commentRepo.findByUserProfile(user);
        for (Comment comment : commentList) {
            comment.setUserProfile(null);
            commentRepo.save(comment);
        }

        userProfileRepo.delete(user);
    }

    @Override
    public UserProfileDto getUserProfileInfo(String profileId) {
        return new UserProfileDto(userProfileRepo.getById(profileId));
    }
}
