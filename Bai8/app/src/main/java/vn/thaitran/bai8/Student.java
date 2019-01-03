package vn.thaitran.bai8;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TranThai on 1/3/2019.
 */

public class Student {
    @SerializedName("id")
    private String studentID;
    private String studentName;
    private String studentClass;

    public Student() {
    }

    public Student(String studentID, String studentName, String studentClass) {

        this.studentID = studentID;
        this.studentName = studentName;
        this.studentClass = studentClass;
    }

    public String getStudentID() {

        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
}
