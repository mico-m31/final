package com.example.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class subjectAdapter extends RecyclerView.Adapter<subjectAdapter.ViewHolder> {

    private ArrayList<subjectModel> subjectList;
    private Context context;
    private dbHelper dbHelper;
    private int studentId;

    public subjectAdapter(ArrayList<subjectModel> subjectList, Context context) {
        this.subjectList = subjectList;
        this.context = context;
        this.dbHelper = new dbHelper(context);
        this.studentId = studentId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        subjectModel modal = subjectList.get(position);
        holder.subjectName.setText(modal.getSubjectName());
        holder.className.setText(modal.getClassName());
        holder.credits.setText(String.valueOf(modal.getCredits()));


        holder.enrollButton.setOnClickListener(v -> {
            boolean isEnrolled = dbHelper.enrollStudent(studentId, modal.getSubjectId(), modal.getCredits());
            if (isEnrolled) {
                Toast.makeText(context, "Enrolled in " + modal.getSubjectName(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Already enrolled in " + modal.getSubjectName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView subjectName, className, credits;
        private Button enrollButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectName);
            className = itemView.findViewById(R.id.className);
            credits = itemView.findViewById(R.id.credits);
            enrollButton = itemView.findViewById(R.id.enrollButton);
        }
    }
}
