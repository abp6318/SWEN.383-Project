import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

public class PostLectureRoute implements Route {
    private static final Logger LOGGER = Logger.getLogger(PostLectureRoute.class.getName());

    private UserManager manager;

    public PostLectureRoute(UserManager manager, TemplateEngine engine) {
        this.manager = manager;
        LOGGER.config("PostLectureRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) throws ParseException {
        LOGGER.info("POST /lecture");

        String classCode = request.session().attribute("classCode");
        LOGGER.info("here is the class code: " + classCode);

        String lessonName = request.queryParams("addLessonName");
        String lessonStart = request.queryParams("addLessonStartTime");
        String lessonEnd = request.queryParams("addLessonEndTime");
        String deleteLessonID = request.queryParams("deleteLessonID");



        if(lessonName != null && !lessonName.equals("") && lessonStart != null && !lessonStart.equals("") && lessonEnd != null && !lessonEnd.equals("")){
            manager.insertLessonSQL(classCode, lessonName, lessonStart, lessonEnd);
        }

        if(deleteLessonID != null && !deleteLessonID.equals("")){
            manager.deleteLessonSQL(deleteLessonID);
        }

        response.redirect(WebServer.LECTURE, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }

}
