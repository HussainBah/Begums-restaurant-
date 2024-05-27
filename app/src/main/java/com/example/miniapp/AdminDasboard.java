package com.example.miniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminDasboard extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    Button add, delete,update,log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dasboard);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        log_out = findViewById(R.id.logout_btn);
        add = findViewById(R.id.add_btn);
        delete = findViewById(R.id.delete_btn);
        update = findViewById(R.id.update_btn);

        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddDashboard.class);
                startActivity(intent);

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), DeleteDashboard.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), UpdateDashboard.class);
                startActivity(intent);

            }
        });
    }
}