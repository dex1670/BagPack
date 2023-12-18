package com.example.bagpack;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CheckList extends AppCompatActivity {

    RecyclerView recyclerView;
    CheckAdapter checkAdapter;
    RoomDB database;
    List<Items> itemsList = new ArrayList<>();
    String header , show;

    EditText textAdd;
    Button btnAdd;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
      // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(header);
        }

        Intent intent = getIntent();
        header = intent.getStringExtra(Constants.HEADER_SMALL);
        show = intent.getStringExtra(Constants.SHOW_SMALL);

        //getSupportActionBar().setTitle(header);

        if (actionBar != null) {
            actionBar.setTitle(header);
        }

        textAdd = findViewById(R.id.txtAdd);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recView);
        linearLayout = findViewById(R.id.Lnlayout);

        database = RoomDB.getInstance(this);
        if (Constants.FALSE_STRING.equals(show)){
            linearLayout.setVisibility(View.GONE);
            itemsList = database.mainDao().getAllSelected(true);

        }else {
            itemsList = database.mainDao().getAll(header);
        }
        updateRecycler(itemsList);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = textAdd.getText().toString();
                if (!itemName.isEmpty()){
                    addNewItem(itemName);
                    Toast.makeText(CheckList.this, "Item is added", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CheckList.this, "Empty item cant be added", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void addNewItem(String itemName){
        Items item = new Items();
        item.setChecked(false);
        item.setCategory(header);
        item.setItemname(itemName);
        item.setAddby(Constants.USER_SMALL);
        database.mainDao().saveItem(item);
        itemsList = database.mainDao().getAll(header);
        recyclerView.scrollToPosition(checkAdapter.getItemCount()-2);
        textAdd.setText(" ");
    }

    private void updateRecycler(List<Items> itemsList){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager
                (1, LinearLayoutManager.VERTICAL));
        checkAdapter = new CheckAdapter(CheckList.
                this,itemsList,database,show);
        recyclerView.setAdapter(checkAdapter);
    }
}