package cm.pdl.plandelocalisation.usagetoken.service.impl;

import cm.pdl.plandelocalisation.usagetoken.dto.UsageTokenDTO;
import cm.pdl.plandelocalisation.usagetoken.infra.repository.UsageTokenRepository;
import cm.pdl.plandelocalisation.usagetoken.mapper.UsageTokenMapper;
import cm.pdl.plandelocalisation.usagetoken.service.UsageTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Georges DEFO
 * @date 06/09/2022
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UsageTokenServiceImpl implements UsageTokenService {

    private final UsageTokenRepository usageTokenRepository;

    private final UsageTokenMapper mapper;

    @Override
    public List<UsageTokenDTO> getUsageTokens() {
        return mapper.tokensToDTO(usageTokenRepository.findAll());
    }
}
