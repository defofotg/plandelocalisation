package cm.pdl.plandelocalisation.usagetoken.domain.controller;

import cm.pdl.plandelocalisation.usagetoken.dto.UsageTokenDTO;
import cm.pdl.plandelocalisation.usagetoken.service.UsageTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Georges DEFO
 * @date 06/09/2022
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class TokenController {

    private final UsageTokenService usageTokenService;

    @GetMapping("/tokens")
    ResponseEntity<List<UsageTokenDTO>> tokens() {

        List<UsageTokenDTO> tokens = this.usageTokenService.getUsageTokens();

        if (CollectionUtils.isEmpty(tokens)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(tokens);
    }

}
