import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetRegisterRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetRegisterRoute.class.getName());
    private final Configuration conf;

    private UserManager manager;

    public GetRegisterRoute(UserManager manager, Configuration c){
        this.manager = manager;
        this.conf = c;
        LOGGER.config("GetRegisterRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetRegister Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)


            Template template = conf.getTemplate("register.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
        }
        return null;
    }

}
