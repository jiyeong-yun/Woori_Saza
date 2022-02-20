package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Party implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Long id;

    // 구매폼 작성여부
    @NotNull
    @Column(columnDefinition = "boolean default false")
    private Boolean formChecked;

    @NotNull
    @FutureOrPresent
    private LocalDateTime deadline;

    @NotNull
    private String product;

    @NotNull
    private Integer totalPrice; //총금액

    @NotNull
    private Integer totalProductCount; //총 물건수량

    @NotNull
    private Integer totalRecruitMember; //총 모집인원수

    @Positive
    @NotNull
    private Integer currentRecruitMember; //현재 인원수

    @NotNull
    @Column(columnDefinition = "boolean default false")
    private Boolean isClosed; //마감

    @NotNull
    private Integer penalty; //위약금

    @OneToOne(mappedBy = "party",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private PaidForm paidForm;

    @OneToOne(mappedBy = "party",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Article article;

    @OneToMany(mappedBy = "party", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MemberInfo> memberInfos = new ArrayList<>();

}
