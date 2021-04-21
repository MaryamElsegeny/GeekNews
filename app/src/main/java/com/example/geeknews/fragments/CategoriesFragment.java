package com.example.geeknews.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geeknews.R;


public class CategoriesFragment extends Fragment {

    private View view ;
    private LinearLayout allBranches ;
    private TextView softwareEngineering;
    private TextView programmingLanguage;
    private TextView databaseManagement;
    private TextView artificialIntelligence;
    private TextView algorithm;
    private TextView dataMining;
    private TextView informationSystem;
    private TextView retrieveInformation;
    private String nameCategory ;
    private String scienceTopic;
    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor;

    private NavController navController ;
    private NavGraph navGraph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_categories, container, false);
         allBranches = view.findViewById(R.id.allBranches);
         softwareEngineering = view.findViewById(R.id.categoey1);
        programmingLanguage = view.findViewById(R.id.categoey2);
        databaseManagement = view.findViewById(R.id.categoey3);
        artificialIntelligence = view.findViewById(R.id.categoey4);
        algorithm = view.findViewById(R.id.categoey5);
        dataMining = view.findViewById(R.id.categoey6);
        informationSystem = view.findViewById(R.id.categoey7);
        retrieveInformation = view.findViewById(R.id.categoey8);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navGraph = navController.getNavInflater().inflate(R.navigation.home_nav);
        return view ;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
            onBackPressed();
            clickCategories();
            saveCategory();

    }

    private void clickCategories(){
        allBranches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allBranches.getBackground().setAlpha(180);
                nameCategory="";
                scienceTopic="All branches";
                saveCategory();
                navGraph.setStartDestination(R.id.categoriesFragment);
                navController.setGraph(navGraph);
                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        });
        softwareEngineering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                softwareEngineering.getBackground().setAlpha(180);
                nameCategory="SoftwareEngineering";
                scienceTopic="Software Engineering";
                saveCategory();
                navGraph.setStartDestination(R.id.categoriesFragment);
                navController.setGraph(navGraph);
                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        });
        programmingLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programmingLanguage.getBackground().setAlpha(180);
                nameCategory="Programming Languages, Compilers, Interpreters";
                scienceTopic="Programming Language";
                saveCategory();
                navGraph.setStartDestination(R.id.categoriesFragment);
                navController.setGraph(navGraph);
                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        }); databaseManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseManagement.getBackground().setAlpha(180);
                nameCategory="Database Management";
                scienceTopic="Database Management";
                saveCategory();
                navGraph.setStartDestination(R.id.categoriesFragment);
                navController.setGraph(navGraph);
                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        }); artificialIntelligence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artificialIntelligence.getBackground().setAlpha(180);
                nameCategory="AI";
                scienceTopic="Artificial Intelligence";
                saveCategory();
                navGraph.setStartDestination(R.id.categoriesFragment);
                navController.setGraph(navGraph);
                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        }); algorithm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                algorithm.getBackground().setAlpha(180);
                nameCategory="Algorithm Analysis and Problem Complexity";
                scienceTopic="Algorithm";

                saveCategory();
                navGraph.setStartDestination(R.id.categoriesFragment);
                navController.setGraph(navGraph);
                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        }); dataMining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataMining.getBackground().setAlpha(180);
                nameCategory="Data Mining and Knowledge Discovery";
                scienceTopic="Data Mining";
                saveCategory();
                navGraph.setStartDestination(R.id.categoriesFragment);
                navController.setGraph(navGraph);
                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        }); informationSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                informationSystem.getBackground().setAlpha(180);
                nameCategory="Management of Computing and Information Systems";
                scienceTopic="Information Systems";

                saveCategory();
                navGraph.setStartDestination(R.id.categoriesFragment);
                navController.setGraph(navGraph);
                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        }); retrieveInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveInformation.getBackground().setAlpha(180);
                nameCategory="Information Storage and Retrieval";
                scienceTopic="Retrieve Information";

                saveCategory();
                navGraph.setStartDestination(R.id.categoriesFragment);
                navController.setGraph(navGraph);
                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        });

    }

    public void onBackPressed() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finishAffinity();

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);

    }


    private void saveCategory(){
        sharedPreferences = requireActivity().getSharedPreferences("category name", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("name",nameCategory);
        editor.putString("topic",scienceTopic);


        editor.apply();
    }

}