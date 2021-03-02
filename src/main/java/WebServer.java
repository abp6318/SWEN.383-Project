import java.util.logging.Logger;
import static spark.Spark.*;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

public class WebServer {

    private static final Logger LOGGER = Logger.getLogger(WebServer.class.getName());

    private UserManager manager;
    private TemplateEngine engine;

    // Routes
    private static final String HOMEPAGE = "/";

    public WebServer(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        this.engine = engine;
    }

    public void initialize(){
        get(HOMEPAGE, new GetHomepageRoute(engine, manager));
    }

    public static void main(String[] args) {
        UserManager uMan = new UserManager();
        TemplateEngine eng = new FreeMarkerEngine();
        WebServer server = new WebServer(uMan, eng);

        server.initialize();
        LOGGER.config("Initialization Complete: Ready to handle request");
    }
}
