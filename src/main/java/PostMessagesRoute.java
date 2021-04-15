import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

public class PostMessagesRoute implements Route {
    private static final Logger LOGGER = Logger.getLogger(PostMessagesRoute.class.getName());

    private UserManager manager;

    public PostMessagesRoute(UserManager manager, TemplateEngine engine) {
        this.manager = manager;
        LOGGER.config("PostMessagesRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /messagesDG");

        response.redirect(WebServer.MESSAGESDG, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }

}
