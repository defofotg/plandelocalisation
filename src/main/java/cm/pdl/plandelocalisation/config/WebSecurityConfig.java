package cm.pdl.plandelocalisation.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author Georges DEFO
 * @date 29/06/2022
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requiresChannel()
                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                .requiresSecure();
    }
}
