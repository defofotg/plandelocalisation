package cm.pdl.plandelocalisation.plan.controller;

import cm.pdl.plandelocalisation.plan.controller.dto.PlanInputVM;
import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.dto.UserDTO;
import cm.pdl.plandelocalisation.plan.mapper.PlanMapper;
import cm.pdl.plandelocalisation.plan.service.PlangGeneratorInterface;
import cm.pdl.plandelocalisation.usagetoken.dto.UsageTokenDTO;
import cm.pdl.plandelocalisation.usagetoken.service.UsageTokenService;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Georges DEFO
 * @date 14/06/2022
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlanController {

    private final PlangGeneratorInterface planGeneratorService;

    private final UsageTokenService tokenService;

    private final PlanMapper planMapper;

    @PostMapping("/plan/download")
    ResponseEntity<?> downloadPlanPDF(@RequestBody PlanInputVM planInputVM, HttpServletResponse response) throws IOException, DocumentException {

        UsageTokenDTO usageTokenDTO = tokenService.findByValue(planInputVM.getToken());
        if (usageTokenDTO == null || !usageTokenDTO.isActivated()) {
            log.debug("Generation du PDF refusée pour le token: {}", usageTokenDTO);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("token_manquant_ou_invalide");
        }

        response.setContentType("application/pdf");

        LocationDTO location = planMapper.planVMtoLocationDTO(planInputVM);
        UserDTO user = planMapper.planVMtoUserDTO(planInputVM);

        if (location == null || user == null) {
            return ResponseEntity.badRequest().build();
        }

        this.planGeneratorService.generate(user, location, response);

        tokenService.revokeToken(usageTokenDTO);

        return ResponseEntity.ok().build();
    }
}
