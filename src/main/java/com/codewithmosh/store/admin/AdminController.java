package com.codewithmosh.store.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin")
public class AdminController {

    @GetMapping("/hello")
    @Operation(summary = "Test Admin Role")
    public String sayHello() {
        return "Hello Admin!";
    }
}
