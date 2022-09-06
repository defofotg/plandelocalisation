package cm.pdl.plandelocalisation.usagetoken.service;

import cm.pdl.plandelocalisation.usagetoken.infra.entity.UsageToken;
import cm.pdl.plandelocalisation.usagetoken.infra.repository.UsageTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.*;

/**
 * @author Georges DEFO
 * @date 24/08/2022
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class StartupRunner implements CommandLineRunner {

    private final UsageTokenRepository usageTokenRepository;

    @Override
    public void run(String... args) throws Exception {
        try (InputStream inputStream = getClass().getResourceAsStream("/tokens.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                reader.lines().map(this::parseLine).toList();
        }

    }

    private UsageToken parseLine(String line) {
        UsageToken usageToken = new UsageToken(line);
        log.debug("Usage token {} is created", usageToken.getValue());
        usageTokenRepository.save(usageToken);
        return usageToken;
    }
}
