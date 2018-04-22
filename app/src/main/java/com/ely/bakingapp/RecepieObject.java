package com.ely.bakingapp;

import java.util.List;

/**
 * Created by lior on 4/22/18.
 */

public class RecepieObject {
    public String name;
    public List<Ingredients> ingredients;
    public List<Steps> steps;
    public String id;

    public RecepieObject(String name, List<Ingredients> ingredients, List<Steps> steps, String id) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }




}
