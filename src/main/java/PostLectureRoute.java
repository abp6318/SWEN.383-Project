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

        // create lesson
        String lessonName = request.queryParams("addLessonName");
        String lessonStart = request.queryParams("addLessonStartTime");
        String lessonEnd = request.queryParams("addLessonEndTime");

        // delete lesson
        String deleteLessonID = request.queryParams("deleteLessonID");

        // update start and end time
        String updateTimeLessonID = request.queryParams("updateTimeLessonID");
        String updatedStartTime = request.queryParams("updatedStartTime");
        String updatedEndTime = request.queryParams("updatedEndTime");

        // delete start and end time
        String deleteTimeLessonID = request.queryParams("deleteTimeLessonID");
        String deleteTime = request.queryParams("deleteTime");

        // getting lessonID so can display lectures associated with lessons
        String lessonID = request.queryParams("classButton");

        // getting multimedia link so can display lecture content
        String multimediaLink = request.queryParams("lectButton");

        // adding a lecture to a lesson (can assume that lessonID is set i think, but that may be a bold assumption)
        String addLectureName = request.queryParams("addLectureName");
        String addMultimedia = request.queryParams("addMultimedia");
        String addLessonID = request.queryParams("addLessonID");

        // updating a lecture
        String updateLectureID = request.queryParams("updateLectureID");
        String updateLectureName = request.queryParams("updateLectureName");
        String updateMultimedia = request.queryParams("updateMultimedia");

        // delete a lecture
        String deleteLectureID = request.queryParams("deleteLectureID");

        if(lessonID != null && !lessonID.equals("")){
            request.session().attribute("lessonID", lessonID);
            LOGGER.info("adding lessonID to session storage: " + lessonID);
        }

        if(multimediaLink != null && !multimediaLink.equals("")){
            request.session().attribute("multimediaLink", multimediaLink);
            LOGGER.info("adding multimediaLink to session storage: " + multimediaLink);
        }

        if(lessonName != null && !lessonName.equals("")){
            if(lessonStart.equals("")){
                lessonStart = null;
            }
            if(lessonEnd.equals("")){
                lessonEnd = null;
            }
            manager.insertLessonSQL(classCode, lessonName, lessonStart, lessonEnd);
        }

        if(deleteLessonID != null && !deleteLessonID.equals("")){
            manager.deleteLessonSQL(deleteLessonID);
        }

        if(updateTimeLessonID!= null && !updateTimeLessonID.equals("")){
            if(updatedStartTime != null && !updatedStartTime.equals("")){
                manager.updateLessonStartTimeSQL(updateTimeLessonID, updatedStartTime);
            }
            if(updatedEndTime != null && !updatedEndTime.equals("")){
                manager.updateLessonEndTimeSQL(updateTimeLessonID, updatedEndTime);
            }
        }
        if(deleteTimeLessonID != null && !deleteTimeLessonID.equals("")){
            if(deleteTime.trim().toLowerCase().equals("start")){
                manager.deleteLessonStartTimeSQL(deleteTimeLessonID);
            }
            else if(deleteTime.trim().toLowerCase().equals("end")){
                manager.deleteLessonEndTimeSQL(deleteTimeLessonID);
            }
        }

        if(addLectureName != null && !addLectureName.equals("") && addMultimedia != null & !addMultimedia.equals("") && addLessonID != null && !addLessonID.equals("")){
            manager.insertLectureSQL(addMultimedia, addLessonID, addLectureName);
        }

        if(updateLectureID != null && !updateLectureID.equals("")) {
            if (updateLectureName != null && !updateLectureName.equals("")) {
                manager.updateLectureNameSQL(updateLectureID, updateLectureName);
            }
            if (updateMultimedia != null && !updateMultimedia.equals("")) {
                manager.updateMultimediaSQL(updateLectureID, updateMultimedia);
            }
        }

        if(deleteLectureID!= null && !deleteLectureID.equals("")){
            manager.deleteLectureSQL(deleteLectureID);
        }

        response.redirect(WebServer.LECTURE, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }

}
