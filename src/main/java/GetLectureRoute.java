import spark.Route;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GetLectureRoute implements Route {
    private static final Logger LOGGER = Logger.getLogger(GetLectureRoute.class.getName());

    private final Configuration conf;
    private UserManager manager;

    public GetLectureRoute(UserManager manager, Configuration conf) {
        this.conf = conf;
        this.manager = manager;
        LOGGER.config("GetLectureRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetLecture Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)

            Template template = conf.getTemplate("lecture.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
        return null;
    }

}
