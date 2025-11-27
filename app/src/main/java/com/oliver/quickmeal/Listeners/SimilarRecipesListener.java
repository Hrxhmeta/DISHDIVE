package com.oliver.quickmeal.Listeners;

import com.oliver.quickmeal.apiCalls.ApiModels.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipeResponse> response, String message);

    void didError(String message);
}
