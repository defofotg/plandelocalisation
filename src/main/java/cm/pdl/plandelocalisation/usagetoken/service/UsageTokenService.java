package cm.pdl.plandelocalisation.usagetoken.service;

import cm.pdl.plandelocalisation.usagetoken.dto.UsageTokenDTO;

import java.util.List;

/**
 * @author Georges DEFO
 * @date 06/09/2022
 */
public interface UsageTokenService {

    List<UsageTokenDTO> getUsageTokens();
}
