import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.logging.Logger;


public class PostAdminRoute implements Route{


    // TODO: create post version of this class so that admin can interact with and redirects back to this class (will then rebuild admin class table with this new info)



    private static final Logger LOGGER = Logger.getLogger(PostAdminRoute.class.getName());

    private UserManager manager;

    public PostAdminRoute(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        LOGGER.config("PostAdminRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /admin");

        // adjust these for values allowed by the given admin actions
        String email = request.queryParams("email");
        String password = request.queryParams("password");

        // TODO: add methods to create class, update class, and delete class in the handle method here and handle logic accordingly
            // fill in ratings dynamically? - needs to be done in separate DB call I think?
        // TODO: add method to add a professor to a course in the handle method here and handle logic accordingly

        response.redirect(WebServer.ADMIN, HttpURLConnection.HTTP_MOVED_PERM);


        return null;
    }

}
