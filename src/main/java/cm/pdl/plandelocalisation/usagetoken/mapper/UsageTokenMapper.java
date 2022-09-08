package cm.pdl.plandelocalisation.usagetoken.mapper;

import cm.pdl.plandelocalisation.usagetoken.dto.UsageTokenDTO;
import cm.pdl.plandelocalisation.usagetoken.infra.entity.UsageToken;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Georges DEFO
 * @date 30/07/2022
 */
@Mapper(componentModel = "spring")
public interface UsageTokenMapper {

    UsageTokenDTO tokenToDTO(UsageToken token);

    UsageToken fromDTO(UsageTokenDTO usageTokenDTO);

    List<UsageTokenDTO> tokensToDTO(List<UsageToken> tokens);

}
