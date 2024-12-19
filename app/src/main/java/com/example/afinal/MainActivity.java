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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {
    private dbHelper dbHelper;
    private EditText studentId, studentName, email, password;
    private Button regisBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        studentId = findViewById(R.id.studentId);
        studentName = findViewById(R.id.studentName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        TextView loginLink = findViewById(R.id.loginLink);
        regisBtn = findViewById(R.id.regisBtn);

        dbHelper = new dbHelper(MainActivity.this);
        subjectModel subject = new subjectModel();
        subject.setSubjectId(110);
        subject.setSubjectName("Game Asset and Design");
        subject.setClassName("A-215");
        subject.setCredits(3);
        dbHelper.addSubject(subject);


        regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String student_Id = studentId.getText().toString().trim();
                String student_Name = studentName.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                if (!student_Id.isEmpty() && !student_Name.isEmpty() && !Email.isEmpty() && !Password.isEmpty()) {
                        int Student_Id = Integer.parseInt(student_Id);
                        studentModel student = new studentModel(Student_Id, student_Name, Email, Password);
                        dbHelper.addStudent(student);
                        Toast.makeText(MainActivity.this, "account created succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, loginPage.class);
                        startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in all required fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, loginPage.class);
                startActivity(intent);
            }
        });
    }
}
