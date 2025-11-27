package com.oliver.quickmeal.apiCalls;

import com.oliver.quickmeal.apiCalls.ApiModels.InstructionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallInstructions {
    @GET("recipes/{id}/analyzedInstructions")
    Call<List<InstructionResponse>> callInstructions(
            @Path("id") int id,
            @Query("apiKey") String apiKey
    );
}
