package vn.thaitran.bai8;

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
 * Created by TranThai on 1/3/2019.
 */

public class StudentAdapter extends ArrayAdapter<Student> {
    List<Student> students;
    Context context;
    int resource;
    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.students=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(resource,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.txtStudentName=convertView.findViewById(R.id.txtStudentName);
            viewHolder.txtStudentClass=convertView.findViewById(R.id.txtStudentClass);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //assign data for view
        Student st=students.get(position);
        viewHolder.txtStudentName.setText(st.getStudentName());
        viewHolder.txtStudentClass.setText(st.getStudentClass());
        return convertView;
    }
    private class ViewHolder{
        TextView txtStudentName;
        TextView txtStudentClass;
    }
}
