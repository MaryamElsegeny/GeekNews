package com.example.geeknews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.geeknews.R;
import com.example.geeknews.models.RadioButtonModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RadioButtonDateAdapter extends RecyclerView.Adapter<RadioButtonDateAdapter.RadioButtonViewHolder>{

    private ArrayList<RadioButtonModel> modelArrayList;

    private Context context ;

    public RadioButtonDateAdapter(ArrayList<RadioButtonModel> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RadioButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.radio_button_rv , parent , false);
        RadioButtonViewHolder radioButtonViewHolder = new RadioButtonViewHolder(view);
        return radioButtonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RadioButtonViewHolder holder, int position) {

        RadioButtonModel radioButtonModel = modelArrayList.get(position);
        holder.filterRadioBtn.setText(radioButtonModel.getRadBtnDate());

        holder.filterRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.filterRadioBtn.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_radio_button_checked_black_24dp,0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    class RadioButtonViewHolder extends RecyclerView.ViewHolder {

        private RadioButton filterRadioBtn;


        public RadioButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            filterRadioBtn = itemView.findViewById(R.id.radio_button);
        }
    }
}
