package com.example.miniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDashboard extends AppCompatActivity {

    FirebaseFirestore db;
    TextInputEditText itemId, itemName, itemDescription, itemUrl;
    Button addRec;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dashboard);

        itemId = (TextInputEditText) findViewById(R.id.add_item_id);
        itemName = (TextInputEditText) findViewById(R.id.add_item_name);
        itemDescription = (TextInputEditText) findViewById(R.id.add_item_description);
        itemUrl = (TextInputEditText) findViewById(R.id.add_item_url);
        addRec = (Button) findViewById(R.id.add_rec);

        db = FirebaseFirestore.getInstance();
        String itemId1 = String.valueOf(itemId.getText());
        String itemName1 = String.valueOf(itemName.getText());
        String itemDes1 = String.valueOf(itemDescription.getText());
        String itemUrl1 = String.valueOf(itemUrl.getText());


        Map<String, Object> itemDoc = new HashMap<>();
        itemDoc.put("Name", itemName1);
        itemDoc.put("Description", itemDes1);
        itemDoc.put("Image", itemUrl1);


        // Add a new document with a new ID
        addRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //For Add or Update data
                db.collection("Menu").document(itemId1)
                        .set(itemDoc)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddDashboard.this,
                                        "Menu Item successfully Added!", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddDashboard.this,
                                        "Error Adding Menu Item", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
}