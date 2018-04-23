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
    public String servings;
    public String image;

    public RecepieObject(String name, List<Ingredients> ingredients, List<Steps> steps, String id, String servings, String image) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.id = id;
        this.servings = servings;
        this.image = image;
    }



    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
