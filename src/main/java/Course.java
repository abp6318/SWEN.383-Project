import java.util.HashMap;

public class Course {
    //Attributes 
    private String courseCode;
    private String creatorEmail;
    private String professorEmail;
    private String className;
    private String learningObj;
    private String learningOutcome;
    private String beginDate;
    private String endDate;

    public Course(String inCode, String inCEmail, String inPEmail, String inName, String inObj, String inOutcome, String inStart, String inEnd) {
        courseCode = inCode;
        creatorEmail = inCEmail;
        professorEmail = inPEmail;
        className = inName;
        learningObj = inObj;
        learningOutcome = inOutcome;
        beginDate = inStart;
        endDate = inEnd;
    }

    //hashmap method 
    public HashMap<String, String> getHash() {
        HashMap<String, String> cMap = new HashMap<>();
        cMap.put("classcode", courseCode);
        cMap.put("adminEmail",creatorEmail);
        cMap.put("profEmail",professorEmail);
        cMap.put("className", className);
        cMap.put("objective",learningObj);
        cMap.put("outcome",learningOutcome);
        cMap.put("start",beginDate);
        cMap.put("end",endDate);
        return cMap;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public String getProfEmail() {
        return professorEmail;
    }

    public String getClassName() {
        return className;
    }

    public String getLearningObj() {
        return learningObj;
    }

    public String getLearningOutcome() {
        return learningOutcome;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + " Creator Email: " + creatorEmail + " Professor Email: " + professorEmail + " Class Name: " + className + " Learning Objective: " + learningObj + " Learning Outcome: " + learningOutcome + " Start Date: " + beginDate + " End Date: " + endDate;
    }
}
