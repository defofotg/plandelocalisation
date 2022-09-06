package cm.pdl.plandelocalisation.usagetoken.dto;

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
public class UsageTokenDTO {
    private Long id;
    private String value;
    private boolean activated = true;
}
