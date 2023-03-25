package ContentData;

import java.io.Serializable;

public class Transcript implements Serializable {
    private String moduleTaken;
    transient private Double creditHours;
    private String moduleResult;
    private String moduleGPA;
    private String cgpa;

    public Transcript(String moduleTaken, Double creditHours, String moduleResult, String moduleGPA, String cgpa) {
        this.moduleTaken = moduleTaken;
        this.creditHours = creditHours;
        this.moduleResult = moduleResult;
        this.moduleGPA = moduleGPA;
        this.cgpa = cgpa;
    }


    public Transcript(){
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
        return "\n==------------== Academic Transcript ==------------==" +
                "Module: '" + moduleTaken + '\'' +
                "\nCredit Hours: " + creditHours +
                "\nModule Result: '" + moduleResult + '\'' +
                "\nModule GPA: '" + moduleGPA + '\'' +
                "\nCGPA: '" + cgpa + '\'' +
                "\n";
    }
}
