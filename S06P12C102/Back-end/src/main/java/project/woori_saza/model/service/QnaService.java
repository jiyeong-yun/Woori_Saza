package project.woori_saza.model.service;

import org.springframework.web.multipart.MultipartFile;
import project.woori_saza.model.dto.QnaDto;

import java.util.List;

public interface QnaService {

    List<QnaDto> getMyQnaList(String profileId);
    List<QnaDto> getQnaList();
    QnaDto getQnaDetail(Long qnaId);
    void insertQna(QnaDto qnaDto) throws Exception;
    List<String> upload(List<MultipartFile> uploadFiles) throws Exception;
    void updateQna(QnaDto qnaDto);
    void deleteQna(Long qnaId);
    void insertQnaComment(QnaDto qnaDto);
}
