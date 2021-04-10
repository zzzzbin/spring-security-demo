package vn.com.pos.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/movie")
public class MovieController {
    //Voting-based annotations: securedEnabled=true
    @GetMapping("public1")
    @Secured("ROLE_PUBLIC")
    public String publiclyAvailable() {
        return "Hello All!";
    }

    @GetMapping("admin1")
    @Secured("ROLE_ADMIN")
    public String adminAccessible() {
        return "Hello Admin!";
    }

    //JSR-250 security annotations: jsr250Enabled=true
    @GetMapping("public2")
    @PermitAll
    public String publiclyAvailable2() {
        return "Hello All2!";
    }

    @GetMapping("admin2")
    @RolesAllowed({"ROLE_ADMIN"})
    public String adminAccessible2() {
        return "Hello Admin2!";
    }

    //Expression-based annotation: prePostEnabled=true
    @GetMapping("public3")
    @PreAuthorize("permitAll()")
    public String publiclyAvailable3() {
        return "Hello All!";
    }

    @GetMapping("admin3")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String adminAccessible3() {
        return "Hello Admin!";
    }
}