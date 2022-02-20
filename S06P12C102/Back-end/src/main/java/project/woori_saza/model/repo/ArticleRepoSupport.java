package project.woori_saza.model.repo;

import project.woori_saza.model.domain.Article;

import java.util.List;

public interface ArticleRepoSupport {

    List<Article> findByRange(Double[] lnglat, String range);

    List<Article> findByKeywordAndRange(String keyword, Double[] lnglat, String range);

    List<Article> findByKeywordAndCategory(String keyword, String category);

    List<Article> findByCategoryAndRange(String category, Double[] lnglat, String range);

    List<Article> findByAllCondition(String keyword, String category, Double[] lnglat, String range);
}
