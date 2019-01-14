package vn.thaitran.testapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by TranThai on 1/12/2019.
 */

public class PersonAdapter extends ArrayAdapter<Person> {
    Context context;
    int resource;
    List<Person> personList;

    public PersonAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
        this.context = context;
        this.personList = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.layout,parent,false);
        TextView txtId=convertView.findViewById(R.id.txtID);
        TextView txtName=convertView.findViewById(R.id.txtName);
        TextView txtAge=convertView.findViewById(R.id.txtAge);
        txtId.setText(String.valueOf(personList.get(position).getPersonID()));
        txtName.setText(personList.get(position).getPersonName());
        txtAge.setText(String.valueOf(personList.get(position).getPersonAge()));
        return convertView;
    }
}
