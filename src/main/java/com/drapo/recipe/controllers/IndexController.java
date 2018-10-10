package com.drapo.recipe.controllers;

import com.drapo.recipe.model.Category;
import com.drapo.recipe.model.UnitOfMeasure;
import com.drapo.recipe.repositories.CategoryRepository;
import com.drapo.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    CategoryRepository categoryRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/", "/index"})
    public String getIndexPage(){

        Optional<Category> categoryOptional=categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> uomOptional=unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("category id is: "+ categoryOptional.get().getId());
        System.out.println("UnitOfMeasure id is: "+ uomOptional.get().getId());

        return "index";
    }
}
