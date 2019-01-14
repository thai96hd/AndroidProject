package vn.thaitran.bai7;

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

    ListView lvStudents;
    List<Student> students;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvStudents = findViewById(R.id.lvStudents);
        this.context = this;
        students = new ArrayList<>();
        StudentProcessor  studentProcessor=new StudentProcessor();
        studentProcessor.execute(Constant.SERVER+Constant.JSON_URL);
        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student st=students.get(position);
                Intent intent=new Intent(MainActivity.this,StudentDetailActivity.class);
                intent.putExtra("student",st);
                startActivity(intent);
            }
        });
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
            lvStudents.setAdapter(studentAdapter);
        }

        @Override
        protected List doInBackground(String... strings) {
            List<Student> list = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();//This String contains list json string object

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String readLine;
                while ((readLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(readLine);
                }
                inputStream.close();
                httpURLConnection.disconnect();

                // parse json string to list java object.
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Student[] studentArray = gson.fromJson(stringBuilder.toString(), Student[].class);
                list = Arrays.asList(studentArray);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return list;
        }
    }
}
