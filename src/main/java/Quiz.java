import java.util.*;

public class Quiz {
    //Attributes
    HashMap<String, ArrayList<String>> questions = new HashMap<String, ArrayList<String>>(); 
    String quizID = "";
    String quizName = "";
    String timeLimit = "";
    String classCode = "";
    String score = "";

    public Quiz(String id, String name, String time, String inClass) {
        quizID = id;
        quizName = name;
        timeLimit = time;
        classCode = inClass;
    }

    /**
     * adds quiz question to quix
     * @param question question to be added
     * @param answer answer to be added
     */
    public void addQuizQuestion (String question, String answer) {
        ArrayList<String> temp;
        if (questions.containsKey(question)) {
            temp = questions.get(question);
            temp.add(answer);
            questions.put(question, temp);
        }
        else {
            temp = new ArrayList<String>();
            temp.add(answer);
            questions.put(question, temp);
        }
    }

    /**
     * Gets the hashmap of the attributes to display in the ftl
     * @return hashmap
     */
    public HashMap<String, String> getHash() {
        HashMap<String, String> qMap = new HashMap<>();
        qMap.put("quizID",quizID);
        qMap.put("quizName", quizName);
        qMap.put("timeLimit", timeLimit);
        qMap.put("classCode", classCode);
        //qMap.put("score", score);
        return qMap;
    }
    
    /**
     * Adding score in a separate method because I didnt want to have to update
     * every time we created a quzi
     * @param inScore the score to be added
     */
    public void setQuizScore(String inScore) {
        score = inScore;
    }

}
