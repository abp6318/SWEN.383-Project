import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;
import java.util.*;
import java.util.logging.Logger;

public class GetLectureLRoute implements Route {
    private static final Logger LOGGER = Logger.getLogger(GetLectureLRoute.class.getName());

    private final Configuration conf;
    private UserManager manager;

    public GetLectureLRoute(UserManager manager, Configuration conf) {
        this.conf = conf;
        this.manager = manager;
        LOGGER.config("GetLectureLRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetLectureL Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)

            String classCode = request.session().attribute("classCode");
            String lessonID = request.session().attribute("lessonID");
            String multimediaLink = request.session().attribute("multimediaLink");

            List<Lesson> lessons = manager.selectLessonsSQL(classCode);

            Collection allLessons = new ArrayList();
            for (int index = 0; index<lessons.size(); index++) {
                HashMap<String, String> lesson = lessons.get(index).getHash();
                ((ArrayList) allLessons).add(lesson);
            }

            if(lessonID != null && !lessonID.equals("")){
                LOGGER.info("Seeing LessonID has value from session storage: " + lessonID);
                // create lecture list for display
                List<Lecture> lectures = manager.selectLecturesSQL(lessonID);
                Collection allLectures = new ArrayList();
                for (int index = 0; index<lectures.size(); index++) {
                    HashMap<String, String> lecture = lectures.get(index).getHash();
                    ((ArrayList) allLectures).add(lecture);
                }
                LOGGER.info("putting allLectures into view model");
                viewModel.put("allLectures", allLectures);
            }

            viewModel.put("allLessons", allLessons.iterator());
            viewModel.put("classCode", classCode);
            viewModel.put("multimediaLink", multimediaLink);

            Template template = conf.getTemplate("lectureL.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
        return null;
    }

}
