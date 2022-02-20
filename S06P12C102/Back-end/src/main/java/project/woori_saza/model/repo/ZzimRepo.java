package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.UserProfile;
import project.woori_saza.model.domain.Zzim;

import java.util.List;

public interface ZzimRepo extends JpaRepository<Zzim, Zzim.ZzimId> {

    List<Zzim> findByUserProfileOrderByArticleDesc(UserProfile userProfile);

    List<Zzim> findByArticle(Article article);

    void deleteByArticleId(String id);

}
