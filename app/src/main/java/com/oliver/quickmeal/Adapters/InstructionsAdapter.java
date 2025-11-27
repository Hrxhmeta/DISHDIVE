package com.oliver.quickmeal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.oliver.quickmeal.R;
import com.oliver.quickmeal.apiCalls.ApiModels.InstructionResponse;

import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsViewHolder> {

    Context context;
    List<InstructionResponse> list;

    public InstructionsAdapter(Context context, List<InstructionResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsViewHolder holder, int position) {
        holder.textViewInstructionName.setText(list.get(position).name);
        holder.recyclerViewInstructionsSteps.setHasFixedSize(true);
        holder.recyclerViewInstructionsSteps.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        InstructionStepAdapter instructionStepAdapter = new InstructionStepAdapter(context, list.get(position).steps);
        holder.recyclerViewInstructionsSteps.setAdapter(instructionStepAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionsViewHolder extends RecyclerView.ViewHolder {

    MaterialTextView textViewInstructionName;
    RecyclerView recyclerViewInstructionsSteps;

    public InstructionsViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewInstructionName = itemView.findViewById(R.id.txtViewInstructionName);
        recyclerViewInstructionsSteps = itemView.findViewById(R.id.recyclerViewInstructionSteps);
    }
}