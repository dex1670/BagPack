package com.example.bagpack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckAdapter extends RecyclerView.Adapter<CheckListViewHolder> {

    Context context;
    List<Items> itemsList;
    RoomDB database;
    String show;

    public CheckAdapter() {
    }

    public CheckAdapter(Context context, List<Items> itemsList, RoomDB database, String show) {
        this.context = context;
        this.itemsList = itemsList;
        this.database = database;
        this.show = show;
        if (itemsList.size()==0){
            Toast.makeText(context.getApplicationContext(), "Nothing to Show", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public CheckListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckListViewHolder(LayoutInflater.from(context).inflate(R.layout.check_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckListViewHolder holder, int position) {

        holder.checkBox.setText(itemsList.get(position).getItemname());
        holder.checkBox.setChecked(itemsList.get(position).getChecked());
        if (Constants.FALSE_STRING.equals(show)){
            holder.btnDelete.setVisibility(View.GONE);
            holder.layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.borderone));
        } else{
            if (itemsList.get(position).getChecked()){
                holder.layout.setBackgroundColor(Color.parseColor("#8e546f"));
            }else{
                holder.layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.borderone));
            }
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean check = holder.checkBox.isChecked();
                database.mainDao().checkedOrUnchecked(itemsList.get(position).getID(),check);
                if (Constants.FALSE_STRING.equals(show)){
                    itemsList=database.mainDao().getAllSelected(true);
                    notifyDataSetChanged();
                }
                else {
                    itemsList.get(position).setChecked(check);
                    notifyDataSetChanged();
                    Toast toastMessege=null;
                    if (toastMessege!=null){
                        toastMessege.cancel();
                    }
                    if (itemsList.get(position).getChecked()){
                        toastMessege = Toast.makeText(context,"("+holder.checkBox.getText()+") Packed",Toast.LENGTH_SHORT);
                    }else{
                        toastMessege = Toast.makeText(context,"("+holder.checkBox.getText()+") Un-Packed",Toast.LENGTH_SHORT);
                    }
                    toastMessege.show();
                }
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete ("+itemsList.get(position).getItemname()+")")
                        .setMessage("Are you Sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database.mainDao().delete(itemsList.get(position));
                                itemsList.remove(itemsList.get(position));
                                notifyDataSetChanged();
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setIcon(R.drawable.ic_delete).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}

class CheckListViewHolder extends RecyclerView.ViewHolder{

    LinearLayout layout;
    CheckBox checkBox;
    Button btnDelete;
    public CheckListViewHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView.findViewById(R.id.lllayout);
        checkBox = itemView.findViewById(R.id.checkbox);
        btnDelete=itemView.findViewById(R.id.btnDelete);


    }
}
