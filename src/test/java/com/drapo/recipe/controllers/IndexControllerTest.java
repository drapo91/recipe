package com.drapo.recipe.controllers;


import com.drapo.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeServiceMock;

    @Mock
    Model modelMock;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        indexController=new IndexController(recipeServiceMock);
    }

    @Test
    public void getIndexPage() {

        String viewName=indexController.getIndexPage(modelMock);

        assertEquals(viewName, "index");
        verify(recipeServiceMock, Mockito.times(1)).getRecipes();
        verify(modelMock, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), Mockito.anySet());
    }
}