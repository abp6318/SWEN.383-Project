import java.util.HashMap;

public class Lecture {

    public String lessonID;
    public String multimedia;
    public String lectureID;
    public String lectureName;


    public Lecture(String lessonID, String multimedia, String lectureID, String lectureName){
        this.lessonID = lessonID;
        this.multimedia = multimedia;
        this.lectureID = lectureID;
        this.lectureName = lectureName;
    }

    public HashMap<String, String> getHash() {
        HashMap<String, String> cMap = new HashMap<>();
        cMap.put("lessonID",lessonID);
        cMap.put("multimedia",multimedia);
        cMap.put("lectureID",lectureID);
        cMap.put("lectureName",lectureName);
        return cMap;
    }


    @Override
    public String toString() {
        return "Lecture{" +
                "lessonID=" + lessonID +
                ", multimedia='" + multimedia + '\'' +
                ", lectureID='" + lectureID + '\'' +
                ", lectureName='" + lectureName + '\'' +
                '}';
    }

}