package com.example.afinal;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper dbHelper = new dbHelper(this);
        EditText Email = findViewById(R.id.email);
        EditText Password = findViewById(R.id.password);
        TextView signUpLink = findViewById(R.id.signUpLink);
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    if (dbHelper.validateUser(email, password)) {
                        Toast.makeText(loginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        preferences.saveSession(loginPage.this, email);  // Pass context
                        Intent intent = new Intent(loginPage.this, enrollmentMenu.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(loginPage.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(loginPage.this, "Enter email and password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}