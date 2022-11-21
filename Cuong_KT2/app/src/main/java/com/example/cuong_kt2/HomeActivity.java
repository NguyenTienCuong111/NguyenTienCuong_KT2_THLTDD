package com.example.cuong_kt2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CayAdapter cayAdapter;
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