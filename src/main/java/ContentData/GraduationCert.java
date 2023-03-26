package ContentData;

import java.io.Serializable;

public class GraduationCert implements Serializable {

    private final String degreeType;
    private final String classHonours;
    private final String CGPA;

    public GraduationCert(String degreeType, String classHonours, String CGPA) {
        this.degreeType = degreeType;
        this.classHonours = classHonours;
        this.CGPA = CGPA;
    }

    public GraduationCert() {
        degreeType = "Bachelors Degree for Computer Science";
        classHonours = "First Class";
        CGPA = "4.0";
    }

    public String getDegreeType() {
        return degreeType;
    }

    public String getClassHonours() {
        return classHonours;
    }

    public String getCGPA() {
        return CGPA;
    }

    @Override
    public String toString() {
        return "\n==------------== Graduation Certificate ==------------=="
                + "\nDegree Type: '" + degreeType + '\''
                + "\nClass Honours: '" + classHonours + '\''
                + "\nCGPA: '" + CGPA + '\''
                + "\n";
    }
    
    
}
