package edu.uncc.itcs.lightbulblms.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/greeting")
    @ApiOperation(value = "Get greeting", notes = "Returns static greeting")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("Hi!");
    }
}
