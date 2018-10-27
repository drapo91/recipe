package com.drapo.recipe.services;

import com.drapo.recipe.converters.RecipeCommandToRecipe;
import com.drapo.recipe.converters.RecipeToRecipeCommand;
import com.drapo.recipe.model.Recipe;
import com.drapo.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
        HashSet recipeData=new HashSet();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes=recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }
}