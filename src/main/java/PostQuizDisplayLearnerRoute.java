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
        Object mapOfAnswers = request.queryParams("allStudentAnswers");
        System.out.println(mapOfAnswers);





        response.redirect(WebServer.QUIZDISPLAYLEARNER, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }

}
