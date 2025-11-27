package com.oliver.quickmeal.Listeners;

import com.oliver.quickmeal.apiCalls.ApiModels.InstructionResponse;

import java.util.List;

public interface InstructionsListeners {
    void didFetch(List<InstructionResponse> response, String message);

    void didError(String message);
}
