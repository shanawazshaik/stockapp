package com.shanu.stockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shanu.stockapp.MainActivity;
import com.shanu.stockapp.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        setupLoginListener();
    }
    private void setupLoginListener() {
        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areCredentialsValid()) {
                    Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainActivity);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean areCredentialsValid() {
        EditText userNameObj = findViewById(R.id.username);
        EditText passwordObj = findViewById(R.id.password);
        String userName = userNameObj.getText().toString();
        String password = passwordObj.getText().toString();
        if (userName == null || password == null) {
            Toast.makeText(getApplicationContext(), "username or password can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return userName.equals("shanawaz.shaik@gmail.com") && password.equals("admin");
    }
}
