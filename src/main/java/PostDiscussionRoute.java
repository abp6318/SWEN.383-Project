import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.logging.Logger;


public class PostDiscussionRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(PostDiscussionRoute.class.getName());

    private UserManager manager;

    public PostDiscussionRoute(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        LOGGER.config("PostDiscussionRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /discussion");

        String groupName = request.queryParams("groupname");
        String email = request.queryParams("email");

        // Aaron: this is where some discission actions can be taken
            // add new discussion data to db with email and groupName info

        // will need to figure out inviting/searching somewhere here too probably


        response.redirect(WebServer.DISCUSSION, HttpURLConnection.HTTP_MOVED_PERM);


        return null;
    }

}
