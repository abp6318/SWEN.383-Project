import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetProfessorRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetProfessorRoute.class.getName());
    private final Configuration conf;

    private UserManager manager;

    public GetProfessorRoute(UserManager manager, Configuration conf){
        this.manager = manager;
        this.conf = conf;
        LOGGER.config("GetProfessorRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetProfessor Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)
            User user = request.session().attribute("User");
            viewModel.put("fname", user.getFirstName());
            viewModel.put("lname", user.getLastName());

            Template template = conf.getTemplate("professor.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
            Spark.halt(500);
        }
        return null;
    }

}
