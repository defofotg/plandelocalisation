package cm.pdl.plandelocalisation.plan.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author Georges DEFO
 * @date 30/07/2022
 */
@Getter
@Setter
@AllArgsConstructor
public class PlanInputVM {
    @NotEmpty
    String longitude;
    @NotEmpty
    String latitude;

    String firstname;
    @NotEmpty
    String lastname;
    @NotEmpty
    String phoneNumber;
    @NotEmpty
    String idCardNumber;
}
