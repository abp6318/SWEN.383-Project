import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetAdminRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetAdminRoute.class.getName());

    private UserManager manager;
    private TemplateEngine engine;

    public GetAdminRoute(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        this.engine = engine;
        LOGGER.config("GetAdminRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetAdmin Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)
            viewModel.put("fname", "fname");
            viewModel.put("lname", "lname");
            return engine.render(new ModelAndView(viewModel, "admin.ftl"));
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
        }
        return null;
    }

}
