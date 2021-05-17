package com.example.geeknews.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.geeknews.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

public class BottomSheetFilter extends BottomSheetDialogFragment implements BottomSheetDate.BottomSheetListener {
    private BottomSheetListener mListener;
    private ConstraintLayout type ;
    private ConstraintLayout date ;
   private SharedPreferences sharedPreferences ;
   private SharedPreferences.Editor editor ;
   private Button applyBtn ;
   public String clickDoneBtn;
    private NavController navController ;
    private NavGraph navGraph;

    private SharedViewModel sharedViewModel;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_filter, container, false);
        type = v.findViewById(R.id.consType);
        date = v.findViewById(R.id.consDate);
        applyBtn=v.findViewById(R.id.applyBtn);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navGraph = navController.getNavInflater().inflate(R.navigation.home_nav);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clickDateAndType();
        clickApplyBtn();
    }

    private void clickDateAndType(){
        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                BottomSheetType bottomSheetType = new BottomSheetType();
                bottomSheetType.show(requireActivity().getSupportFragmentManager(), "exampleBottomSheet");


            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                BottomSheetDate bottomSheetDate = new BottomSheetDate();
                bottomSheetDate.show(requireActivity().getSupportFragmentManager(), "exampleBottomSheet");
            }
        });
    }
    private void clickApplyBtn(){

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();

//                sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//                sharedViewModel.select("done");

            }
        });
    }

    @Override
    public void onButtonClicked(String text) {
    }

    public interface BottomSheetListener {
        void onButtonClicked(String text);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }



}