import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetFailedRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetFailedRoute.class.getName());
    private final Configuration conf;

    private UserManager manager;
    private TemplateEngine engine;

    public GetFailedRoute(UserManager manager, Configuration conf){
        this.manager = manager;
        this.conf = conf;
        LOGGER.config("GetFailedRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetFailed Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)
            Template template = conf.getTemplate("failed.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
        }

        return null;
    }

}
