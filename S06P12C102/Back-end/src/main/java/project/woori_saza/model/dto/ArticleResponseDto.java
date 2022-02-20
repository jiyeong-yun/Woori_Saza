package project.woori_saza.model.dto;

import lombok.Data;
import project.woori_saza.model.domain.Article;
import project.woori_saza.model.domain.Category;
import project.woori_saza.model.domain.Tag;

import javax.persistence.ElementCollection;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleResponseDto {
    // 보여줄때
    private Long id;
    private String title;
    private String author;
    private String content;
    private String product;
    private LocalDateTime createdAt;
    private String link;
    @ElementCollection
    private List<String> pic;
    private Integer totalPrice;
    private Integer totalRecruitMember;
    private Integer myPrice;
    private Integer currentRecruitMember;
    private Tag tag;
    private Category category;
    private Integer penalty;
    private LocalDateTime deadline;
    private Integer totalProductCount;
    private Long partyId;
    // 찜 유무..


    public ArticleResponseDto(Article article) {
        id = article.getId();
        title = article.getTitle();
        author = article.getUserProfile() == null ? "탈퇴한 사용자입니다" :article.getUserProfile().getNickname();
        product = article.getParty().getProduct();
        content = article.getContent();
        createdAt = article.getCreatedAt();
        link = article.getLink();
        pic = article.getPic();
        totalPrice = article.getParty().getTotalPrice();
        totalRecruitMember = article.getParty().getTotalRecruitMember();
        currentRecruitMember = article.getParty().getCurrentRecruitMember();
        myPrice = article.getParty().getTotalPrice() / totalRecruitMember;
        tag = article.getTag();
        category = article.getCategory();
        penalty = article.getParty().getPenalty();
        deadline = article.getParty().getDeadline();
        totalProductCount = article.getParty().getTotalProductCount();
        partyId=article.getParty().getId();
    }
}
