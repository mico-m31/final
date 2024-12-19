package com.example.afinal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class selectSubject extends AppCompatActivity {
    private ArrayList<subjectModel> subjectList;
    private dbHelper dbHelper;
    private subjectAdapter subjectAdapter;
    private RecyclerView subjectRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);

        subjectList = new ArrayList<>();
        dbHelper = new dbHelper(selectSubject.this);


        subjectList = dbHelper.readDb();

        subjectAdapter = new subjectAdapter(subjectList, selectSubject.this);
        subjectRV = findViewById(R.id.idRVsubject);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(selectSubject.this, RecyclerView.VERTICAL, false);
        subjectRV.setLayoutManager(linearLayoutManager);
        subjectRV.setAdapter(subjectAdapter);
    }
}
