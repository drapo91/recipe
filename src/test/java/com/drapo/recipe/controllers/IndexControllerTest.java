package com.drapo.recipe.controllers;


import com.drapo.recipe.model.Recipe;
import com.drapo.recipe.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
        //given
        Set<Recipe> recipes =new HashSet<>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());

        when(recipeServiceMock.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor=ArgumentCaptor.forClass(Set.class);

        //when
        String viewName=indexController.getIndexPage(modelMock);

        //then
        assertEquals(viewName, "index");
        verify(recipeServiceMock, Mockito.times(1)).getRecipes();
        verify(modelMock, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController=argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

    @Test
    public void testMockMVC() throws Exception{
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("index"));
    }
}