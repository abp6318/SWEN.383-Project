import spark.*;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.*;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.*;
import javax.activation.*;


public class PostLoginRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(PostLoginRoute.class.getName());

    private UserManager manager;

    public PostLoginRoute(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        LOGGER.config("PostLoginRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /login");

        String email = request.queryParams("email");
        String password = request.queryParams("password");
        String verify = request.queryParams("entered");
        System.out.println(verify);
        System.out.println(email);
        System.out.println(password);
        
        // if it is, change their verified status and log them in
        // if it is not, redirect to failed page (or some page saying incorrect verification code)

        // verify value exists, trying to validate account
        if(verify != null && !verify.equals("")){
            // if verifies match
            if(verify.equals(manager.selectVerifyCodeSQL(email))){
                manager.updateUserVerification(email);
            }
        }

        System.out.println(":)");
        User tempUserObj = manager.userLogin(email, password);
        System.out.println("TEMP-USER-OBJ (LINE 29 of PostLoginRoutes): " + tempUserObj);

        if (tempUserObj.getVerified().equals("1")) { // if successful login
            String tempUserObjRole = tempUserObj.getRole();
            System.out.println(tempUserObjRole);
            if (!tempUserObjRole.equals("")) {
                request.session().attribute("User", tempUserObj);
                // a for admin, p for professor, s for student, and "" for nothing/failed login
                if (tempUserObjRole.equals("a")) { // if admin
                    response.redirect(WebServer.ADMIN, HttpURLConnection.HTTP_MOVED_PERM);
                } else if (tempUserObjRole.equals("p")) { // if professor
                    response.redirect(WebServer.PROFESSOR, HttpURLConnection.HTTP_MOVED_PERM);
                } else // if learner
                    response.redirect(WebServer.LEARNER, HttpURLConnection.HTTP_MOVED_PERM);
            } else
                response.redirect(WebServer.FAILED, HttpURLConnection.HTTP_MOVED_PERM);

        }
            // else{ // redirect to login page (or not authenticated page)
            //    response.redirect(WebServer.LOGIN, HttpURLConnection.HTTP_MOVED_PERM);
            // }

            return null;
    }


}


