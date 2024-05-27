package com.example.miniapp;

import static com.example.miniapp.R.layout.activity_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    TextInputEditText logEmail, logPassword;
    Button logButton;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);

        logEmail = findViewById(R.id.email);
        logPassword = findViewById(R.id.password);
        logButton = findViewById(R.id.login_btn);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.registerNow);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registraion.class);
                startActivity(intent);
                finish();
            }
        });

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(logEmail.getText());
                password = String.valueOf(logPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                auth = FirebaseAuth.getInstance();
                                user = auth.getCurrentUser();

                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Login Successfully",
                                            Toast.LENGTH_SHORT).show();
//                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    if(logEmail.getText().toString().equals("admin@gmail.com")) {
                                        Intent homeIntent = new Intent(getApplicationContext(), AdminDasboard.class);
                                        startActivity(homeIntent);
                                        finish();
                                    }
                                    else{
                                        Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                                        startActivity(homeIntent);
                                        finish();
                                    }

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this, "Authentication failed",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });




            }
        });





    }
}