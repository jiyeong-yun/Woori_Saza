package project.woori_saza.model.service;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import project.woori_saza.model.domain.Qna;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.dto.QnaDto;
import project.woori_saza.model.repo.QnaRepo;
import project.woori_saza.model.repo.UserProfileRepo;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class QnaServiceImpl implements QnaService{

    @Autowired
    QnaRepo qnaRepo;
    @Autowired
    UserProfileRepo userProfileRepo;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 내가 작성한 모든 문의 리스트
     */
    @Override
    public List<QnaDto> getMyQnaList(String profileId) {
        UserProfile user = userProfileRepo.getById(profileId);
        List<Qna> qnas = qnaRepo.findByUserProfile(user);
        return qnas.stream().map(QnaDto::new).collect(Collectors.toList());
    }

    /**
     * 관리자가 보는 문의 리스트
     */
    @Override
    public List<QnaDto> getQnaList() {
        List<Qna> qnas = qnaRepo.findAll();
        return qnas.stream().map(QnaDto::new).collect(Collectors.toList());
    }

    /**
     * 선택한 문의 글 내용
     */
    @Override
    public QnaDto getQnaDetail(Long qnaId) {
        Qna qna = qnaRepo.getById(qnaId);
        QnaDto qnaDto = new QnaDto(qna);
        return qnaDto;
    }

    /**
     * 1:1 문의 작성 - 작성자
     */
    @Override
    @Transactional
    public void insertQna(QnaDto qnaDto) throws Exception {
        UserProfile user = userProfileRepo.getById(qnaDto.getProfileId());
        Qna qna = new Qna();
        qna.setId(qnaDto.getId());
        qna.setCategory(qnaDto.getCategory());
        qna.setContent(qnaDto.getContent());
        qna.setTitle(qnaDto.getTitle());
        qna.setDate(ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime());
        qna.setUserProfile(user);
        List<String>list=new ArrayList<>();

        qna.setPic(list);
        qnaRepo.save(qna);
        qnaDto.setPic(qna.getPic());
    }

    @Override
    public List<String> upload(List<MultipartFile> uploadFiles) throws Exception {
        List<String>filename=new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            // 파일 정보
            String originFilename = uploadFile.getOriginalFilename(); //파일이름
            String extension = originFilename.substring(originFilename.length()-3); //확장자

            // 사진인지 체크
            if(!(extension.equals("jpg") || extension.equals("png")|| extension.equals("PNG")|| extension.equals("JPG"))){
                throw new FileUploadException("파일 확장자가 jpg나 png가 아닙니다.");
            }

            //파일이름 랜덤으로 만들기
            String url="/qna/";
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
            body.add("parentPath","qna");

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            String serverUrl = "http://i6c102.p.ssafy.io:3000/upload";

            ResponseEntity<String> response = restTemplate
                    .postForEntity(serverUrl, requestEntity, String.class);
            filename.add("http://i6c102.p.ssafy.io/images"+saveFileName2);

        }
        return filename;
    }

    /**
     * 1:1 문의 수정 - 관리자
     */
    @Override
    @Transactional
    public void updateQna(QnaDto qnaDto) {
        System.out.println("===1:1 문의 수정===");
        UserProfile user = userProfileRepo.getById(qnaDto.getProfileId());
        Qna qna = qnaRepo.getById(qnaDto.getId());
        qna.setCategory(qnaDto.getCategory());
        qna.setTitle(qnaDto.getTitle());
        qna.setContent(qnaDto.getContent());
        qna.setUserProfile(user);
        qnaRepo.save(qna);
    }

    /**
     * 1:1 문의 삭제 - 관리자
     */
    @Override
    @Transactional
    public void deleteQna(Long qnaId) {
        System.out.println("===1:1 문의 삭제===");
        qnaRepo.deleteById(qnaId);
    }

    /**
     * 관리자가 1:1 문의 답변달기
     */
    @Override
    @Transactional
    public void insertQnaComment(QnaDto qnaDto) {
        System.out.println("===1:1 문의 답변 작성===");
        Qna qna = qnaRepo.getById(qnaDto.getId());
        qna.setComment(qnaDto.getComment());
        qnaRepo.save(qna);
    }
}
