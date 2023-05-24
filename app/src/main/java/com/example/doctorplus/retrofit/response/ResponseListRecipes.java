package com.example.doctorplus.retrofit.response;

import com.example.doctorplus.common.Recipe;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseListRecipes {
    @SerializedName("recipes")
    @Expose
    List<Recipe> recipes;

    public List<Recipe> getRecipes () {
        return recipes;
    }

    public List<String> getIds(){
        List<String> idsRecipe = new ArrayList<>();
        for(Recipe recipe : recipes){
            idsRecipe.add(recipe.getId());
        }
        return idsRecipe;
    }

    public List<String> getDates(){
        List<String> idsRecipe = new ArrayList<>();
        for(Recipe recipe : recipes){
            idsRecipe.add(recipe.getDate());
        }
        return idsRecipe;
    }

    public void setRecipes (List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
