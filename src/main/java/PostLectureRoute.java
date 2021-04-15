import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

public class PostLectureRoute implements Route {
    private static final Logger LOGGER = Logger.getLogger(PostLectureRoute.class.getName());

    private UserManager manager;

    public PostLectureRoute(UserManager manager, TemplateEngine engine) {
        this.manager = manager;
        LOGGER.config("PostLectureRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /lecture");

        response.redirect(WebServer.LECTURE, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }

}
