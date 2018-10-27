package com.drapo.recipe.controllers;

import com.drapo.recipe.model.Recipe;
import com.drapo.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    @Before
    public void  setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        recipeController=new RecipeController(recipeService);
    }

    @Test
    public void getRecipe() throws Exception{

        Recipe recipe=new Recipe();
        recipe.setId(1L);

        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();

        doReturn(recipe).when(recipeService).findById(anyLong());

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }
}