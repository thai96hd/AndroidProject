package vn.thaitran.bai6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateStatusActivity extends AppCompatActivity {

    EditText txtStatusMessageUpdate;
    Button btnUpdate,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);
        txtStatusMessageUpdate=findViewById(R.id.txtStatusMessageUpdate);
        btnCancel=findViewById(R.id.btnCancel);
        btnUpdate=findViewById(R.id.btnUpdate);
        Intent intent=getIntent();
        Student student= (Student) intent.getSerializableExtra("student");
        txtStatusMessageUpdate.setText(student.getStatusMessage().toString());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatusStudent("");
            }
        });

    }
    private void updateStatusStudent(String status){

    }
}
