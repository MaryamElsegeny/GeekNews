package com.example.geeknews.fragments;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geeknews.R;
import com.example.geeknews.adapters.PostAdapter;
import com.example.geeknews.classes.BottomSheetFilter;
import com.example.geeknews.classes.RecyclerTouchListener;
import com.example.geeknews.interfaces.DrawerLocker;
import com.example.geeknews.models.PostModel;
import com.example.geeknews.models.ResultsModel;
import com.example.geeknews.retrofit.ApiInterface;
import com.example.geeknews.retrofit.RetrofitFactory;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment implements BottomSheetFilter.BottomSheetListener {

private View view ;
private ConstraintLayout filterIV ;
private PostAdapter postAdapter ;
private ArrayList<PostModel> postModelArrayList = new ArrayList<>();
private RecyclerView rv;

    private ApiInterface apiInterface;
    private String categoryName;
    private String categoryNameSideMenu;

    private String scirnceTopic;
    private String scirnceTopicSideMenu;

    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor;
    private TextView scienceTopicTV;
    private ProgressBar progressBar ;

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
        view = inflater.inflate(R.layout.fragment_home, container, false);
        filterIV = view.findViewById(R.id.filterconstaraint);
        rv = view.findViewById(R.id.post_rv);
        scienceTopicTV=view.findViewById(R.id.topicTV);
        progressBar=view.findViewById(R.id.progressBar);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navGraph = navController.getNavInflater().inflate(R.navigation.home_nav);
        return view ;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((DrawerLocker) getActivity()).setDrawerEnabled(true);
        progressBar.setVisibility(View.VISIBLE);


        getCategoryNameFromCategoriesFragment();
        getCategoryNameFromSideMenu();
        setScienceTopicText();


      if (navController.getGraph().getStartDestination() == R.id.homeFragment){
          getPostFromActivity();

      }
        else {

          getPost();

        }
        onBackPressed();
        clickFilterIv();
        setUpPostRecyclerView();
        clickRv();


    }
    private void clickFilterIv(){
        filterIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterIV.getBackground().setAlpha(45);

                BottomSheetFilter bottomSheet = new BottomSheetFilter();
                bottomSheet.show(requireActivity().getSupportFragmentManager(), "exampleBottomSheet");

            }
        });
    }
    private void setUpPostRecyclerView() {
        postAdapter = new PostAdapter(postModelArrayList, requireContext());
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(postAdapter);
        rv.setHasFixedSize(true);

        postAdapter.notifyDataSetChanged();
    }

    private void clickRv() {
        rv.addOnItemTouchListener(new RecyclerTouchListener(requireContext(), rv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_postFragment);


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
    private void setScienceTopicText(){
        if (navController.getGraph().getStartDestination() == R.id.homeFragment){
            scienceTopicTV.setText(scirnceTopicSideMenu);

        }
        else {

            scienceTopicTV.setText(scirnceTopic);

        }
    }

    public void onBackPressed() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
//                Navigation.findNavController(view).popBackStack();
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_categoriesFragment);

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);

    }
    public void getPost() {
        apiInterface = RetrofitFactory.getRetrofit().create(ApiInterface.class);
        Call<ResultsModel> getLineClients = apiInterface.getPosts(categoryName);
        getLineClients.enqueue(new Callback<ResultsModel>() {
            @Override
            public void onResponse(Call<ResultsModel> call, Response<ResultsModel> response) {
                if (response.code() == 200) {
                    progressBar.setVisibility(View.INVISIBLE);

                    postModelArrayList.clear();
                    postModelArrayList.addAll(response.body().getPostModelList());
                    postAdapter.notifyDataSetChanged();



                } else {

                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(requireContext(), "" + response.code(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResultsModel> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show();

            }
        });
    }
    public void getPostFromActivity() {
        apiInterface = RetrofitFactory.getRetrofit().create(ApiInterface.class);
        Call<ResultsModel> getLineClients = apiInterface.getPosts(categoryNameSideMenu);
        getLineClients.enqueue(new Callback<ResultsModel>() {
            @Override
            public void onResponse(Call<ResultsModel> call, Response<ResultsModel> response) {
                if (response.code() == 200) {
                    progressBar.setVisibility(View.INVISIBLE);

                    postModelArrayList.clear();
                    postModelArrayList.addAll(response.body().getPostModelList());
                    postAdapter.notifyDataSetChanged();



                } else {

                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(requireContext(), "" + response.code(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResultsModel> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void getCategoryNameFromCategoriesFragment(){

        sharedPreferences = requireContext().getSharedPreferences("category name", MODE_PRIVATE);
        categoryName = sharedPreferences.getString("name", "");
        scirnceTopic = sharedPreferences.getString("topic", "");

    }
    private void getCategoryNameFromSideMenu(){
        sharedPreferences = requireContext().getSharedPreferences("category name in navDrawer", MODE_PRIVATE);
        categoryNameSideMenu = sharedPreferences.getString("name", "");
        scirnceTopicSideMenu = sharedPreferences.getString("topic", "");

    }

    @Override
    public void onButtonClicked(String text) {

    }
}