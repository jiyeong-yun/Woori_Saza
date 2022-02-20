package project.woori_saza.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.woori_saza.model.domain.PaidForm;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PaidFormResponseDto {

    @ApiModelProperty(value = "구매 인증 사진")
    private String pic;
    @ApiModelProperty(value = "운송장 번호", example = "1234567")
    private String billingNo;
    @ApiModelProperty(value = "택배 예상 도착일", example = "2022-02-01-23:59:59")
    private LocalDate deliveryDate;
    @ApiModelProperty(value = "예상 수령일", example = "2022-02-01-23:59:59")
    private LocalDate receiptDate;
    @ApiModelProperty(value = "파티 id", example = "1")
    private Long partyId;

    public PaidFormResponseDto(PaidForm paidForm) {
        pic = paidForm.getPic();
        billingNo = paidForm.getBillingNo();
        deliveryDate = paidForm.getDeliveryDate();
        receiptDate = paidForm.getReceiptDate();
        partyId=paidForm.getParty().getId();
    }
}
