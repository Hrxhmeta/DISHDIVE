package com.oliver.quickmeal.apiCalls;

import com.oliver.quickmeal.apiCalls.ApiModels.RecipeDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallRecipeDetails {
    @GET("recipes/{id}/information")
    Call<RecipeDetailsResponse> callRecipeResponse(
            @Path("id") int id,
            @Query("apiKey") String apiKey
    );
}
