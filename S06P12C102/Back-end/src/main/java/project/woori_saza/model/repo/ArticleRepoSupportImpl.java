package project.woori_saza.model.repo;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Category;
import project.woori_saza.model.domain.QArticle;
import project.woori_saza.model.domain.QUserProfile;

import java.util.List;

@Transactional(readOnly = true)
public class ArticleRepoSupportImpl extends QuerydslRepositorySupport implements ArticleRepoSupport {

    public ArticleRepoSupportImpl() {
        super(Article.class);

    }

    @Override
    public List<Article> findByRange(Double[] lnglat, String range) {
        QArticle article = QArticle.article;
        JPQLQuery<Article> jpqlQuery = from(article);
        jpqlQuery.where(article.userProfile.isNotNull());
        jpqlQuery.leftJoin(article.userProfile, QUserProfile.userProfile);
        jpqlQuery.fetchJoin();
        jpqlQuery.where(Expressions.stringTemplate("function('ST_Distance_Sphere', Point({0},{1}), Point({2},{3}))",
                        QUserProfile.userProfile.lng, QUserProfile.userProfile.lat, lnglat[0],lnglat[1])
                .loe(range)).orderBy(article.createdAt.desc());
        return jpqlQuery.fetch();
    }

    @Override
    public List<Article> findByKeywordAndRange(String keyword, Double[] lnglat, String range) {
        QArticle article = QArticle.article;
        JPQLQuery<Article> jpqlQuery = from(article);
        jpqlQuery.where(article.userProfile.isNotNull());
        jpqlQuery.leftJoin(article.userProfile, QUserProfile.userProfile);
        jpqlQuery.fetchJoin();
        jpqlQuery.where(Expressions.stringTemplate("function('ST_Distance_Sphere', Point({0},{1}), Point({2},{3}))",
                        QUserProfile.userProfile.lng, QUserProfile.userProfile.lat, lnglat[0],lnglat[1])
                .loe(range)
                .and(article.title.contains(keyword)
                        .or(article.content.contains(keyword)))).orderBy(article.createdAt.desc());
        return jpqlQuery.fetch();
    }

    @Override
    public List<Article> findByKeywordAndCategory(String keyword, String category) {
        QArticle article = QArticle.article;
        JPQLQuery<Article> jpqlQuery = from(article);
        jpqlQuery.where(article.userProfile.isNotNull());
        jpqlQuery.where(article.category.eq(Category.valueOf(category))
                .and(article.title.contains(keyword).or(article.content.contains(keyword)))).orderBy(article.createdAt.desc());
        return jpqlQuery.fetch();
    }

    @Override
    public List<Article> findByCategoryAndRange(String category, Double[] lnglat, String range) {
        QArticle article = QArticle.article;
        JPQLQuery<Article> jpqlQuery = from(article);
        jpqlQuery.where(article.userProfile.isNotNull());
        jpqlQuery.leftJoin(article.userProfile, QUserProfile.userProfile);
        jpqlQuery.fetchJoin();
        jpqlQuery.where(Expressions.stringTemplate("function('ST_Distance_Sphere', Point({0},{1}), Point({2},{3}))",
                        QUserProfile.userProfile.lng, QUserProfile.userProfile.lat, lnglat[0],lnglat[1])
                .loe(range)
                .and(article.category.eq(Category.valueOf(category)))).orderBy(article.createdAt.desc());
        return jpqlQuery.fetch();
    }

    @Override
    public List<Article> findByAllCondition(String keyword, String category, Double[] lnglat, String range) {
        QArticle article = QArticle.article;
        JPQLQuery<Article> jpqlQuery = from(article);
        jpqlQuery.where(article.userProfile.isNotNull());
        jpqlQuery.leftJoin(article.userProfile, QUserProfile.userProfile);
        jpqlQuery.fetchJoin();
        jpqlQuery.where(Expressions.stringTemplate("function('ST_Distance_Sphere', Point({0},{1}), Point({2},{3}))",
                        QUserProfile.userProfile.lng, QUserProfile.userProfile.lat, lnglat[0],lnglat[1])
                .loe(range)
                .and(article.category.eq(Category.valueOf(category)))
                .and(article.title.contains(keyword).or(article.content.contains(keyword)))).orderBy(article.createdAt.desc());
        return jpqlQuery.fetch();
    }
}
