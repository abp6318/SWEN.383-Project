import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetDiscussionRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetDiscussionRoute.class.getName());

    private final Configuration conf;
    private UserManager manager;

    public GetDiscussionRoute(UserManager manager, Configuration conf){
        this.conf = conf;
        LOGGER.config("GetDiscussionRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetDiscussion Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)

            Template template = conf.getTemplate("discussion.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
        }
        return null;
    }

}
