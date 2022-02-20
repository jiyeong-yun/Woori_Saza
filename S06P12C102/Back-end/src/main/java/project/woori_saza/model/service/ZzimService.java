package project.woori_saza.model.service;

import project.woori_saza.model.dto.ZzimDto;

import java.util.List;

public interface ZzimService {

    ZzimDto getZzim(String profileId, Long articleId);

    List<ZzimDto> getZzimList(String profileId);
    List<ZzimDto> getZzimList(Long articleId);

    ZzimDto insertZzim(String profileId, Long articleId);
    void deleteZzim(String profileId, Long articleId);

}
