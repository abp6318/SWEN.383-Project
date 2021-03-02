import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetRegisterRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetRegisterRoute.class.getName());

    private UserManager manager;
    private TemplateEngine engine;

    public GetRegisterRoute(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        this.engine = engine;
        LOGGER.config("GetRegisterRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetRegister Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)
            return engine.render(new ModelAndView(viewModel, "register.ftl"));
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
        }
        return null;
    }

}
