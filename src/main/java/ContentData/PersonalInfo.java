package ContentData;

import Enum.MaritalStatus;
import Enum.Gender;
import java.io.Serializable;
import java.util.Random;

public class PersonalInfo implements Serializable {

    private String studID;
    private String studName;
    private Gender studGender;
    private String studContactNo;
    private String studAddress;
    private String studEmail;
    private String studDOB; //Date of Birth
    private MaritalStatus studMaritalStat;

    public PersonalInfo(String studID, String studName, Gender studGender, String studContactNo, String studAddress, String studEmail, String studDOB, MaritalStatus studMaritalStat) {
        this.studID = studID;
        this.studName = studName;
        this.studGender = studGender;
        this.studContactNo = studContactNo;
        this.studAddress = studAddress;
        this.studEmail = studEmail;
        this.studDOB = studDOB;
        this.studMaritalStat = studMaritalStat;
    }

    public PersonalInfo() {
        studID = "TP061914";
        studName = "John Doe";
        studGender = Gender.MALE;
        studContactNo = "0123456789";
        studAddress = "PJ";
        studEmail = "johndoe123@email.com";
        studDOB = "3/3/23";
        studMaritalStat = MaritalStatus.SINGLE;
    }

    public String getID() {
        return studID;
    }

    public String getName() {
        return studName;
    }

    public Gender getGender() {
        return studGender;
    }

    public String getContact() {
        return studContactNo;
    }

    public String getAddress() {
        return studAddress;
    }

    public String getEmail() {
        return studEmail;
    }

    public String getDob() {
        return studDOB;
    }

    public MaritalStatus getMarital() {
        return studMaritalStat;
    }

    //used to generate random data
    public PersonalInfo(String name, String ID) {
        this.studID = ID;
        this.studName = name;
        Random random = new Random();
        if (random.nextInt(2) == 1) {
            studGender = Gender.MALE;
        } else {
            studGender = Gender.FEMALE;
        }
        studContactNo = "111";
        studAddress = "PJ";
        studEmail = "johndoe123@email.com";
        studDOB = "22/3/23";

        if (random.nextInt(2) == 1) {
            studMaritalStat = MaritalStatus.MARRIED;
        } else {
            studMaritalStat = MaritalStatus.SINGLE;
        }
    }

    @Override
    public String toString() {
        return "\n==------------== Student Information ==------------=="
                + "ID: '" + studID + '\''
                + "\nName: '" + studName + '\''
                + "\nGender: " + studGender
                + "\nContact No.: '" + studContactNo + '\''
                + "\nAddress: '" + studAddress + '\''
                + "\nEmail: '" + studEmail + '\''
                + "\nDate of Birth: '" + studDOB + '\''
                + "\nMarital Status: " + studMaritalStat
                + "\n";
    }
}
