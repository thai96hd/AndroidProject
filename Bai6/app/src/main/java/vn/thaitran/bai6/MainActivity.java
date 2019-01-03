package vn.thaitran.bai6;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvStudent;
    List<Student> students;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvStudent = findViewById(R.id.lvStudent);
        students = new ArrayList<>();

        this.context = this;

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, UpdateStatusActivity.class);
                Student student = students.get(position);
                intent.putExtra("student", student);
                startActivity(intent);
            }
        });
        StudentProcessor studentProcessor=new StudentProcessor();
        studentProcessor.execute("students.json");
    }



    class StudentProcessor extends AsyncTask<String, Void, List> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
            students = list;
            StudentAdapter studentAdapter = new StudentAdapter(context, R.layout.student_item, students);
            lvStudent.setAdapter(studentAdapter);
        }

        @Override
        protected List doInBackground(String... strings) {
            List<Student> studentList = new ArrayList<>();
            try {
                //Get json string from json file
                InputStream inputStream = context.getAssets().open(strings[0]);
                String strLine;
                StringBuilder _strStudents = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while ((strLine = bufferedReader.readLine()) != null) {
                    _strStudents.append(strLine);
                }
                inputStream.close();

                //pass json string to list java object.
                if (_strStudents != null) {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    Student[] studentsArray;
                    studentsArray = gson.fromJson(_strStudents.toString(), Student[].class);
                    studentList = Arrays.asList(studentsArray);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return studentList;
        }
    }
}
