package project.woori_saza.model.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class MemberInfo {

    @Id
    @Column(name="member_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "boolean default false")
    private Boolean isBoss; //파티장 여부

//    @Positive
    @NotNull
    private Integer amount; // 파티원 선택수량

//    @Positive
    @NotNull
    private Integer price; // 파티원 부담금액

    private String paidMethod; //결제 방법

    @Column(columnDefinition = "boolean default false")
    private Boolean isConfirmed; //파티원 구매확정여부

    @Column(columnDefinition = "boolean default false")
    private Boolean isLated; // 위약금 부담여부


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id")
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="party_id")
    private Party party;


}
