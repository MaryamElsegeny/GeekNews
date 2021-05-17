package com.example.geeknews.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geeknews.R;
import com.example.geeknews.adapters.RadioButtonDateAdapter;
import com.example.geeknews.models.RadioButtonModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BottomSheetDate extends BottomSheetDialogFragment {

    private BottomSheetListener mListener;
    private RadioButtonDateAdapter radioButtonDateAdapter;
    private ArrayList<RadioButtonModel> radioButtonModelArrayList = new ArrayList<>();
    private RecyclerView rv;
    private String startDate ;
    private String endDate ;
    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor;
    private SharedViewModel sharedViewModel;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_date, container, false);

        rv = v.findViewById(R.id.radio_button_date_rv);

        setUpRadioBtnRecyclerView();
        addDataToListDate();
        clickRv();
        checkClickRv();

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
        radioButtonDateAdapter = new RadioButtonDateAdapter(radioButtonModelArrayList, requireContext());
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(radioButtonDateAdapter);
        rv.setHasFixedSize(true);

        radioButtonDateAdapter.notifyDataSetChanged();
    }


    private void addDataToListDate() {

        RadioButtonModel postModel = new RadioButtonModel("2009","2009-1-1" ,"2009-12-31");
        RadioButtonModel postModel1 = new RadioButtonModel("2010","2010-1-1" ,"2010-12-31");
        RadioButtonModel postModel2 = new RadioButtonModel("2011","2011-1-1" ,"2011-12-31");
        RadioButtonModel postModel3 = new RadioButtonModel("2012","2012-1-1" ,"2012-12-31");
        RadioButtonModel postModel4 = new RadioButtonModel("2013","2013-1-1" ,"2013-12-31");
        RadioButtonModel postModel5 = new RadioButtonModel("2014","2014-1-1" ,"2014-12-31");
        RadioButtonModel postModel6 = new RadioButtonModel("2015","2015-1-1" ,"2015-12-31");
        RadioButtonModel postModel7 = new RadioButtonModel("2016","2016-1-1" ,"2016-12-31");
        RadioButtonModel postModel8 = new RadioButtonModel("2017","2017-1-1" ,"2017-12-31");
        RadioButtonModel postModel9 = new RadioButtonModel("2018","2018-1-1" ,"2018-12-31");
        RadioButtonModel postModel10 = new RadioButtonModel("2019","2019-1-1" ,"2019-12-31");
        RadioButtonModel postModel11 = new RadioButtonModel("2020","2020-1-1" ,"2020-12-31");
        RadioButtonModel postModel12 = new RadioButtonModel("2021","2021-1-1" ,"2021-12-31");


        radioButtonModelArrayList.add(postModel);
        radioButtonModelArrayList.add(postModel1);
        radioButtonModelArrayList.add(postModel2);
        radioButtonModelArrayList.add(postModel3);
        radioButtonModelArrayList.add(postModel4);
        radioButtonModelArrayList.add(postModel5);
        radioButtonModelArrayList.add(postModel6);
        radioButtonModelArrayList.add(postModel7);
        radioButtonModelArrayList.add(postModel8);
        radioButtonModelArrayList.add(postModel9);
        radioButtonModelArrayList.add(postModel10);
        radioButtonModelArrayList.add(postModel11);
        radioButtonModelArrayList.add(postModel12);



    }
    private void checkClickRv(){
        sharedPreferences = requireActivity().getSharedPreferences("type or date", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (clickRv()){
            editor.putString("startDate",startDate);
            editor.putString("endDate",endDate);
            editor.apply();
        }
        else {
            editor.putString("startDate","");
            editor.putString("endDate","");
            editor.apply();
        }
    }
    private boolean clickRv() {
        rv.addOnItemTouchListener(new RecyclerTouchListener(requireContext(), rv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                dismiss();
                RadioButtonModel radioButtonModel = radioButtonModelArrayList.get(position);
                startDate = radioButtonModel.getRadBtnDateStart();
                endDate=radioButtonModel.getRadBtnDateEnd();






                sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
                sharedViewModel.selectDateAndType(radioButtonModel);

                BottomSheetFilter bottomSheet = new BottomSheetFilter();
                bottomSheet.show(requireActivity().getSupportFragmentManager(), "exampleBottomSheet");

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return  true ;
    }
}