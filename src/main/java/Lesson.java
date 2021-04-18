import java.util.HashMap;

public class Lesson {

    public String lessonID;
    public String classCode;
    public String lessonName;
    public String startDate;
    public String endDate;


    public Lesson(String lessonID, String classCode, String lessonName, String startDate, String endDate){
        this.lessonID = lessonID;
        this.classCode = classCode;
        this.lessonName = lessonName;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public HashMap<String, String> getHash() {
        HashMap<String, String> cMap = new HashMap<>();
        cMap.put("lessonID",lessonID);
        cMap.put("classCode",classCode);
        cMap.put("lessonName",lessonName);
        cMap.put("startDate",startDate);
        cMap.put("endDate",endDate);

        return cMap;
    }

    public String getLessonID() { return lessonID; }

    public void setLessonID(String lessonID) { this.lessonID = lessonID; }

    public String getClassCode() { return classCode; }

    public void setClassCode(String classCode) { this.classCode = classCode; }

    public String getLessonName() { return lessonName; }

    public void setLessonName(String lessonName) { this.lessonName = lessonName; }

    public String getStartDate() { return startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonID=" + lessonID +
                ", classCode='" + classCode + '\'' +
                ", lessonName='" + lessonName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

}