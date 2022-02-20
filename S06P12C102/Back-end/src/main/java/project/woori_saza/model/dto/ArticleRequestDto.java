package project.woori_saza.model.dto;

import lombok.Data;
import project.woori_saza.model.domain.Category;

import javax.persistence.ElementCollection;
import java.util.List;

@Data
public class ArticleRequestDto {
// 작성
    private String title;
    private String content;
    private String link;
    private Category category;
    @ElementCollection
    private List<String> pic;

//    public Article toEntity() {
//        return Article.builder()
//                .title(title)
//                .content(content)
//                .link(link)
//                .pic(pic)
//                .build();
//    }
}
