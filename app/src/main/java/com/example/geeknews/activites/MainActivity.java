package com.example.geeknews.activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.geeknews.R;
import com.example.geeknews.adapters.CategoriesAdapter;
import com.example.geeknews.classes.BottomSheetDialog;
import com.example.geeknews.classes.RecyclerTouchListener;
import com.example.geeknews.fragments.CategoriesFragment;
import com.example.geeknews.interfaces.DrawerLocker;
import com.example.geeknews.models.Model;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DrawerLocker , BottomSheetDialog.BottomSheetListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout ;
    private RecyclerView rv ;
    private CategoriesAdapter categoriesAdapter ;
    private ArrayList<Model> modelArrayList = new ArrayList<>();
    private ActionBarDrawerToggle actionBarDrawerToggle ;
    private Model model ;

    NavController navController ;
    NavGraph navGraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setUpRecyclerView();
        clickRv();
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
         navController = Navigation.findNavController(this, R.id.nav_host_fragment);
         navGraph = navController.getNavInflater().inflate(R.navigation.home_nav);


    }
    private void findId(){
        toolbar =  findViewById(R.id.toolbar_actionbar);
        drawerLayout = findViewById(R.id.drawer_layout);
    }


    private void setUpRecyclerView() {
        rv = findViewById(R.id.category_rv);
        categoriesAdapter = new CategoriesAdapter(modelArrayList, MainActivity.this);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv.addItemDecoration(new DividerItemDecoration(MainActivity.this, 0));
        drawerLayout = findViewById(R.id.drawer_layout);
        rv.setHasFixedSize(true);
        categoriesAdapter.notifyDataSetChanged();
        modelArrayList.clear();
        addDataToList();
        rv.setAdapter(categoriesAdapter);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this , drawerLayout , toolbar , R.string.drawer_open , R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }

    private void addDataToList() {
        Model orderModel = new Model("Data analysis");
        Model orderModel1 = new Model("Software");
        Model orderModel2 = new Model("Algorithm");
        Model orderModel3 = new Model("Network");
        Model orderModel4 = new Model("Data mining");
        Model orderModel5 = new Model("Artificial neural network");

        modelArrayList.add(orderModel);
        modelArrayList.add(orderModel1);
        modelArrayList.add(orderModel2);
        modelArrayList.add(orderModel3);
        modelArrayList.add(orderModel4);
        modelArrayList.add(orderModel5);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    private void clickRv() {
        rv.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this, rv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                model = modelArrayList.get(position);
                Toast.makeText(MainActivity.this, ""+model.getCategory(), Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawers();
                navGraph.setStartDestination(R.id.homeFragment);
                navController.setGraph(navGraph);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void setDrawerEnabled(boolean enabled) {
        int lockMode = enabled ? DrawerLayout.LOCK_MODE_UNLOCKED :
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        drawerLayout.setDrawerLockMode(lockMode);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(enabled);
    }

    @Override
    public void onButtonClicked(String text) {

    }
}