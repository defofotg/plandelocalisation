package cm.pdl.plandelocalisation.usagetoken.infra.repository;

import cm.pdl.plandelocalisation.usagetoken.infra.entity.UsageToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Georges DEFO
 * @date 23/08/2022
 */
public interface UsageTokenRepository extends JpaRepository<UsageToken, Integer> {

    UsageToken findUsageTokenByValue(String value);
}
