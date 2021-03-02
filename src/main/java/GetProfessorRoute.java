import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class GetProfessorRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetProfessorRoute.class.getName());

    private UserManager manager;
    private TemplateEngine engine;

    public GetProfessorRoute(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        this.engine = engine;
        LOGGER.config("GetProfessorRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetProfessor Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)
            return engine.render(new ModelAndView(viewModel, "professor.ftl"));
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
        }
        return null;
    }

}
