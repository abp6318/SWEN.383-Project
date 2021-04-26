import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

public class PostQuizLearnerRoute implements Route {
    private static final Logger LOGGER = Logger.getLogger(PostQuizLearnerRoute.class.getName());

    private UserManager manager;

    public PostQuizLearnerRoute(UserManager manager, TemplateEngine engine) {
        this.manager = manager;
        LOGGER.config("PostQuizLearnerRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /quizLearner");

        response.redirect(WebServer.QUIZDISPLAYLEARNER, HttpURLConnection.HTTP_MOVED_PERM);

        //what does this file even need in it??? 
        //left the user email stuff just in case 
        
        String userEmail = ((User) request.session().attribute("User")).getEmail();


        return null;
    }

}
