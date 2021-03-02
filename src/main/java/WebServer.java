import java.util.logging.Logger;
import static spark.Spark.*;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

public class WebServer {

    private static final Logger LOGGER = Logger.getLogger(WebServer.class.getName());

    private UserManager manager;
    private TemplateEngine engine;

    // Routes
    public static final String HOMEPAGE = "/";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String ADMIN = "/admin";
    public static final String PROFESSOR = "/professor";
    public static final String LEARNER = "/learner";


    public WebServer(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        this.engine = engine;
    }

    public void initialize(){
        // healthcheck
        get("/ping", (res, req) -> "pong");

        // routes
        get(HOMEPAGE, new GetHomepageRoute(manager, engine));
        get(LOGIN, new GetLoginRoute(manager, engine));
        post(LOGIN, new PostLoginRoute(manager, engine));
        get(REGISTER, new GetRegisterRoute(manager, engine));
        post(REGISTER, new PostRegisterRoute(manager, engine));
        get(ADMIN, new GetAdminRoute(manager, engine));
    }

    public static void main(String[] args) {
        UserManager uMan = new UserManager();
        TemplateEngine eng = new FreeMarkerEngine();
        WebServer server = new WebServer(uMan, eng);

        server.initialize();
        LOGGER.config("Initialization Complete: Ready to handle request");
    }
}
