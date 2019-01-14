package vn.thaitran.testapi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TranThai on 1/12/2019.
 */

public class Person {

    @SerializedName("studentID")
    private int personID;
    @SerializedName("studentName")
    private String personName;
    @SerializedName("studentAge")
    private int personAge;
    public int getPersonID() {
        return personID;
    }

    public Person() {
    }

    public Person(int personID, String personName, int personAge) {
        this.personID = personID;
        this.personName = personName;
        this.personAge = personAge;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonAge() {
        return personAge;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }

}
