package com.drapo.recipe.services;

import com.drapo.recipe.commands.RecipeCommand;
import com.drapo.recipe.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
