import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetFeedbackRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetFeedbackRoute.class.getName());

    private final Configuration conf;
    private UserManager manager;

    public GetFeedbackRoute(UserManager manager, Configuration conf){
        this.conf = conf;
        LOGGER.config("GetFeedbackRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetFeedback Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)

            Template template = conf.getTemplate("rating.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
        }
        return null;
    }

}
