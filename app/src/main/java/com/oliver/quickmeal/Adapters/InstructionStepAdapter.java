package com.oliver.quickmeal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.oliver.quickmeal.R;
import com.oliver.quickmeal.apiCalls.ApiModels.Step;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepViewHolder> {

    Context context;
    List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_steps, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {

            holder.textViewStepNumber.setText(String.valueOf(list.get(position).number));
            holder.textViewStepTitle.setText(list.get(position).step);

            holder.recyclerViewIngredients.setHasFixedSize(true);
            holder.recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            InstructionIngredientsAdapter instructionIngredientsAdapter = new InstructionIngredientsAdapter(context, list.get(position).ingredients);
            holder.recyclerViewIngredients.setAdapter(instructionIngredientsAdapter);

            holder.recyclerViewEquipment.setHasFixedSize(true);
            holder.recyclerViewEquipment.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            InstructionEquipmentsAdapter instructionEquipmentsAdapter = new InstructionEquipmentsAdapter(context, list.get(position).equipment);
            holder.recyclerViewEquipment.setAdapter(instructionEquipmentsAdapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionStepViewHolder extends RecyclerView.ViewHolder {

    MaterialTextView textViewStepNumber, textViewStepTitle;
    RecyclerView recyclerViewEquipment, recyclerViewIngredients;

    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewStepNumber = itemView.findViewById(R.id.txtViewInstructionStepNumber);
        textViewStepTitle = itemView.findViewById(R.id.txtViewInstructionStepTitle);
        recyclerViewEquipment = itemView.findViewById(R.id.recyclerViewInstructionEquipments);
        recyclerViewIngredients = itemView.findViewById(R.id.recyclerViewInstructionIngredients);
    }
}