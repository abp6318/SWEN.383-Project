import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.logging.Logger;


public class PostFeedbackRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(PostFeedbackRoute.class.getName());

    private UserManager manager;

    public PostFeedbackRoute(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        LOGGER.config("PostFeedbackRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /rating");



        response.redirect(WebServer.FEEDBACK, HttpURLConnection.HTTP_MOVED_PERM);


        return null;
    }

}
