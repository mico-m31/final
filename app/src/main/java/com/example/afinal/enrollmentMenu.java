package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class enrollmentMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enrollment_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if(preferences.isNotLoggedIn(this)){
            Intent intent = new Intent(enrollmentMenu.this,loginPage.class);
            startActivity(intent);
        }
        Button selectSubjectBtn = findViewById(R.id.selectSubjectBtn);
        Button enrollSummaryBtn = findViewById(R.id.enrollSummaryBtn);
        TextView userName = findViewById(R.id.userName);
        Button logOutBtn = findViewById(R.id.logOutBtn);
        String email = preferences.getEmail(this);
        userName.setText(email);


        selectSubjectBtn.setOnClickListener(v -> {
            Intent intent = new Intent(enrollmentMenu.this, selectSubject.class);
            startActivity(intent);
        });

        enrollSummaryBtn.setOnClickListener(v -> {
            Intent intent = new Intent(enrollmentMenu.this, enrollSummary.class);
            startActivity(intent);
        });

        logOutBtn.setOnClickListener(v -> {
            preferences.clearSession(enrollmentMenu.this);
            Intent intent = new Intent(enrollmentMenu.this, loginPage.class);
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        });
    }
}