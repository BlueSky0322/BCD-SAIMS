package ContentData;

import Utils.MaritalStatus;
import Utils.Gender;
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

    //used to generate random data
    public PersonalInfo(String ID) {
        this.studID = ID;
        this.studName = "Student " + ID;

        // randomly assign gender
        Random random = new Random();
        if (random.nextInt(2) == 1) {
            studGender = Gender.MALE;
        } else {
            studGender = Gender.FEMALE;
        }

        // generate a random 10 digit Malaysian phone number
        StringBuilder sb = new StringBuilder();
        sb.append("01");
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        studContactNo = sb.toString();

        //randomly assign address
        String[] klDistricts = {"Cheras", "Kepong", "Segambut", "Setapak", "Wangsa Maju"};
        studAddress = klDistricts[random.nextInt(klDistricts.length)];

        studEmail = ID + "@gmail.com";
        studDOB = "22/3/23";

        // randomly assign marital status
        if (random.nextInt(2) == 1) {
            studMaritalStat = MaritalStatus.MARRIED;
        } else {
            studMaritalStat = MaritalStatus.SINGLE;
        }
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

    @Override
    public String toString() {
        return "\n==------------== Student Information ==------------=="
                + "\nID: '" + studID + '\''
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
