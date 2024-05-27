package com.example.miniapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import com.example.miniapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class HomeActivity extends AppCompatActivity {

    //ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseUser user;

    RecyclerView myRecycler;
    ArrayList<Menu> menuArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        setContentView(R.layout.activity_home);

        myRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        //myRecycler.setHasFixedSize(true);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));


        menuArrayList = new ArrayList<>();
        myAdapter = new MyAdapter(HomeActivity.this, menuArrayList);
        myRecycler.setAdapter(myAdapter);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        db = FirebaseFirestore.getInstance();

        EventChangeListener();
    }



    private void EventChangeListener() {
        menuArrayList.clear();


        db.collection("Menu").orderBy("name").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d:list){
                            Menu obj = d.toObject(Menu.class);
                            menuArrayList.add(obj);
                        }

                        myAdapter.notifyDataSetChanged();
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

