package project.woori_saza.model.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @NotNull
    private String content;

    private LocalDateTime date;

    @NotNull
    private int score;

    // review_from_id
    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile fromUser;

    // review_to_id
    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile toUser;

    @Builder
    public Review(String content, int score) {
        this.content = content;
        this.score = score;
    }

}