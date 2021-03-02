import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetLoginRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetLoginRoute.class.getName());

    private final Configuration conf;

    public GetLoginRoute(Configuration conf){
        this.conf = conf;
        LOGGER.config("GetLoginRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetLogin Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)

            Template template = conf.getTemplate("login.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
        }
        return null;
    }

}
