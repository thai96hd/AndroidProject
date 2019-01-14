package vn.thaitran.bai7;

import android.content.Context;
import android.provider.ContactsContract;
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
 * Created by TranThai on 1/2/2019.
 */

public class StudentAdapter extends ArrayAdapter<Student> {
    Context context;
    int resource;
    List<Student> studentList;

    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.studentList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(resource,parent,false);
            viewHolder.imgStudentAvatar=convertView.findViewById(R.id.imgStudentAvatar);
            viewHolder.txtStudentName=convertView.findViewById(R.id.txtStudentName);
            viewHolder.txtStatusMessage=convertView.findViewById(R.id.txtStatusMessage);
            convertView.setTag(viewHolder);
        }
        else{
           viewHolder= (ViewHolder) convertView.getTag();
        }
        Student st=studentList.get(position);
        Picasso.with(context).load(Constant.SERVER+st.getStudentAvatar()).into(viewHolder.imgStudentAvatar);
        viewHolder.txtStatusMessage.setText(st.getStatusMessage().toString());
        viewHolder.txtStudentName.setText(st.getStudentName());
        return convertView;
    }

    class ViewHolder {
        private ImageView imgStudentAvatar;
        private TextView txtStudentName,txtStatusMessage;
    }
}
