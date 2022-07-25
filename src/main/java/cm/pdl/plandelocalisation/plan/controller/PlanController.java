package cm.pdl.plandelocalisation.plan.controller;

import cm.pdl.plandelocalisation.plan.service.PlangGeneratorInterface;
import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/plan/download")
    ResponseEntity<?> downloadPlanPDF(@RequestParam @NonNull String latitude, @RequestParam @NonNull String longitude, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        this.planGeneratorService.generate(new LocationDTO(latitude, longitude), response);
        return ResponseEntity.ok().build();
    }
}
