package com.example.miniapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class MenuDetail extends AppCompatActivity {

    TextView detailName, detailDes, detailPrice;
    ImageView detailImage;
    Button detailBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        detailName = findViewById(R.id.detail_name);
        detailImage = findViewById(R.id.detail_image);
        detailDes = findViewById(R.id.detail_description);
        detailPrice = findViewById(R.id.detail_price);
        detailBtn = findViewById(R.id.detail_btn);


        detailName.setText(getIntent().getStringExtra("uname").toString());
        detailDes.setText(getIntent().getStringExtra("udes").toString());
        detailPrice.setText(String.valueOf(getIntent().getFloatExtra("uprice", 2)));
        Glide.with(MenuDetail.this).load(getIntent().getStringExtra("uimage")).into(detailImage);




       // Glide.with(context).load(menu.getImage()).into(holder.menuImage);

        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),order.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_main){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        } else if (item.getItemId() == R.id.menu_out) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}