package com.example.geeknews.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.models.Model;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private ArrayList<Model> modelArrayList;

    private Context context ;

    public CategoriesAdapter(ArrayList<Model> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_side_menu , parent , false);
        CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(view);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {

        Model model = modelArrayList.get(position);
        holder.categoryIv.setText(model.getCategory());
//        holder.row_linearlayout.setBackgroundColor(context.getResources().getColor(android.R.color.white));


    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder {

       private TextView categoryIv;
       private LinearLayout row_linearlayout;


        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIv = itemView.findViewById(R.id.categoryItemSidemenu);
            row_linearlayout = itemView.findViewById(R.id.linear);
        }
    }

}

