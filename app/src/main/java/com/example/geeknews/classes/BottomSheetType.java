package com.example.geeknews.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapters.RadioButtonTypeAdapter;
import com.example.geeknews.models.RadioButtonModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BottomSheetType extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;

    public RadioButtonTypeAdapter radioButtonTypeAdapter;
    public ArrayList<RadioButtonModel> radioButtonModelArrayList = new ArrayList<>();
    private RecyclerView rv;
    private String type ;
    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor;
    private SharedViewModel sharedViewModel;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_type, container, false);

        rv = v.findViewById(R.id.radio_button_type_rv);

        setUpRadioBtnRecyclerView();
        addDataToListType();

        clickRv();
//        checkClickRv();

        return v;
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

    private void setUpRadioBtnRecyclerView() {
        radioButtonTypeAdapter = new RadioButtonTypeAdapter(radioButtonModelArrayList, requireContext());
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(radioButtonTypeAdapter);
        rv.setHasFixedSize(true);

        radioButtonTypeAdapter.notifyDataSetChanged();
    }

    private void addDataToListType() {

        RadioButtonModel postModel = new RadioButtonModel("Chapter");
        RadioButtonModel postModel1 = new RadioButtonModel("Article");
        RadioButtonModel postModel2 = new RadioButtonModel("Chapter ConferencePaper");
        RadioButtonModel postModel3 = new RadioButtonModel("Chapter ReferenceWorkEntry");

        radioButtonModelArrayList.add(postModel);
        radioButtonModelArrayList.add(postModel1);
        radioButtonModelArrayList.add(postModel2);
        radioButtonModelArrayList.add(postModel3);



    }



    private void clickRv() {
        rv.addOnItemTouchListener(new RecyclerTouchListener(requireContext(), rv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                dismiss();
                RadioButtonModel radioButtonModel = radioButtonModelArrayList.get(position);
                type=radioButtonModel.getRadBtnType();




                sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
                sharedViewModel.selectDateAndType(radioButtonModel);




                BottomSheetFilter bottomSheet = new BottomSheetFilter();
                bottomSheet.show(requireActivity().getSupportFragmentManager(), "exampleBottomSheet");


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
}