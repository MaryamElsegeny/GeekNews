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

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.geeknews.R;
import com.example.geeknews.adapters.CategoriesAdapter;
import com.example.geeknews.classes.BottomSheetFilter;
import com.example.geeknews.classes.BottomSheetDate;
import com.example.geeknews.classes.BottomSheetType;
import com.example.geeknews.classes.RecyclerTouchListener;
import com.example.geeknews.interfaces.DrawerLocker;
import com.example.geeknews.models.Model;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DrawerLocker , BottomSheetFilter.BottomSheetListener , BottomSheetDate.BottomSheetListener ,  BottomSheetType.BottomSheetListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout ;
    private RecyclerView rv ;
    private CategoriesAdapter categoriesAdapter ;
    private ArrayList<Model> modelArrayList = new ArrayList<>();
    private ActionBarDrawerToggle actionBarDrawerToggle ;
    private Model model ;
    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor;
    private NavController navController ;
    private NavGraph navGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setUpRecyclerView();
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
        clickRv();


    }

    private void addDataToList() {
        Model orderModel1= new Model("Software Engineering" , "SoftwareEngineering");
        Model orderModel2 = new Model("Programming Languages" , "Programming Languages, Compilers, Interpreters");
        Model orderModel3 = new Model("Database Management" , "Database Management");
        Model orderModel4 = new Model("Artificial Intelligence" , "AI");
        Model orderModel5 = new Model("Algorithm" , "Algorithm Analysis and Problem Complexity");
        Model orderModel6 = new Model("Data Mining" , "Data Mining and Knowledge Discovery");
        Model orderModel7 = new Model("Information Systems" , "Management of Computing and Information Systems");
        Model orderModel8 = new Model("Retrieve Information","Information Storage and Retrieval");


        modelArrayList.add(orderModel1);
        modelArrayList.add(orderModel2);
        modelArrayList.add(orderModel3);
        modelArrayList.add(orderModel4);
        modelArrayList.add(orderModel5);
        modelArrayList.add(orderModel6);
        modelArrayList.add(orderModel7);
        modelArrayList.add(orderModel8);

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

                sharedPreferences = getSharedPreferences("category name in navDrawer", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString("name",model.getNameCategory());
                editor.putString("topic",model.getCategory());
                editor.putString("pass","pass");

                editor.apply();

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