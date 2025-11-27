package com.oliver.quickmeal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.oliver.quickmeal.Listeners.RecipeClickListener;
import com.oliver.quickmeal.R;
import com.oliver.quickmeal.apiCalls.ApiModels.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeViewHolder> {

    Context context;
    List<SimilarRecipeResponse> list;
    RecipeClickListener listener;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipeResponse> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
        holder.textViewSimilarTitle.setText(list.get(position).title);
        holder.textViewSimilarTitle.setSelected(true);
        holder.textViewSimilarServing.setText(list.get(position).servings + " Persons");
        holder.textViewSimilarServing.setSelected(true);

        Picasso.get()
                .load("https://spoonacular.com/recipeImages/" + list.get(position).id + "-556x370." + list.get(position).imageType)
                .placeholder(R.drawable.loadingsmall)
                .into(holder.imageViewSimilar);

        holder.cardViewRecipeHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class SimilarRecipeViewHolder extends RecyclerView.ViewHolder {

    MaterialCardView cardViewRecipeHolder;
    MaterialTextView textViewSimilarTitle, textViewSimilarServing;
    ImageView imageViewSimilar;

    public SimilarRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        cardViewRecipeHolder = itemView.findViewById(R.id.similarRecipeHolder);
        textViewSimilarTitle = itemView.findViewById(R.id.txtViewSimilarTitle);
        textViewSimilarServing = itemView.findViewById(R.id.txtViewSimilarServing);
        imageViewSimilar = itemView.findViewById(R.id.imageViewSimilar);
    }
}
