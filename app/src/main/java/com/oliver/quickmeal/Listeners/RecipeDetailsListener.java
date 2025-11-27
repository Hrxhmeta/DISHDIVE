package com.oliver.quickmeal.Listeners;

import com.oliver.quickmeal.apiCalls.ApiModels.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
