package com.example.geeknews.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.classes.SpacesItemDecoration;
import com.example.geeknews.models.ChildModelAuther;
import com.example.geeknews.models.PostModel;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static java.security.AccessController.getContext;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private ArrayList<PostModel> postModelArrayList;
    private Context context ;

    public PostAdapter(ArrayList<PostModel> postModelArrayList, Context context) {
        this.postModelArrayList = postModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_rv , parent , false);
        PostViewHolder postViewHolder = new PostViewHolder(view);
        return postViewHolder;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostModel postModel = postModelArrayList.get(position);
        holder.postTypeTV.setText(postModel.getPostType());
        holder.postAbstractTV.setText(postModel.getPostAbstract());
        holder.postDateTV.setText(postModel.getPostDate());

//        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(context , LinearLayoutManager.HORIZONTAL );
//        layoutManager.setFlexDirection(FlexDirection.ROW);
//        layoutManager.setFlexWrap(FlexWrap.WRAP);
//        layoutManager.setJustifyContent(JustifyContent.FLEX_START);

//        holder.postAutherNameRV.setLayoutManager(layoutManager);



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.postAutherNameRV.setLayoutManager(layoutManager);


//        holder.postAutherNameRV.setLayoutManager(new GridLayoutManager(context, 2));

        holder.postAutherNameRV.setHasFixedSize(true);

        ArrayList<ChildModelAuther> arrayList = new ArrayList<>();

//            arrayList.add(new ChildModelAuther("Mohammmed"));
            arrayList.add(new ChildModelAuther("yasser"));
//            arrayList.add(new ChildModelAuther("nour"));
//            arrayList.add(new ChildModelAuther("ramy"));
//            arrayList.add(new ChildModelAuther("ahmed"));
//            arrayList.add(new ChildModelAuther("Dwarakanath "));
//        arrayList.add(new ChildModelAuther("Mohammmed"));
//        arrayList.add(new ChildModelAuther("yasser"));
//        arrayList.add(new ChildModelAuther("nour"));
//        arrayList.add(new ChildModelAuther("ramy"));
//        arrayList.add(new ChildModelAuther("ahmed"));
//        arrayList.add(new ChildModelAuther("Visweswaran "));

        ChildAutherAdapter childAutherAdapter = new ChildAutherAdapter(arrayList,holder.postAutherNameRV.getContext());
        holder.postAutherNameRV.setAdapter(childAutherAdapter);
    }




    @Override
    public int getItemCount() {
        return postModelArrayList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

       private TextView postTypeTV;
        private TextView postAbstractTV;
        private TextView postDateTV;
        private RecyclerView  postAutherNameRV;



        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            postTypeTV = itemView.findViewById(R.id.postTypeTV);
            postAbstractTV = itemView.findViewById(R.id.postAbstractTV);
            postDateTV = itemView.findViewById(R.id.postDateTV);
            postAutherNameRV = itemView.findViewById(R.id.postAutherNameRV);

        }
    }

}

