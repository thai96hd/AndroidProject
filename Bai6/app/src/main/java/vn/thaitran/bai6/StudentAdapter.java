package vn.thaitran.bai6;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by TranThai on 1/1/2019.
 */

public class StudentAdapter extends ArrayAdapter<Student> {
    Context context;
    int resource;
    List<Student> students;

    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> students) {
        super(context, resource, students);
        this.context = context;
        this.resource = resource;
        this.students = students;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //LayoutInflater layoutInflater1=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(resource,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.imgAvatar=convertView.findViewById(R.id.imgAvatar);
            viewHolder.txtName=convertView.findViewById(R.id.txtName);
            viewHolder.txtStatusMessage=convertView.findViewById(R.id.txtStatusMessage);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Student student=new Student();
        student=students.get(position);
        viewHolder.txtName.setText(student.getName());
        viewHolder.txtStatusMessage.setText(student.getStatusMessage());
        Picasso.with(context).load("file:///android_asset/"+student.getAvatar()).into(viewHolder.imgAvatar);
        return convertView;
    }

    class ViewHolder {
        private ImageView imgAvatar;
        private TextView txtName;
        private TextView txtStatusMessage;
    }
}
