package com.pratul20.pandorameet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.pratul20.pandorameet.databinding.ActivityLoginBinding;
import com.pratul20.pandorameet.databinding.ActivityMainBinding;

import java.io.FileReader;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new ProgressDialog(this);
        dialog.setTitle("Logging in");
        dialog.setMessage("Please Wait...");

        auth = FirebaseAuth.getInstance();

        binding.createButton.setOnClickListener( view -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });

        binding.loginBtn.setOnClickListener( view -> {
            dialog.show();
            String email = binding.emailBox.getText().toString();
            String password = binding.passwordBox.getText().toString();
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    dialog.dismiss();
                    if(task.isSuccessful()) {
                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    }
                    else {
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

    }
}