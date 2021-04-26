import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.logging.Logger;

public class PostLectureLRoute implements Route {
    private static final Logger LOGGER = Logger.getLogger(PostLectureLRoute.class.getName());

    private UserManager manager;

    public PostLectureLRoute(UserManager manager, TemplateEngine engine) {
        this.manager = manager;
        LOGGER.config("PostLectureLRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) throws ParseException {
        LOGGER.info("POST /lectureL");

        String classCode = request.session().attribute("classCode");
        LOGGER.info("here is the class code: " + classCode);


        // getting lessonID so can display lectures associated with lessons
        String lessonID = request.queryParams("classButton");

        // getting multimedia link so can display lecture content
        String multimediaLink = request.queryParams("lectButton");


        if(lessonID != null && !lessonID.equals("")){
            request.session().attribute("lessonID", lessonID);
            LOGGER.info("adding lessonID to session storage: " + lessonID);
        }

        if(multimediaLink != null && !multimediaLink.equals("")){
            request.session().attribute("multimediaLink", multimediaLink);
            LOGGER.info("adding multimediaLink to session storage: " + multimediaLink);
        }

        response.redirect(WebServer.LECTUREL, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }

}
