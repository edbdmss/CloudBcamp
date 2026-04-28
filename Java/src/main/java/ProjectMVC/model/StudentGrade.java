package ProjectMVC.model;

public enum StudentGrade {
    FRESHMAN("F"),
	SOPHMORE("S"), 
	JUNIOR("J"), 
	SENIOR("S");

    private final String gradecode;

    private StudentGrade(String gradecode) {
        this.gradecode = gradecode;
    }
}
