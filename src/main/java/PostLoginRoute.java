import spark.*;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class PostLoginRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(PostLoginRoute.class.getName());

    private UserManagerTwo manager;
    private TemplateEngine engine;

    public PostLoginRoute(UserManagerTwo manager, TemplateEngine engine){
        this.manager = manager;
        this.engine = engine;
        LOGGER.config("PostLoginRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /login");

        String email = request.queryParams("email");
        String password = request.queryParams("password");
        email = "abdul@gmail.com";
        password = "abdul123";

        // manager.userlogin(email, password, request.session()); ---- this will be where the method aaron's working on is called :)

        if(manager.validUser(email, password)){ // if successful login
            String role = manager.getRole(email);
            if(role.equals("admin")){
                response.redirect(WebServer.ADMIN, HttpURLConnection.HTTP_MOVED_PERM);
            }
            else if (role.equals("professor")){
                response.redirect(WebServer.PROFESSOR, HttpURLConnection.HTTP_MOVED_PERM);
            }
            else
                response.redirect(WebServer.LEARNER, HttpURLConnection.HTTP_MOVED_PERM);

        }
        else{ // redirect to login page (or not authenticated page)
            response.redirect(WebServer.LOGIN, HttpURLConnection.HTTP_MOVED_PERM);
        }

        return null;
    }

}
