import java.util.*;

public class Quiz {
    //Attributes
    HashMap<String, ArrayList<String>> questions = new HashMap<String, ArrayList<String>>(); 

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

    //TODO: display quiz html? or print??? 
    
    //student view

    //prof view

}
