package com.example.cuong_kt2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    Button btn_save,btn_back;
    EditText edt_tenkhoahoc,edt_tenthuonggoi, edt_dactinh, edt_maula, edt_address_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Anhxa();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserData();
                clearAll();

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this,HomeActivity.class);

                startActivity(intent);

            }
        });
    }
    private void Anhxa() {
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_back=(Button) findViewById(R.id.btn_back);

        edt_tenkhoahoc = findViewById(R.id.edt_tenkhoahoc);
        edt_tenthuonggoi = findViewById(R.id.edt_tenthuonggoi);
        edt_dactinh = findViewById(R.id.edt_dactinh);
        edt_maula = findViewById(R.id.edt_maula);
        edt_address_image = findViewById(R.id.edt_address_image);
    }
    private void inserData(){

        Map<String,Object> map = new HashMap<>();
        map.put("tenkhoahoc",edt_tenkhoahoc.getText().toString());
        map.put("tenthuonggoi",edt_tenthuonggoi.getText().toString());
        map.put("dactinh",edt_dactinh.getText().toString());
        map.put("maula",edt_maula.getText().toString());
        map.put("hinhanh",edt_address_image.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Cay").push()
                .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Successfully completed",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this,"Failure addition",Toast.LENGTH_SHORT).show();

                    }
                });
    }
    private  void clearAll(){
        edt_tenkhoahoc.setText("");
        edt_tenthuonggoi.setText("");
        edt_dactinh.setText("");
        edt_maula.setText("");
        edt_address_image.setText("");
    }
}