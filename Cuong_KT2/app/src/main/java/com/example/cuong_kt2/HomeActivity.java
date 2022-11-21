package com.example.cuong_kt2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CayAdapter cayAdapter;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView =(RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<CayModel> options =
                new FirebaseRecyclerOptions.Builder<CayModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cay"), CayModel.class)
                        .build();

        cayAdapter=new CayAdapter(options);
        recyclerView.setAdapter(cayAdapter);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),AddActivity.class);
                startActivity(intent);
            }
        });



    }
    @Override
    public void onStart() {
        super.onStart();
        cayAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        cayAdapter.stopListening();
    }
}