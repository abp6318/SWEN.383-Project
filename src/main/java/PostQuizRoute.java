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

        return null;
    }

}
