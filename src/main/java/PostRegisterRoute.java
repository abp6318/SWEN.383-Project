import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.logging.Logger;


public class PostRegisterRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(PostRegisterRoute.class.getName());

    private UserManager manager;
    private TemplateEngine engine;

    public PostRegisterRoute(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        this.engine = engine;
        LOGGER.config("PostRegisterRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /register");

        String email = request.queryParams("email");
        String password = request.queryParams("password");
        String fname = request.queryParams("fname");
        String lname = request.queryParams("lname");

        // manager.userregister(email, password, fname, lname, request.session()); ---- this will be where the methods aaron's working on are called :)

        response.redirect(WebServer.LOGIN, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }

}
