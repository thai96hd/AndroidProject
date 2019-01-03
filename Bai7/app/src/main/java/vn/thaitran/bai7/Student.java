package vn.thaitran.bai7;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by TranThai on 1/2/2019.
 */

public class Student implements Serializable {

    @SerializedName("studentid")
    private int studentId;
    @SerializedName("studentname")
    private String studentName;
    private String studentAddress;
    @SerializedName("DayOfBirth")
    private String dayOfBirth;
    private String studentAvatar;
    private String statusMessage;

    public Student() {
    }

    public int getStudentId() {

        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getStudentAvatar() {
        return studentAvatar;
    }

    public void setStudentAvatar(String studentAvatar) {
        this.studentAvatar = studentAvatar;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
