package tech.abede.walletapi.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import tech.abede.walletapi.authentication.model.UserPrincipal;

@SecurityRequirement(name = "bearerAuth")
@RestController
public class GreetingController {
    @GetMapping("/greetings")
    public String greet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return "Hello " + userPrincipal.getEmail();
    }

}
