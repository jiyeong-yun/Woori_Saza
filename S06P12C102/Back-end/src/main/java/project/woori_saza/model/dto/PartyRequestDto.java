package project.woori_saza.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyRequestDto {
    private LocalDateTime deadline;
    private String product;
    private Integer totalPrice;
    private Integer totalProductCount;
    private Integer totalRecruitMember;
}
