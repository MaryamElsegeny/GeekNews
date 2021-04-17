package com.example.geeknews.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.adapters.RadioButtonAdapter;
import com.example.geeknews.models.RadioButtonModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.MODE_PRIVATE;

public class BottomSheetTypeAndDate extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    private TextView typeFilterTv ;
    private ImageView typeFilterIV;
    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor ;
    private String date ;
    private RadioButtonAdapter radioButtonAdapter ;
    private ArrayList<RadioButtonModel> radioButtonModelArrayList = new ArrayList<>();
    private RecyclerView rv;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_type_date, container, false);
        typeFilterTv=v.findViewById(R.id.typeFilterTV);
        typeFilterIV = v.findViewById(R.id.typeFilterIV);
        rv = v.findViewById(R.id.radio_button_rv);

        typeFilterText();
        setUpRadioBtnRecyclerView();
        addDataToList();
        clickRv();

        return v;
    }

    private void typeFilterText(){
        sharedPreferences = requireContext().getSharedPreferences("type", MODE_PRIVATE);
        date = sharedPreferences.getString("type", "");

        if (date.equals("date"))
        {
            typeFilterTv.setText("Date");
            Drawable drawable = getResources().getDrawable(R.drawable.date);
            typeFilterIV.setImageDrawable(drawable);
        }
        else if (date.equals("type")){
            typeFilterTv.setText("Type");
            Drawable drawable = getResources().getDrawable(R.drawable.type);
            typeFilterIV.setImageDrawable(drawable);

        }
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
        radioButtonAdapter = new RadioButtonAdapter(radioButtonModelArrayList, requireContext());
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(radioButtonAdapter);
        rv.setHasFixedSize(true);

        radioButtonAdapter.notifyDataSetChanged();
    }

    private void addDataToList() {
        RadioButtonModel postModel = new RadioButtonModel("Article");
        RadioButtonModel postModel1 = new RadioButtonModel("Poster");

        radioButtonModelArrayList.add(postModel);
        radioButtonModelArrayList.add(postModel1);
        radioButtonModelArrayList.add(postModel);
        radioButtonModelArrayList.add(postModel1);
        radioButtonModelArrayList.add(postModel);
        radioButtonModelArrayList.add(postModel1);
        radioButtonModelArrayList.add(postModel);
        radioButtonModelArrayList.add(postModel1);

    }

    private void clickRv() {
        rv.addOnItemTouchListener(new RecyclerTouchListener(requireContext(), rv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                dismiss();
                BottomSheetFilter bottomSheet = new BottomSheetFilter();
                bottomSheet.show(requireActivity().getSupportFragmentManager(), "exampleBottomSheet");

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}