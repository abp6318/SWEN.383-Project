import java.util.HashMap;

public class QuizQuestion {

    String quizID = "";
    String questionNum = "";
    String questionContent = "";
    String optionA = "";
    String optionB = "";
    String optionC = "";
    String optionD = "";
    String questionAnswer = "";

    public QuizQuestion(String quizID, String questionNum, String questionContent, String optionA, String optionB, String optionC, String optionD, String questionAnswer){
        this.quizID = quizID;
        this.questionNum = questionNum;
        this.questionContent = questionContent;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.questionAnswer = questionAnswer;
    }

    public String getQuizID() {
        return quizID;
    }

    public String getQuestionNum() {
        return questionNum;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" + quizID + ", " + questionNum + ", " + questionContent + ", " + optionA + ", " + optionB + ", " + optionC + ", " + optionD + ", " + questionAnswer + " }";
    }

    public HashMap<String, String> getHash() {
        HashMap<String, String> qMap = new HashMap<>();
        qMap.put("quizID",quizID);
        qMap.put("questionNum",questionNum);
        qMap.put("questionContent",questionContent);
        qMap.put("optionA",optionA);
        qMap.put("optionB",optionB);
        qMap.put("optionC",optionC);
        qMap.put("optionD",optionD);
        qMap.put("questionAnswer",questionAnswer);

        return qMap;
    }
}
