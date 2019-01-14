package vn.thaitran.bai7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class StudentDetailActivity extends AppCompatActivity {

    TextView txtStudentName, txtDayOfBirth, txtStudentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        txtStudentName = findViewById(R.id.txtStudentName);
        txtDayOfBirth = findViewById(R.id.txtDayOfBirth);
        txtStudentAddress = findViewById(R.id.txtStudentAddress);
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        if (student != null) {
            txtStudentName.setText(student.getStudentName());
            txtStudentAddress.setText(student.getStudentAddress());
            txtDayOfBirth.setText(student.getDayOfBirth());
        }

    }
}
