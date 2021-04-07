package com.example.geeknews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.models.ChildModelAuther;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChildAutherAdapter  extends RecyclerView.Adapter<ChildAutherAdapter.chilsAutherViewHolder> {
    private ArrayList<ChildModelAuther> childModelAutherArrayList;
    private Context context ;

    public ChildAutherAdapter(ArrayList<ChildModelAuther> childModelAutherArrayList, Context context) {
        this.childModelAutherArrayList = childModelAutherArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public chilsAutherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.auther_rv , parent , false);
        chilsAutherViewHolder chilsAutherViewHolder = new chilsAutherViewHolder(view);
        return chilsAutherViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull chilsAutherViewHolder holder, int position) {
        ChildModelAuther childModelAuther = childModelAutherArrayList.get(position);
        holder.autherTV.setText(childModelAuther.getAuther());
//        if (position == getItemCount()-1){
//            holder.colonTV.setText(" . ");
//
//        }else {
//            holder.colonTV.setText(" , ");
//        }
    }

    @Override
    public int getItemCount() {
        return childModelAutherArrayList.size();
    }

    class chilsAutherViewHolder extends RecyclerView.ViewHolder {

        private TextView autherTV;
        private TextView colonTV;



        public chilsAutherViewHolder(@NonNull View itemView) {
            super(itemView);
            autherTV = itemView.findViewById(R.id.autherTV);
            colonTV = itemView.findViewById(R.id.colonTV);


        }
    }

}
