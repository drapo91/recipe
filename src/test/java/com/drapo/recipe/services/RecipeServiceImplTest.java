package com.drapo.recipe.services;

import com.drapo.recipe.converters.RecipeCommandToRecipe;
import com.drapo.recipe.converters.RecipeToRecipeCommand;
import com.drapo.recipe.model.Recipe;
import com.drapo.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeById(){
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        Optional<Recipe> optionalRecipe= Optional.of(recipe);

        doReturn(optionalRecipe).when(recipeRepository).findById(anyLong());

        Recipe returnedRecipe = recipeService.findById(1L);

        assertNotNull("Null recipe returned", returnedRecipe);
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipes() {
        Recipe recipe=new Recipe();
        HashSet<Recipe> recipeData=new HashSet<>();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes=recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }


    @Test
    public void deleteById(){
        Long idToDelete = 5L;

        recipeService.deleteById(idToDelete);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
//    @Test
//    public void deleteRecipe(){
//        Recipe recipe = new Recipe();
//        Recipe recipeToDelete = new Recipe();
//        List<Recipe> recipes = new ArrayList<>();
//        recipes.addAll(Arrays.asList(recipe, recipeToDelete));
//        recipes.remove(recipeToDelete);
//
//        doReturn(recipes).when(recipeRepository).delete(any(Recipe.class));
//
//        Set<Recipe> remaininedRecipes = recipeService.deleteRecipe();
//
//        assertTrue(!remaininedRecipes.contains(recipeToDelete));
//    }
}