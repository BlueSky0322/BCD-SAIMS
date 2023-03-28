package ContentData;

import java.io.Serializable;

public class AcademicTranscript implements Serializable {

    private String moduleTaken;
    transient private Double creditHours;
    private String moduleResult;
    private String moduleGPA;
    private String cgpa;

    public AcademicTranscript() {
        moduleTaken = "BCD";
        creditHours = 40.0;
        moduleResult = "A";
        moduleGPA = "4.0";
        cgpa = "4.0";
    }

    public String getModule() {
        return moduleTaken;
    }

    public double getCredit() {
        return creditHours;
    }

    public String getResult() {
        return moduleResult;
    }

    public String getGrade() {
        return moduleGPA;
    }

    public String getCgpa() {
        return cgpa;
    }

    @Override
    public String toString() {
        return "\n==------------== Academic Transcript ==------------=="
                + "\nModule: '" + moduleTaken + '\''
                + "\nCredit Hours: " + creditHours
                + "\nModule Result: '" + moduleResult + '\''
                + "\nModule GPA: '" + moduleGPA + '\''
                + "\nCGPA: '" + cgpa + '\''
                + "\n";
    }
}
