package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Zzim{
        @EmbeddedId
        private ZzimId zzimId;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("profileId")
        private UserProfile userProfile;

        @ManyToOne(fetch = FetchType.LAZY)
        @MapsId("articleId")
        private Article article;

        public Zzim(UserProfile userProfile, Article article) {
                this.userProfile = userProfile;
                this.article = article;
                this.zzimId = new ZzimId(userProfile.getId(),article.getId());
        }

        @Getter
        @Setter
        @Embeddable
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ZzimId implements Serializable {

                private String profileId;
                private Long articleId;

        }


}
