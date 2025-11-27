package com.oliver.quickmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;
import com.oliver.quickmeal.Adapters.IngredientAdapter;
import com.oliver.quickmeal.Adapters.InstructionsAdapter;
import com.oliver.quickmeal.Adapters.SimilarRecipeAdapter;
import com.oliver.quickmeal.Listeners.InstructionsListeners;
import com.oliver.quickmeal.Listeners.RecipeClickListener;
import com.oliver.quickmeal.Listeners.RecipeDetailsListener;
import com.oliver.quickmeal.Listeners.SimilarRecipesListener;
import com.oliver.quickmeal.apiCalls.ApiModels.InstructionResponse;
import com.oliver.quickmeal.apiCalls.ApiModels.RecipeDetailsResponse;
import com.oliver.quickmeal.apiCalls.ApiModels.SimilarRecipeResponse;
import com.oliver.quickmeal.apiCalls.RequestManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {

    int id;
    MaterialTextView textViewMealName, textViewMealSource, textViewMealSummery;
    ImageView imageViewMeal;
    RecyclerView recyclerViewIngredients, recyclerViewSimilarMeal, recyclerViewInstructions;
    RequestManager manager;
    ProgressDialog pd;
    IngredientAdapter adapter;
    SimilarRecipeAdapter similarRecipeAdapter;
    InstructionsAdapter instructionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        initViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));

        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, id);

        manager.getSimilarRecipes(similarRecipesListener, id);

        manager.getInstructions(instructionsListeners, id);

        pd = new ProgressDialog(this);
        pd.setTitle("Loading Details ...");
        pd.setMessage("Loading this Recipe, please wait !");
        pd.show();
    }

    private void initViews() {
        textViewMealName = findViewById(R.id.txtViewMealName);
        textViewMealSource = findViewById(R.id.txtViewMealSource);
        textViewMealSummery = findViewById(R.id.txtViewMealSummery);
        imageViewMeal = findViewById(R.id.imageViewMealImage);
        recyclerViewIngredients = findViewById(R.id.recyclerViewMealIngredients);
        recyclerViewSimilarMeal = findViewById(R.id.recyclerViewSimilarMeal);
        recyclerViewInstructions = findViewById(R.id.recyclerViewMealInstructions);
    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            pd.dismiss();
            textViewMealName.setText(response.title);
            textViewMealSource.setText(response.sourceName);
            textViewMealSummery.setText(response.summary);

            Picasso
                    .get()
                    .load(response.image)
                    .placeholder(R.drawable.loading)
                    .into(imageViewMeal);

            recyclerViewIngredients.setHasFixedSize(true);
            recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            adapter = new IngredientAdapter(RecipeDetailsActivity.this, response.extendedIngredients);
            recyclerViewIngredients.setAdapter(adapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, "Some Error Occurred ...", Toast.LENGTH_SHORT).show();
        }
    };

    private final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> response, String message) {
            recyclerViewSimilarMeal.setHasFixedSize(true);
            recyclerViewSimilarMeal.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            similarRecipeAdapter = new SimilarRecipeAdapter(RecipeDetailsActivity.this, response, recipeClickListener);
            recyclerViewSimilarMeal.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecipeDetailsActivity.this, RecipeDetailsActivity.class).putExtra("id", id));
        }
    };

    private final InstructionsListeners instructionsListeners = new InstructionsListeners() {
        @Override
        public void didFetch(List<InstructionResponse> response, String message) {
            recyclerViewInstructions.setHasFixedSize(true);
            recyclerViewInstructions.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
            instructionsAdapter = new InstructionsAdapter(RecipeDetailsActivity.this, response);
            recyclerViewInstructions.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}