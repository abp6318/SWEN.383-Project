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



//        String quizID = request.queryParams("quizDisplayLearner");
        String quizID = request.session().attribute("quizID");
        String studentEmail = ((User) request.session().attribute("User")).getEmail();
        String mapOfAnswers = request.queryParams("allStudentAnswers");
        System.out.println("Map: " + mapOfAnswers);

        String[] map = mapOfAnswers.split(",");
        for(int index=0; index<map.length; index++){
            manager.insertStudentAnswers(quizID, map[index].split(":")[0], studentEmail, map[index].split(":")[1]);
        }
        manager.automaticallyGrade(studentEmail, quizID);

        response.redirect(WebServer.QUIZLEARNER, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }

}
