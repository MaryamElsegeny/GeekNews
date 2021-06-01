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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geeknews.R;
import com.example.geeknews.classes.BottomSheetType;
import com.example.geeknews.classes.SharedViewModel;
import com.example.geeknews.models.NotficationModel;
import com.example.geeknews.retrofit.ApiInterface;
import com.example.geeknews.retrofit.RetrofitFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


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
    private SharedViewModel sharedViewModel;

    private ApiInterface apiInterface;
    private String  isTokenSend = "false" ;
    private String token ;


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
            getTokenShared();
            getToken();
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                @Override
                public void onComplete(@NonNull Task<String> task) {
                    Log.d(TAG, "onComplete: "+ task.getResult());
                    if (isTokenSend=="false"){
                        postLogin(task);
                    }
                }

            });
    }
    private void postLogin(Task<String> task) {
        apiInterface = RetrofitFactory.getRetrofit().create(ApiInterface.class);

        NotficationModel notficationModel = new NotficationModel(task.getResult(), "android");
        Call<NotficationModel> insertToken = apiInterface.insertToken(notficationModel , "Bearer "+token);
        insertToken.enqueue(new Callback<NotficationModel>() {
            @Override
            public void onResponse(Call<NotficationModel> call, Response<NotficationModel> response) {
             if (response.code()==201) {
                 isTokenSend = "true";
                 sharedPreferences = requireContext().getSharedPreferences("GeekNews", 0);
                 editor = sharedPreferences.edit();
                 editor.putString("token1", isTokenSend);
                 editor.commit();
             }
            }

            @Override
            public void onFailure(Call<NotficationModel> call, Throwable t) {
                Toast.makeText(requireContext(), ""+t, Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void getToken(){
        sharedPreferences =  requireContext().getSharedPreferences("GeekNews", 0);
        isTokenSend = sharedPreferences.getString("token1", "false");
    }
    private void getTokenShared() {

        sharedPreferences = requireActivity().getSharedPreferences("GeekNews", MODE_PRIVATE);
        token = sharedPreferences.getString("token","");
    }
    private void clickCategories(){
        allBranches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allBranches.getBackground().setAlpha(180);
                nameCategory="";
                scienceTopic="All branches";
                collectData();

            }
        });
        softwareEngineering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                softwareEngineering.getBackground().setAlpha(180);
                nameCategory="Software Engineering";
                scienceTopic="Software Engineering";
                collectData();

            }
        });
        programmingLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programmingLanguage.getBackground().setAlpha(180);
                nameCategory="Programming Languages Compilers Interpreters";
                scienceTopic="Programming Language";
                collectData();
            }
        }); databaseManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseManagement.getBackground().setAlpha(180);
                nameCategory="Database Management";
                scienceTopic="Database Management";
                collectData();

            }
        }); artificialIntelligence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                artificialIntelligence.getBackground().setAlpha(180);
                nameCategory="AI";
                scienceTopic="Artificial Intelligence";
                collectData();

            }
        }); algorithm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                algorithm.getBackground().setAlpha(180);
                nameCategory="Algorithm Analysis and Problem Complexity";
                scienceTopic="Algorithm";
                collectData();

            }
        }); dataMining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataMining.getBackground().setAlpha(180);
                nameCategory="Data Mining and Knowledge Discovery";
                scienceTopic="Data Mining";
                collectData();

            }
        }); informationSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                informationSystem.getBackground().setAlpha(180);
                nameCategory="Management of Computing and Information Systems";
                scienceTopic="Information Systems";

                collectData();

            }
        }); retrieveInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveInformation.getBackground().setAlpha(180);
                nameCategory="Information Storage and Retrieval";
                scienceTopic="Retrieve Information";

              collectData();
            }
        });

    }
    private void collectData()
    {
        saveCategory();
        navGraph.setStartDestination(R.id.categoriesFragment);
        navController.setGraph(navGraph);
        Navigation.findNavController(view).navigate(R.id.action_categoriesFragment_to_homeFragment);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.select("catergory");

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
        sharedPreferences = requireActivity().getSharedPreferences("GeekNews", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("name",nameCategory);
        editor.putString("topic",scienceTopic);


        editor.apply();
    }

}