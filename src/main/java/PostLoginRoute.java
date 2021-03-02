import spark.*;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class PostLoginRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(PostLoginRoute.class.getName());

    private UserManager manager;
    private TemplateEngine engine;

    public PostLoginRoute(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        this.engine = engine;
        LOGGER.config("PostLoginRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /login");

        String email = request.queryParams("email");
        String password = request.queryParams("password");

        // manager.userlogin(email, password, request.session()); ---- this will be where the method aaron's working on is called :)

        if(true){ // if successful login
            // check user type
            // redirect accordingly (update location)
            response.redirect("", HttpURLConnection.HTTP_MOVED_PERM);
        }
        else{ // redirect to login page (or not authenticated page)
            response.redirect(WebServer.LOGIN, HttpURLConnection.HTTP_MOVED_PERM);
        }
        return null;
    }

}
