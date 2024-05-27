package com.example.miniapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

public class DeleteDashboard extends AppCompatActivity {


    FirebaseFirestore db;
    TextInputEditText delItem;
    Button delRec;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_dashboard);

        delItem = findViewById(R.id.delete_item);
        delRec = findViewById(R.id.delete_rec);
        progressBar = findViewById(R.id.progressBar);
        db = FirebaseFirestore.getInstance();

        delRec.setOnClickListener(new View.OnClickListener() {
            //progressBar.setVisibility(View.VISIBLE);
            @Override
            public void onClick(View v) {

                String id = String.valueOf(delItem.getText());

                db.collection("Menu")
                        .document(id)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            //progressBar.setVisibility(View.GONE);
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(DeleteDashboard.this,
                                        "Menu Item successfully Deleted!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DeleteDashboard.this,
                                        "Error Deleting Menu Item", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


    }
}