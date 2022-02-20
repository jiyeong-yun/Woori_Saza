package project.woori_saza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.Zzim;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZzimDto {
    private String profileId;
    private Long articleId;

    public ZzimDto(Zzim zzim) {
        this.profileId = zzim.getUserProfile().getId();
        this.articleId = zzim.getArticle().getId();
    }
}
