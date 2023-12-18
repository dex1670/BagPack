package com.example.bagpack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder>{
    List<String> title;
    List<Integer> images;
    LayoutInflater inflater;
    Activity activity;

    public Adapter(Context context, List<String> title, List<Integer> images, Activity activity) {
        this.title = title;
        this.images = images;
        this.activity = activity;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View view = inflater.inflate(R.layout.main_item_holder,
                parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder,int position) {
        holder.title.setText(title.get(position));
        holder.img.setImageResource(images.get(position));
        holder.linear.setAlpha(0.8F);

        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent   = new Intent(v.getContext(),CheckList.class);
                intent.putExtra(Constants.HEADER_SMALL,title.get(position));
                if (Constants.MY_SELECTIONS.equals(title.get(position))){
                    intent.putExtra(Constants.SHOW_SMALL,
                            Constants.FALSE_STRING);
                } else {
                    intent.putExtra(Constants.SHOW_SMALL,
                            Constants.TRUE_STRING);
                }
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;
        LinearLayout linear;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.img);
            linear = itemView.findViewById(R.id.linear_layout);
        }
    }
}
