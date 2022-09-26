package com.example.a04androidrecyclerview;

import android.content.Intent;
import android.os.Bundle;

import com.example.a04androidrecyclerview.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.a04androidrecyclerview.RecipeAdapter;
import com.example.a04androidrecyclerview.RecipeData;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList mWordList = new LinkedList<String>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private ArrayList<RecipeData> recipeList;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRecipeList();
        @NonNull ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, mWordList);
        RecipeAdapter recipeAdapter = new RecipeAdapter(MainActivity.this, recipeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(recipeAdapter);
        recipeAdapter.setOnItemClickListener(onItemClickListener);
    }

    private void setRecipeList() {
        recipeList = new ArrayList<>();
        RecipeData data;
        data = new RecipeData(getString(R.string.gnocchi_name), getString(R.string.gnocchi_description), R.drawable.gnocchi_img, getString(R.string.gnocchi_details));
        recipeList.add(data);
        data = new RecipeData(getString(R.string.pumpkin_pasta_name), getString(R.string.pumpkin_pasta_description), R.drawable.pumpkin_pasta_img, getString(R.string.pumpkin_pasta_details));
        recipeList.add(data);
        data = new RecipeData(getString(R.string.cheese_gnocchi_name), getString(R.string.cheese_gnocchi_description), R.drawable.cheese_gnocchi_img, getString(R.string.cheese_gnocchi_details));
        recipeList.add(data);
        data = new RecipeData(getString(R.string.chicken_pasta_name), getString(R.string.chicken_pasta_description), R.drawable.chicken_pasta_img, getString(R.string.chicken_pasta_details));
        recipeList.add(data);
        data = new RecipeData(getString(R.string.cauliflower_soup_name), getString(R.string.cauliflower_soup_description), R.drawable.cauliflower_soup_img, getString(R.string.cauliflower_soup_details));
        recipeList.add(data);

    }

    public void openDetailActivity(int imageId, String details){
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("image", imageId);
        intent.putExtra("details", details);
        startActivity(intent);
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            RecipeData thisRecipe = recipeList.get(position);
            openDetailActivity(thisRecipe.getImage(), thisRecipe.getDetails());
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}