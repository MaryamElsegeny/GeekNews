package com.example.geeknews.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.example.geeknews.classes.SharedViewModel;
import com.example.geeknews.interfaces.DrawerLocker;
import com.example.geeknews.models.PostModel;
import com.example.geeknews.models.ResultsModel;
import com.example.geeknews.retrofit.ApiInterface;
import com.example.geeknews.retrofit.RetrofitFactory;

import java.util.ArrayList;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment implements BottomSheetFilter.BottomSheetListener {

    private View view;
    private ConstraintLayout filterIV;
    private PostAdapter postAdapter;
    private ArrayList<PostModel> postModelArrayList = new ArrayList<>();
    private RecyclerView rv;

    private ApiInterface apiInterface;
    private String categoryName;
    private String categoryNameSideMenu;

    private String scirnceTopic;
    private String scirnceTopicSideMenu;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView scienceTopicTV;
    private ProgressBar progressBar;

    private NavController navController;
    private SearchView searchView;
    private String startDate;
    private String endDate;
    private String type;

    private int page = 1;

    private SharedViewModel sharedViewModel;
    private String searchText;

    // Index from which pagination should start (0 is 1st page in our case)
    private static final int PAGE_START = 0;

    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;

    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;

    // total no. of pages to load. Initial load is page 0, after which 2 more pages will load.
    private int TOTAL_PAGES = 985;

    // indicates the current page which Pagination is fetching.
    private int currentPage = PAGE_START;

     String requestType = "";
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
        scienceTopicTV = view.findViewById(R.id.topicTV);
        progressBar = view.findViewById(R.id.progressBar);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        searchView = view.findViewById(R.id.search_view);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((DrawerLocker) getActivity()).setDrawerEnabled(true);
        progressBar.setVisibility(View.VISIBLE);
        getCategoryNameFromCategoriesFragment();
        caseGetPost();
        onBackPressed();
        clickFilterIv();
        setUpPostRecyclerView();
        clickRv();
        clickSearchView();
        setScienceTopicText();
        getViewModel();
        initScrollListener();
    }

    private void clickFilterIv() {
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
                PostModel postModel = postModelArrayList.get(position);

                sharedPreferences = requireActivity().getSharedPreferences("GeekNews", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putInt("id", postModel.getId());
                Log.d(TAG, "onClick: "+postModel.getId());
                editor.apply();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void setScienceTopicText() {
        getCategoryNameFromSideMenu();

        if (navController.getGraph().getStartDestination() == R.id.homeFragment) {
            scienceTopicTV.setText(scirnceTopicSideMenu);
        } else {

            scienceTopicTV.setText(scirnceTopic);
        }
    }

    public void onBackPressed() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_categoriesFragment);

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);
    }

    public void getPost() {
        apiInterface = RetrofitFactory.getRetrofit().create(ApiInterface.class);
        Call<ResultsModel> getPosts = apiInterface.getPosts(categoryName, page);
        getPosts.enqueue(new Callback<ResultsModel>() {
            @Override
            public void onResponse(Call<ResultsModel> call, Response<ResultsModel> response) {
                if (response.code() == 200) {
                    progressBar.setVisibility(View.INVISIBLE);

                    //postModelArrayList.clear();
                    postModelArrayList.addAll(response.body().getPostModelList());
                    postAdapter.notifyDataSetChanged();
                    isLoading = false;
                    progressBar.setVisibility(View.INVISIBLE);
                    if (response.body().getCount() == 0) {
                        Toast.makeText(requireContext(), "Sorry, we could not find any matches for your search.", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (response.code()==404){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(requireContext(), "No publications more", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultsModel> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d(TAG, "onFailure: "+t);
//                Toast.makeText(requireContext(), "No Internet Connection" , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getPostFromActivity() {

        apiInterface = RetrofitFactory.getRetrofit().create(ApiInterface.class);
        Call<ResultsModel> getPosts = apiInterface.getPosts(categoryNameSideMenu, page);
        getPosts.enqueue(new Callback<ResultsModel>() {
            @Override
            public void onResponse(Call<ResultsModel> call, Response<ResultsModel> response) {
                if (response.code() == 200) {
                    progressBar.setVisibility(View.INVISIBLE);

//                    postModelArrayList.clear();
                    postModelArrayList.addAll(response.body().getPostModelList());
                    isLoading = false;

                    postAdapter.notifyDataSetChanged();
                }   else if (response.code()==404){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(requireContext(), "No publications more", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultsModel> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getCategoryNameFromCategoriesFragment() {

        sharedPreferences = requireContext().getSharedPreferences("GeekNews", MODE_PRIVATE);
        categoryName = sharedPreferences.getString("name", "");
        scirnceTopic = sharedPreferences.getString("topic", "");
    }

    private void getCategoryNameFromSideMenu() {
        sharedPreferences = requireContext().getSharedPreferences("GeekNews", MODE_PRIVATE);
        categoryNameSideMenu = sharedPreferences.getString("name", "");
        scirnceTopicSideMenu = sharedPreferences.getString("topic", "");
    }

    private void clickSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                postModelArrayList.clear();
                getSearchedPost(query);
                requestType = "search" ;
                searchText = query;

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    private void getViewModel() {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getSelectedDateAndType().observe(getViewLifecycleOwner(), radioButtonModel -> {
            if (radioButtonModel != null) {
                if (radioButtonModel.getRadBtnType() != null) {
                    type = radioButtonModel.getRadBtnType();
                }
                if (radioButtonModel.getRadBtnDateStart() != null) {
                    startDate = radioButtonModel.getRadBtnDateStart();
                }
                if (radioButtonModel.getRadBtnDateEnd() != null) {
                    endDate = radioButtonModel.getRadBtnDateEnd();
                }
            }

            if (searchText != null) {
                postModelArrayList.clear();
                getFilter(searchText, startDate, endDate, type);
            } else {
                caseGetPost();
            }
        });
    }

    public void getFilter(String filter, String startDate, String endDate, String typee) {
            requestType = "filter" ;
        apiInterface = RetrofitFactory.getRetrofit().create(ApiInterface.class);
        Call<ResultsModel> getPosts = apiInterface.getFilter(filter, categoryName, startDate, endDate, typee);
        getPosts.enqueue(new Callback<ResultsModel>() {
            @Override
            public void onResponse(Call<ResultsModel> call, Response<ResultsModel> response) {
                if (response.code() == 200) {
                    progressBar.setVisibility(View.INVISIBLE);

//                    postModelArrayList.clear();
                    postModelArrayList.addAll(response.body().getPostModelList());
                    isLoading = false;

                    postAdapter.notifyDataSetChanged();

                    if (response.body().getCount() == 0) {
                        Toast.makeText(requireContext(), "Sorry, we could not find any matches for your search.", Toast.LENGTH_SHORT).show();
                    }
                }   else if (response.code()==404){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(requireContext(), "No publications more", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultsModel> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getSearchedPost(String text) {
        apiInterface = RetrofitFactory.getRetrofit().create(ApiInterface.class);
        Call<ResultsModel> getPosts = apiInterface.getSearch(text, categoryName,page);
        getPosts.enqueue(new Callback<ResultsModel>() {
            @Override
            public void onResponse(Call<ResultsModel> call, Response<ResultsModel> response) {
                if (response.code() == 200) {
                    progressBar.setVisibility(View.INVISIBLE);

//                    postModelArrayList.clear();
                    postModelArrayList.addAll(response.body().getPostModelList());
                    isLoading = false;
                    postAdapter.notifyDataSetChanged();
                    if (response.body().getCount() == 0) {
                        Toast.makeText(requireContext(), "Sorry, we could not find any matches for your search.", Toast.LENGTH_SHORT).show();
                    }
                }   else if (response.code()==404){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(requireContext(), "No publications more", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultsModel> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void caseGetPost() {
        if (navController.getGraph().getStartDestination() == R.id.homeFragment) {
            postModelArrayList.clear();
            getPostFromActivity();
        } else {
            postModelArrayList.clear();
            getPost();
        }
    }

    @Override
    public void onButtonClicked(String text) {
    }

    private void initScrollListener() {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!isLoading) {
                        if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() != -1
                                && linearLayoutManager.findLastCompletelyVisibleItemPosition() == postModelArrayList.size() - 1) {
                        //bottom of list!
                        //loadMore();
                        page = page+1;
                        if (requestType == "search" && postModelArrayList.size()>0) {
                      getSearchedPost(searchText);
                        }else if (requestType == "filter" && postModelArrayList.size()>0) {
                                getFilter(searchText, startDate, endDate, type);
                        }
                        else {
                            if (navController.getGraph().getStartDestination() == R.id.homeFragment) {
                                getPostFromActivity();
                            } else {
                                getPost();
                            }
                        }
                        isLoading = true;
                    }
                }
            }
        });
    }

/*  private void loadMore() {
    postModelArrayList.add(null);
    postAdapter.notifyItemInserted(postModelArrayList.size() - 1);
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        postModelArrayList.remove(postModelArrayList.size() - 1);
        int scrollPosition = postModelArrayList.size();
        postAdapter.notifyItemRemoved(scrollPosition);
        int currentSize = scrollPosition;
        int nextLimit = currentSize + 10;
        while (currentSize - 1 < nextLimit) {
          //postModelArrayList.add("Number " + currentSize);
          currentSize++;
        }
        postAdapter.notifyDataSetChanged();
        isLoading = false;
      }
    }, 2000);
  }*/
}