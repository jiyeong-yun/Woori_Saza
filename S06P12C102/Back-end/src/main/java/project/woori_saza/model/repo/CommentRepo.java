package project.woori_saza.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Comment;
import project.woori_saza.model.domain.UserProfile;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByArticle(Article article);
    List<Comment> findByUserProfileOrderByCreateAtDesc(UserProfile userProfile);
    List<Comment> findByUserProfile(UserProfile userProfile);

}
