package org.vsanyc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @GetMapping("/simple-request")
    public String simpleRequest() {
        return "Simple response";
    }
}
