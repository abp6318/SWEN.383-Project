import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

public class PostQuizDisplayLearnerRoute implements Route {
    private static final Logger LOGGER = Logger.getLogger(PostQuizDisplayLearnerRoute.class.getName());

    private UserManager manager;

    public PostQuizDisplayLearnerRoute(UserManager manager, TemplateEngine engine) {
        this.manager = manager;
        LOGGER.config("PostQuizDisplayLearnerRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /quizDisplayLearner");

        String quizID = request.queryParams("quizDisplayLearner");
        request.session().attribute("quizID", quizID);
        response.redirect(WebServer.QUIZDISPLAYLEARNER, HttpURLConnection.HTTP_MOVED_PERM);

        //add calculate score stuff??
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
