package com.oliver.quickmeal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.oliver.quickmeal.R;
import com.oliver.quickmeal.apiCalls.ApiModels.Equipment;
import com.oliver.quickmeal.apiCalls.ApiModels.Ingredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionEquipmentsAdapter extends RecyclerView.Adapter<InstructionEquipmentViewHolder> {

    Context context;
    List<Equipment> list;

    public InstructionEquipmentsAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionEquipmentViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_step_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEquipmentViewHolder holder, int position) {
            holder.textViewStepItem.setText(list.get(position).name);
            holder.textViewStepItem.setSelected(true);
            Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/" + list.get(position).image).placeholder(R.drawable.loading).into(holder.imageViewStepItem);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionEquipmentViewHolder extends RecyclerView.ViewHolder {

    ImageView imageViewStepItem;
    MaterialTextView textViewStepItem;

    public InstructionEquipmentViewHolder(@NonNull View itemView) {
        super(itemView);

        imageViewStepItem = itemView.findViewById(R.id.imageViewInstructionStepItem);
        textViewStepItem = itemView.findViewById(R.id.txtViewInstructionStepItem);
    }
}