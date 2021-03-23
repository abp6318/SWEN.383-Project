import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetAdminRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetAdminRoute.class.getName());
    private final Configuration conf;

    private UserManager manager;

    public GetAdminRoute(UserManager manager, Configuration conf){
        this.manager = manager;
        this.conf = conf;
        LOGGER.config("GetAdminRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetAdmin Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)
            User user = request.session().attribute("User");
            viewModel.put("fname", user.getFirstName());
            viewModel.put("lname", user.getLastName());

            // TODO: need a database call to get all of the admin's classes so they can by dynamically put into the ftl file

            // TODO: need to figure out some way to dynamically put classes into the ftl file (there may be some kind of for-loop we can use with the html table elements

            // TODO: add some logic in the admin ftl file that lets people move from this page to the discussion group page by clicking some 'discussion group button'

            Template template = conf.getTemplate("admin.ftl");
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
