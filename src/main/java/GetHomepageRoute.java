import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetHomepageRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetHomepageRoute.class.getName());

    private UserManager manager;
    private TemplateEngine engine;

    public GetHomepageRoute(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        this.engine = engine;
        LOGGER.config("GetHomepageRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetHomepage Called");
        Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)
        viewModel.put("appName", "myPLS"); // second param can be any object
        return engine.render(new ModelAndView(viewModel, "home.ftl"));
    }

}
