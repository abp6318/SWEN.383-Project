import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

public class PostQuizRoute implements Route {
    private static final Logger LOGGER = Logger.getLogger(PostQuizRoute.class.getName());

    private UserManager manager;

    public PostQuizRoute(UserManager manager, TemplateEngine engine) {
        this.manager = manager;
        LOGGER.config("PostQuizRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /quiz");

        response.redirect(WebServer.QUIZ, HttpURLConnection.HTTP_MOVED_PERM);


        // Add Quiz functionality
        String addQuizName = request.queryParams("AddQuizName");
        String addQuizTimeLimit = request.queryParams("AddQuizTimeLimit");
        String addQuizCreatorEmail = ((User) request.session().attribute("User")).getEmail();
        String addQuizClassCode = request.queryParams("AddQuizClassCode");

        if (addQuizName != null && !addQuizName.equals("")) {
            if (addQuizTimeLimit != null && !addQuizTimeLimit.equals("")) {
                if (addQuizCreatorEmail != null && !addQuizCreatorEmail.equals("")) {
                    if (addQuizClassCode != null && !addQuizClassCode.equals("")) {
                        // if everything has a value, perform action
                        manager.insertQuizSQL(addQuizName, addQuizTimeLimit, addQuizCreatorEmail, addQuizClassCode);
                    }
                }
            }
        }

        // Add Quiz Question functionality
        String addQuizQuestionQuizID = request.queryParams("AddQuizQuestionQuizID");
        String addQuizQuestionQuestionNum = request.queryParams("AddQuizQuestionQuestionNum");
        String addQuizQuestionQuestionContent = request.queryParams("AddQuizQuestionQuestionContent");
        String addQuizQuestionOptionA = request.queryParams("AddQuizQuestionOptionA");
        String addQuizQuestionOptionB = request.queryParams("AddQuizQuestionOptionB");
        String addQuizQuestionOptionC = request.queryParams("AddQuizQuestionOptionC");
        String addQuizQuestionOptionD = request.queryParams("AddQuizQuestionOptionD");
        String addQuizQuestionQuestionAnswer = request.queryParams("AddQuizQuestionQuestionAnswer");
        if((addQuizQuestionOptionA != null && !addQuizQuestionOptionA.equals("")) || (addQuizQuestionOptionB != null && !addQuizQuestionOptionB.equals("")) || (addQuizQuestionOptionC != null && !addQuizQuestionOptionC.equals("")) || (addQuizQuestionOptionD != null && !addQuizQuestionOptionD.equals(""))){
            if (addQuizQuestionQuizID != null && !addQuizQuestionQuizID.equals("")) {
                if (addQuizQuestionQuestionNum != null && !addQuizQuestionQuestionNum.equals("")) {
                    if (addQuizQuestionQuestionContent != null && !addQuizQuestionQuestionContent.equals("")) {
                        if (addQuizQuestionQuestionAnswer != null && !addQuizQuestionQuestionAnswer.equals("")) {
                            // if everything has a value, perform action
                            manager.insertQuizQuestionSQL(addQuizQuestionQuizID, addQuizQuestionQuestionNum, addQuizQuestionQuestionContent, addQuizQuestionOptionA, addQuizQuestionOptionB, addQuizQuestionOptionC, addQuizQuestionOptionD, addQuizQuestionQuestionAnswer);
                        }
                    }
                }
            }
        }

        // Delete Quiz functionality
        String deleteQuizQuizID = request.queryParams("DeleteQuizQuizID");
        String deleteQuizCreatorEmail = ((User) request.session().attribute("User")).getEmail();
        if (deleteQuizQuizID != null && !deleteQuizQuizID.equals("")) {
            if (deleteQuizCreatorEmail != null && !deleteQuizCreatorEmail.equals("")) {
                // if everything has a value, perform action
                manager.deleteQuizSQL(deleteQuizQuizID, deleteQuizCreatorEmail);
            }
        }

        // Delete Quiz Question functionality
        String deleteQuizQuestionQuizID = request.queryParams("DeleteQuizQuestionQuizID");
        String deleteQuizQuestionQuestionNum = request.queryParams("DeleteQuizQuestionQuestionNum");
        if (deleteQuizQuestionQuizID != null && !deleteQuizQuestionQuizID.equals("")) {
            if (deleteQuizQuestionQuestionNum != null && !deleteQuizQuestionQuestionNum.equals("")) {
                // if everything has a value, perform action
                manager.deleteQuizQuestionSQL(deleteQuizQuestionQuizID, deleteQuizQuestionQuestionNum);
            }
        }

        // Add Student Quiz Grade
        String addQuizGradeQuizID = request.queryParams("AddQuizGradeQuizID");
        String addQuizGradeLearnerEmail = request.queryParams("AddQuizGradeLearnerEmail");
        String addQuizGradeScore = request.queryParams("AddQuizGradeScore");
        if (addQuizGradeQuizID != null && !addQuizGradeQuizID.equals("")) {
            if (addQuizGradeLearnerEmail != null && !addQuizGradeLearnerEmail.equals("")) {
                if (addQuizGradeScore != null && !addQuizGradeScore.equals("")) {
                    // if everything has a value, perform action
                    manager.insertQuizScoreSQL(addQuizGradeQuizID, addQuizGradeLearnerEmail, addQuizGradeScore);
                }
            }
        }



        return null;
    }

}
