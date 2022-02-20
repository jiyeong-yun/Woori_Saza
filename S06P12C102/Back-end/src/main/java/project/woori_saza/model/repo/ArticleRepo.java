package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Category;
import project.woori_saza.model.domain.Party;
import project.woori_saza.model.domain.UserProfile;

import java.util.List;

public interface ArticleRepo extends JpaRepository<Article, Long>, ArticleRepoSupport{

    List<Article> findAllByUserProfileIsNotNullOrderByCreatedAtDesc();

    List<Article> findByUserProfileIsNotNullAndUserProfileOrderByCreatedAtDesc(UserProfile userProfile);

    List<Article> findByUserProfileIsNotNullAndCategoryOrderByCreatedAtDesc(Category category);

    List<Article> findByUserProfileIsNotNullAndTitleContainingOrContentContainingOrderByCreatedAtDesc(String title, String content);

    Article findByParty(Party party);
}
