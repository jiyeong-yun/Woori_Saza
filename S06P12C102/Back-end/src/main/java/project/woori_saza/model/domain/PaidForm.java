package project.woori_saza.model.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaidForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Paid_form_id")
    private Long id;

    private String pic;

    private String billingNo;

    private LocalDate deliveryDate;

    private LocalDate receiptDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id", unique = true)
    private Party party;

    @Builder
    public PaidForm(String pic, String billingNo, LocalDate deliveryDate, LocalDate receiptDate) {
        this.pic = pic;
        this.billingNo = billingNo;
        this.deliveryDate = deliveryDate;
        this.receiptDate = receiptDate;
    }
}
