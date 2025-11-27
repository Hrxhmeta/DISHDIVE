package com.oliver.quickmeal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.oliver.quickmeal.R;
import com.oliver.quickmeal.apiCalls.ApiModels.ExtendedIngredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {

    Context context;
    List<ExtendedIngredient> list;

    public IngredientAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.textViewIngredientsName.setText(list.get(position).name);
        holder.textViewIngredientsName.setSelected(true);
        holder.textViewIngredientsQuantity.setText(list.get(position).original);
        holder.textViewIngredientsQuantity.setSelected(true);

        Picasso
                .get()
                .load("https://spoonacular.com/cdn/ingredients_100x100/" + list.get(position).image)
                .placeholder(R.drawable.loadingsmall)
                .into(holder.imageViewIngredients);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngredientsViewHolder extends RecyclerView.ViewHolder {

    MaterialTextView textViewIngredientsQuantity, textViewIngredientsName;
    ImageView imageViewIngredients;

    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewIngredientsQuantity = itemView.findViewById(R.id.txtViewIngredientsQuantity);
        textViewIngredientsName = itemView.findViewById(R.id.txtViewIngredientName);
        imageViewIngredients = itemView.findViewById(R.id.imageViewIngredient);
    }
}