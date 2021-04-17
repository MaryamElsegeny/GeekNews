package com.example.geeknews.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapters.PostAdapter;
import com.example.geeknews.classes.BottomSheetFilter;
import com.example.geeknews.classes.RecyclerTouchListener;
import com.example.geeknews.interfaces.DrawerLocker;
import com.example.geeknews.models.PostModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements BottomSheetFilter.BottomSheetListener {

private View view ;
private ConstraintLayout filterIV ;
private PostAdapter postAdapter ;
private ArrayList<PostModel> postModelArrayList = new ArrayList<>();
private RecyclerView rv;
private CardView postCard ;
    private ConstraintLayout type ;
    private ConstraintLayout date ;



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

        return view ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((DrawerLocker) getActivity()).setDrawerEnabled(true);

        onBackPressed();
        clickFilterIv();
        setUpPostRecyclerView();
        addDataToList();
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
    private void addDataToList() {
        PostModel postModel = new PostModel("Article" , "nonlinear control schemes for Unmanned Aerial Vehicles (UAV): "
        , "22-3-2021" );
        PostModel postModel1 = new PostModel("Poster" , "Recently, Computer Science (CS) education has experienced renewed interest "
                , "6-4-2015" );

        postModelArrayList.add(postModel);
        postModelArrayList.add(postModel1);
        postModelArrayList.add(postModel);
        postModelArrayList.add(postModel1);
        postModelArrayList.add(postModel);
        postModelArrayList.add(postModel1);
        postModelArrayList.add(postModel);
        postModelArrayList.add(postModel1);


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

    @Override
    public void onButtonClicked(String text) {

    }
}