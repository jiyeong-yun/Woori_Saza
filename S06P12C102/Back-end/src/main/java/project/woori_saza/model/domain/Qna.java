package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Qna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_id")
    private Long id;

    @NotNull
    private String category;

    @NotNull
    private String content;

    @NotNull
    private String title;

    private LocalDateTime date;

    private String comment;

    @ElementCollection
    private List<String> pic=new ArrayList<>();

    //profile_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id")
    UserProfile userProfile;

}
