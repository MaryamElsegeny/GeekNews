package com.example.geeknews.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.geeknews.R;


public class CategoriesFragment extends Fragment {

    private View view ;
    private LinearLayout allBranches ;
    private TextView category1;
    private TextView category2;
    private TextView category3;
    private TextView category4;
    private TextView category5;
    private TextView category6;
    private TextView category7;
    private TextView category8;


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
         category1 = view.findViewById(R.id.categoey1);
        category2 = view.findViewById(R.id.categoey2);
        category3 = view.findViewById(R.id.categoey3);
        category4 = view.findViewById(R.id.categoey4);
        category5 = view.findViewById(R.id.categoey5);
        category6 = view.findViewById(R.id.categoey6);
        category7 = view.findViewById(R.id.categoey7);
        category8 = view.findViewById(R.id.categoey8);

        return view ;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
onBackPressed();
clickCategories();

    }

    private void clickCategories(){
        allBranches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allBranches.getBackground().setAlpha(180);

                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        });
        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category1.getBackground().setAlpha(180);

                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        });
        category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category2.getBackground().setAlpha(180);

                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        }); category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category3.getBackground().setAlpha(180);

                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        }); category4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category4.getBackground().setAlpha(180);

                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        }); category5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category5.getBackground().setAlpha(180);

                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        }); category6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category6.getBackground().setAlpha(180);

                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        }); category7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category7.getBackground().setAlpha(180);

                Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_homeFragment);
            }
        }); category8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category8.getBackground().setAlpha(180);
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

}