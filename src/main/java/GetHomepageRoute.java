import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;

import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetHomepageRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetHomepageRoute.class.getName());

    private final Configuration conf;

    public GetHomepageRoute(Configuration c){
        this.conf = c;
        LOGGER.config("GetHomepageRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetHomepage Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)

            Template template = conf.getTemplate("home.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
            Spark.halt(HttpURLConnection.HTTP_INTERNAL_ERROR);
        }
        return null;
    }

}
