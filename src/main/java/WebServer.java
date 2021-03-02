import java.util.logging.Logger;
import static spark.Spark.*;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

public class WebServer {

    private static final Logger LOGGER = Logger.getLogger(WebServer.class.getName());

    private UserManager manager;
    private TemplateEngine engine;
    private UserManagerTwo managerTwo;

    // Routes
    public static final String HOMEPAGE = "/";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String ADMIN = "/admin";
    public static final String PROFESSOR = "/professor";
    public static final String LEARNER = "/learner";
    public static final String FAILED = "/failed";


    public WebServer(UserManager manager, TemplateEngine engine, UserManagerTwo two){
        this.manager = manager;
        this.engine = engine;
        this.managerTwo = two;
    }

    public void initialize(){
        // healthcheck
        get("/ping", (res, req) -> "pong");

        // routes
        get(HOMEPAGE, new GetHomepageRoute(manager, engine));
        get(LOGIN, new GetLoginRoute(manager, engine));
        post(LOGIN, new PostLoginRoute(managerTwo, engine));
        get(REGISTER, new GetRegisterRoute(manager, engine));
        post(REGISTER, new PostRegisterRoute(managerTwo, engine));
        get(ADMIN, new GetAdminRoute(manager, engine));
        get(PROFESSOR, new GetProfessorRoute(manager, engine));
        get(LEARNER, new GetLearnerRoute(manager, engine));
        get(FAILED, new GetFailedRoute(manager, engine));
    }

    public static void main(String[] args) {
        UserManager uMan = new UserManager();
        TemplateEngine eng = new FreeMarkerEngine();
        UserManagerTwo manTwo = new UserManagerTwo();
        WebServer server = new WebServer(uMan, eng, manTwo);

        server.initialize();
        LOGGER.config("Initialization Complete: Ready to handle request");
    }
}
