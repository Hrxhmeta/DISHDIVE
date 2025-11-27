package com.oliver.quickmeal.Listeners;

import com.oliver.quickmeal.apiCalls.ApiModels.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);
}
