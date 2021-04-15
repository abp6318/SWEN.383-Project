import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

public class PostMultimediaRoute implements Route {
    private static final Logger LOGGER = Logger.getLogger(PostMultimediaRoute.class.getName());

    private UserManager manager;

    public PostMultimediaRoute(UserManager manager, TemplateEngine engine) {
        this.manager = manager;
        LOGGER.config("PostMultimediaRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /multimedia");

        response.redirect(WebServer.MULTIMEDIA, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }

}
