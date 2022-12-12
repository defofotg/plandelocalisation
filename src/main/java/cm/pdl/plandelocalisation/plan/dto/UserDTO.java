package cm.pdl.plandelocalisation.plan.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author Georges DEFO
 * @date 30/07/2022
 */
@RequiredArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String lastname;
    private String firstname;
    private String phoneNumber;
    private String idCardNumber;
}
