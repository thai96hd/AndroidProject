package vn.thaitran.bai8;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String SERVER = "http://10.0.3.2:3000/";
    private static final String URL_JSON = "students";

    ListView listViewStudent;
    EditText editTextStudentName;
    EditText editTextStudentClass;
    Button btnAdd;
    Context context;

    StudentAdapter studentAdapter;
    List<Student> students;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewStudent = findViewById(R.id.lvStudent);
        editTextStudentName = findViewById(R.id.txtStudentName);
        editTextStudentClass = findViewById(R.id.txtStudentClass);
        btnAdd = findViewById(R.id.btnAdd);
        students = new ArrayList<>();
        this.context = this;


        StudentProcessor studentProcessor = new StudentProcessor();
        studentProcessor.execute(SERVER + URL_JSON, "nothing");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentProcessor studentProcessor1 = new StudentProcessor();
                studentProcessor1.execute(SERVER + URL_JSON, "add");
            }
        });
    }

    class StudentProcessor extends AsyncTask<String, Void, List<Student>> {

        @Override
        protected List<Student> doInBackground(String... strings) {
            if (strings[1].equalsIgnoreCase("add")) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
                    httpURLConnection.setRequestProperty("Accept", "application/json");
                    httpURLConnection.setRequestMethod("POST");
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    JsonObject jsonObject = new JsonObject();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//+
                    jsonObject.addProperty("id", simpleDateFormat.format(new Date()));
                    jsonObject.addProperty("studentName", editTextStudentName.getText().toString());
                    jsonObject.addProperty("studentClass", editTextStudentClass.getText().toString());
                    String strJsonStudent = jsonObject.toString();
                    bufferedWriter.write(strJsonStudent);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    Log.i("err",String.valueOf(httpURLConnection.getResponseCode())+ httpURLConnection.getResponseMessage());
                    httpURLConnection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("err", e.toString());
                    e.printStackTrace();
                }
            }
            return getStudents(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Student> list) {
            super.onPostExecute(list);
            students = list;
            studentAdapter = new StudentAdapter(context, R.layout.item, students);
            listViewStudent.setAdapter(studentAdapter);
        }
    }

    private List<Student> getStudents(String urlParam) {
        List<Student> list = new ArrayList<>();
        try {
            URL url = new URL(urlParam);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setRequestProperty("Accept","application/json");
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String readLine;
            StringBuilder strStudentJSON = new StringBuilder();
            while ((readLine = bufferedReader.readLine()) != null) {
                strStudentJSON.append(readLine);
            }
            //parse json string to list java object
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Student[] arrayStudent = gson.fromJson(strStudentJSON.toString(), Student[].class);
            list = Arrays.asList(arrayStudent);
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private boolean addStudent(String urlParam) {
        try {
            URL url = new URL(urlParam);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestMethod("POST");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            JsonObject jsonObject = new JsonObject();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//+ simpleDateFormat.format(new Date())
            //jsonObject.addProperty("studentID", "student012" );
            jsonObject.addProperty("studentName", editTextStudentName.getText().toString());
            jsonObject.addProperty("studentClass", editTextStudentClass.getText().toString());
            String strJsonStudent = jsonObject.toString();
            bufferedWriter.write(strJsonStudent);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            Log.i("err",String.valueOf(httpURLConnection.getResponseCode())+ httpURLConnection.getResponseMessage());
            httpURLConnection.disconnect();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("err", e.toString());
            e.printStackTrace();
        }

        return false;
    }
}
