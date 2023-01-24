package edu.kit.recipe.recipebackend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BaseController {

    @GetMapping
    public ResponseEntity<String> checkAlive() {
        return ResponseEntity.ok("Hello World");
    }
}
