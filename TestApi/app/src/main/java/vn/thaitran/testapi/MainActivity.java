package vn.thaitran.testapi;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    PersonAdapter  personAdapter;
    List<Person> personList;
    ListView lvPerson;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvPerson=findViewById(R.id.lvPerson);
        this.context=this;
        personList=new ArrayList<>();
       PersonProcess  personProcess=new PersonProcess();
       personProcess.execute("https://apipersondemo.herokuapp.com/api/person/getAll");
    }
    class PersonProcess extends AsyncTask<String,Void,List<Person> >{
        @Override
        protected void onPostExecute(List<Person> people) {
            super.onPostExecute(people);
            personList=people;
            personAdapter=new PersonAdapter(context,R.layout.layout,personList);
            lvPerson.setAdapter(personAdapter);
        }

        @Override
        protected List<Person> doInBackground(String... strings) {

            List<Person> list=new ArrayList<>();
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                StringBuilder stringBuilder=new StringBuilder();
                String str;
                BufferedReader  bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                while ((str=bufferedReader.readLine())!=null){
                    stringBuilder.append(str);
                }
                GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson=gsonBuilder.create();
                Person [] personArr=gson.fromJson(stringBuilder.toString(),Person[].class);
                list= Arrays.asList(personArr);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return list;
        }
    }
}
