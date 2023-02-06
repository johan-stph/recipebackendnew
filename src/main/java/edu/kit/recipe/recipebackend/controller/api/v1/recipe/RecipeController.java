package edu.kit.recipe.recipebackend.controller.api.v1.recipe;


import edu.kit.recipe.recipebackend.dto.RecipeDTO;
import edu.kit.recipe.recipebackend.entities.Recipe;
import edu.kit.recipe.recipebackend.repository.RecipeInfo;
import edu.kit.recipe.recipebackend.repository.RecipeRepository;
import edu.kit.recipe.recipebackend.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
@Validated
public class RecipeController {

    private final RecipeRepository recipeRepository;

    private final RecipeService recipeService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable String id) {
        return ResponseEntity.ok().body(recipeService.deleteRecipe(id));
    }

    @PostMapping
    public ResponseEntity<String> addRecipe(@RequestBody @Valid RecipeDTO recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @GetMapping
    public ResponseEntity<List<RecipeInfo>> getRecipeNameWithID() {
        return ResponseEntity.ok(recipeRepository.findAllProjectedBy());
    }

    @PostMapping("/{recipeId}/image/{imageId}")
    public ResponseEntity<String> addImageToRecipe(@PathVariable String recipeId, @PathVariable String imageId ) {
        return ResponseEntity.ok(recipeService.addImageToRecipe(recipeId, imageId));
    }

    @GetMapping("/{recipeId}/image")
    public ResponseEntity<String> getImageForRecipe(@PathVariable String recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(UUID.fromString(recipeId));
        return recipe.map(value -> ResponseEntity.ok(value.getImageData().getName())).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}