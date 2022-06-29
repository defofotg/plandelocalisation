package cm.pdl.plandelocalisation.plan.controller;

import cm.pdl.plandelocalisation.plan.dto.LocationDTO;
import cm.pdl.plandelocalisation.plan.service.PlanGeneratorService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final PlanGeneratorService planGeneratorService;

    @GetMapping("/plan/download")
    void downloadPlanPDF(@RequestParam @NonNull String latitude, @RequestParam @NonNull String longitude, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        this.planGeneratorService.export(new LocationDTO(latitude, longitude), response);
    }
}
