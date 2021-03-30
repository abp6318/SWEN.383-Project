import java.util.HashMap;

public class Feedback {
    //Attributes
    private String userEmail;
    private String classCode;
    private String feedback;

    public Feedback(String inUserEmail, String inClassCode, String inFeedback) {
        userEmail = inUserEmail;
        classCode = inClassCode;
        feedback = inFeedback;
    }

    //hashmap method
    public HashMap<String, String> getHash() {
        HashMap<String, String> fMap = new HashMap<>();
        fMap.put("userEmail",userEmail);
        fMap.put("classcode", classCode);
        fMap.put("feedback",feedback);
        return fMap;
    }

    //Get methods
    public String getUserEmail() {
        return userEmail;
    }

    public String getClassCode() {
        return classCode;
    }

    public String getFeedback() {
        return feedback;
    }

    //set methods
    public void setUserEmail(String inEmail) {
        userEmail = inEmail;
    }

    public void setClassCode(String inCode) {
        classCode = inCode;
    }

    public void setFeedback(String inFeedback) {
        feedback = inFeedback;
    }

    @Override
    public String toString() {
        return "User Email: " + userEmail + " Class Code: " + classCode + " Feedback: " + feedback;
    }
}
