import spark.*;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class PostLoginRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(PostLoginRoute.class.getName());

    private UserManagerTwo manager;

    public PostLoginRoute(UserManagerTwo manager, TemplateEngine engine){
        this.manager = manager;
        LOGGER.config("PostLoginRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /login");

        String email = request.queryParams("email");
        String password = request.queryParams("password");

        // TODO: check if the verufy field is empty
            // TODO: if empty, continue like normal
            // TODO: if not empty, need to make database call to verify that the input verify is the same
                // if it is, change their verified status and log them in
                // if it is not, redirect to failed page (or some page saying incorrect verification code)

        if(manager.validUser(email, password)){ // if successful login
            String[] userInfo = manager.getRole(email);
            if(!userInfo[3].equals("0")){
                String role = userInfo[2];
                User user = new User(userInfo[0], userInfo[1], email, role);
                request.session().attribute("User", user);
                if(role.equals("admin")){
                    response.redirect(WebServer.ADMIN, HttpURLConnection.HTTP_MOVED_PERM);
                }
                else if (role.equals("professor")){
                    response.redirect(WebServer.PROFESSOR, HttpURLConnection.HTTP_MOVED_PERM);
                }
                else
                    response.redirect(WebServer.LEARNER, HttpURLConnection.HTTP_MOVED_PERM);
            }
            else
                response.redirect(WebServer.FAILED, HttpURLConnection.HTTP_MOVED_PERM);

        }
        else{ // redirect to login page (or not authenticated page)
            response.redirect(WebServer.LOGIN, HttpURLConnection.HTTP_MOVED_PERM);
        }

        return null;
    }

}
