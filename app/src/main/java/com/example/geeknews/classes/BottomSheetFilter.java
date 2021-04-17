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

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class BottomSheetFilter extends BottomSheetDialogFragment implements BottomSheetTypeAndDate.BottomSheetListener {
    private BottomSheetListener mListener;
    private ConstraintLayout type ;
    private ConstraintLayout date ;
   private SharedPreferences sharedPreferences ;
   private SharedPreferences.Editor editor ;
   private Button applyBtn ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_filter, container, false);
        type = v.findViewById(R.id.consType);
        date = v.findViewById(R.id.consDate);
        applyBtn=v.findViewById(R.id.applyBtn);
        clickDateAndType();
        clickApplyBtn();
        return v;
    }



    private void clickDateAndType(){
        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeShared();
                dismiss();
                BottomSheetTypeAndDate bottomSheetTypeAndDate = new BottomSheetTypeAndDate();
                bottomSheetTypeAndDate.show(requireActivity().getSupportFragmentManager(), "exampleBottomSheet");


            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateShared();
                dismiss();
                BottomSheetTypeAndDate bottomSheetTypeAndDate = new BottomSheetTypeAndDate();
                bottomSheetTypeAndDate.show(requireActivity().getSupportFragmentManager(), "exampleBottomSheet");
            }
        });
    }
    private void clickApplyBtn(){

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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
    private void dateShared(){
        sharedPreferences = requireActivity().getSharedPreferences("type", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("type", "date");
        editor.apply();
    }
    private void typeShared(){
        sharedPreferences = requireActivity().getSharedPreferences("type", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("type", "type");
        editor.apply();
    }
}