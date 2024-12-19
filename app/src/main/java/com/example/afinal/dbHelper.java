package com.example.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class dbHelper extends SQLiteOpenHelper {
    private static final String dbName = "enrollmentMenu.db";
    private static final int dbVersion = 1;
    private static final String table_students = "student";
    private static final String Column_studentId = "StudentId";
    private static final String Column_studentName = "StudentName";
    private static final String Column_email = "Email";
    private static final String Column_password = "Password";

    private static final String table_subjects = "subjects";
    private static final String Column_subject_id = "SubjectId";
    private static final String Column_subject_name = "SubjectName";
    private static final String Column_class_name = "className";
    private static final String Column_credits = "credit";

    private static final String table_enrollment = "enrollment";


    private static final String CREATE_TABLE_students =
            "CREATE TABLE " + table_students + " (" +
                    Column_studentId + " INTEGER PRIMARY KEY, " +
                    Column_studentName + " TEXT NOT NULL, " +
                    Column_email + " TEXT NOT NULL, " +
                    Column_password + " TEXT NOT NULL)";

    private static final String CREATE_TABLE_subjects =
            "CREATE TABLE " + table_subjects + " (" +
                    Column_subject_id + " INTEGER PRIMARY KEY, " +
                    Column_subject_name + " TEXT," +
                    Column_class_name + " TEXT," +
                    Column_credits + " INTEGER)";

    private static final String CREATE_TABLE_enrollment =
            "CREATE TABLE " + table_enrollment + " (" +
                    Column_studentId + " TEXT," +
                    Column_subject_id + " TEXT," +
                    Column_credits  + " INTEGER," +
                    "FOREIGN KEY (" + Column_studentId + ") REFERENCES " + table_students + "(" + Column_studentId + ")," +
                    "FOREIGN KEY (" + Column_subject_id + ") REFERENCES " + table_subjects + "(" + Column_subject_id + ")" +
                    ")";

    public dbHelper(Context context){
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
            db.execSQL(CREATE_TABLE_students);
            db.execSQL(CREATE_TABLE_subjects);
            db.execSQL(CREATE_TABLE_enrollment);
            db.execSQL("PRAGMA foreign_keys=ON;");
        }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_students);
        db.execSQL("DROP TABLE IF EXISTS " + table_subjects);
        db.execSQL("DROP TABLE IF EXISTS " + table_enrollment);
        onCreate(db);
    }

    public void addStudent(studentModel student){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db;
        contentValues.put(Column_studentId, student.getStudentId());
        contentValues.put(Column_studentName, student.getStudentName());
        contentValues.put(Column_email, student.getEmail());
        contentValues.put(Column_password, student.getPassword());
        db = this.getWritableDatabase();
        db.insert(table_students, null, contentValues);
    }

    public void addSubject(subjectModel subject){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db;
        contentValues.put(Column_subject_id, subject.getSubjectId());
        contentValues.put(Column_subject_name, subject.getSubjectName());
        contentValues.put(Column_class_name, subject.getClassName());
        contentValues.put(Column_credits, subject.getCredits());
        db = this.getWritableDatabase();
        db.insert(table_subjects, null, contentValues);
    }

    public void addEnrollment(enrollmentModel enrollment){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db;
        contentValues.put(Column_studentId,enrollment.getStudentId());
        contentValues.put(Column_subject_id,enrollment.getSubjectId());
        contentValues.put(Column_credits,enrollment.getCredits());
        db = this.getWritableDatabase();
        db.insert(table_enrollment,null,contentValues);
    }

    public boolean validateUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + table_students + " WHERE " +
                        Column_email + " = ? AND " + Column_password + " = ?",
                new String[]{email, password}
        );

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isValid;
     }

    public ArrayList<subjectModel> readDb() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_subjects, null);

        ArrayList<subjectModel> subjectList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                subjectList.add(new subjectModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return subjectList;
    }

    public boolean enrollStudent(int studentId, int subjectId, int credits) {
        SQLiteDatabase db = this.getWritableDatabase();


        db.setForeignKeyConstraintsEnabled(true);

        try {
            Cursor cursor = db.rawQuery(
                    "SELECT * FROM enrollment WHERE studentId = ? AND subjectId = ?",
                    new String[]{String.valueOf(studentId), String.valueOf(subjectId)}
            );

            if (cursor.moveToFirst()) {
                cursor.close();
                return false;
            }

            cursor.close();

            ContentValues values = new ContentValues();
            values.put("studentId", studentId);
            values.put("subjectId", subjectId);
            values.put("credits", credits);

            long result = db.insert("enrollment", null, values);
            if (result == -1) {
                Log.e("DB_ERROR", "Failed to insert data into enrollment table");
                return false;
            }

            Log.d("DB_SUCCESS", "Enrollment data inserted successfully");
            return true;

        } catch (Exception e) {
            Log.e("DB_EXCEPTION", "Error inserting enrollment data: " + e.getMessage());
            return false;

        } finally {
            db.close();
        }
    }


    public int getTotalCredits(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT SUM(credits) FROM enrollment WHERE studentId = ?",
                new String[]{String.valueOf(studentId)}
        );

        int totalCredits = 0;
        if (cursor.moveToFirst()) {
            totalCredits = cursor.getInt(0);
        }
        cursor.close();
        return totalCredits;
    }

}
