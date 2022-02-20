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
import project.woori_saza.model.domain.PaidForm;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.dto.PaidFormRequestDto;
import project.woori_saza.model.dto.PaidFormResponseDto;
import project.woori_saza.model.repo.PaidFormRepo;
import project.woori_saza.model.repo.PartyRepo;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PaidFormServiceImpl implements PaidFormService{

    @Autowired
    PaidFormRepo paidFormRepo;

    @Autowired
    PartyRepo partyRepo;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public PaidFormResponseDto getPaidForm(Long partyId) {
        Party party=partyRepo.getById(partyId);
        PaidForm paidForm=paidFormRepo.findByParty(party);
        PaidFormResponseDto paidFormResponseDto = new PaidFormResponseDto(paidForm);
        return paidFormResponseDto;
    }

    @Override
    @Transactional
    public void insertPaidForm(PaidFormRequestDto paidFormRequestDto) {

            PaidForm paidForm = paidFormRequestDto.toEntity();
            Party party=partyRepo.getById(paidFormRequestDto.getPartyId());
            party.setFormChecked(true);
            paidForm.setParty(party);
            paidFormRepo.save(paidForm);
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
        String url="/paidform/";
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
        body.add("parentPath","paidform");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        String serverUrl = "http://i6c102.p.ssafy.io:3000/upload";

        ResponseEntity<String> response = restTemplate
                .postForEntity(serverUrl, requestEntity, String.class);

        return "http://i6c102.p.ssafy.io/images"+saveFileName2;

    }


    @Override
    @Transactional
    public void updatePaidForm(Long partyId, PaidFormRequestDto paidFormRequestDto) {

        Party party=partyRepo.getById(partyId);
        PaidForm paidForm=paidFormRepo.findByParty(party);
        paidForm.setPic(paidFormRequestDto.getPic());
        paidForm.setBillingNo(paidFormRequestDto.getBillingNo());
        paidForm.setDeliveryDate(paidFormRequestDto.getDeliveryDate());
        paidForm.setReceiptDate(paidFormRequestDto.getReceiptDate());
        paidFormRepo.save(paidForm);
    }


}
