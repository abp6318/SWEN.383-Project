import java.util.*;

public class Quiz {
    //Attributes
    HashMap<String, ArrayList<String>> questions = new HashMap<String, ArrayList<String>>(); 
    String quizID = "";
    String quizName = "";
    String timeLimit = "";
    String classCode = "";

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

    
    public HashMap<String, String> getHash() {
        HashMap<String, String> qMap = new HashMap<>();
        qMap.put("quizID",quizID);
        qMap.put("quizName", quizName);
        qMap.put("timeLimit", timeLimit);
        qMap.put("classCode", classCode);
        return qMap;
    }
    
    //student view

    //prof view

}
