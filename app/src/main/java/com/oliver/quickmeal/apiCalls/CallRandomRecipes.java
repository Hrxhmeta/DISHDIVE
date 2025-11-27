package com.oliver.quickmeal.apiCalls;

import com.oliver.quickmeal.apiCalls.ApiModels.RandomRecipeApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallRandomRecipes {
    @GET("recipes/random")
    Call<RandomRecipeApiResponse> callRandomRecipe(
            @Query("apiKey") String apiKey,
            @Query("number") String number,
            @Query("tags") List<String> tags
    );
}
