package project.woori_saza.model.service;

import project.woori_saza.model.dto.ArticleAndPartyRequestDto;
import project.woori_saza.model.dto.ArticleResponseDto;

import java.util.List;

public interface ArticleService {
    ArticleResponseDto getArticle(Long articleId);
    List<ArticleResponseDto> getArticleList(String profileId, String category, String range, String keyword);
    ArticleResponseDto insertArticle(ArticleAndPartyRequestDto articleAndPartyRequestDto);
    ArticleResponseDto updateArticle(ArticleAndPartyRequestDto articleAndPartyRequestDto, Long articleId);
    void deleteArticle(Long articleId);

}
