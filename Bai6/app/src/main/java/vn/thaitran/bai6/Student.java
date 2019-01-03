package vn.thaitran.bai6;

import java.io.Serializable;

/**
 * Created by TranThai on 1/1/2019.
 */

public class Student implements Serializable{
    private String avatar;

    public Student() {
    }

    public Student(String avatar, String name, String statusMessage) {
        this.avatar = avatar;
        this.name = name;
        this.statusMessage = statusMessage;
    }

    private String name;
    private String statusMessage;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
