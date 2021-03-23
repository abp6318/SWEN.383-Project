import java.util.logging.Logger;
import static spark.Spark.*;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

public class WebServer {


    // general TODOs
    // TODO: create discussion group ftl files
    // TODO: Create discussion group object?
    // TODO: Create get/post routes for discussion group page
    // TODO: add redirect in POST to new discussion group page if something is added/updated


    // TODO: some kind of search functionality???? on discussion groups??

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
    private Configuration conf;


    public WebServer(){ }

    public void setRoutes(){
        // healthcheck
        get("/ping", (res, req) -> "pong");

        // routes
        get(HOMEPAGE, new GetHomepageRoute(conf));
        get(LOGIN, new GetLoginRoute(conf));
        post(LOGIN, new PostLoginRoute(managerTwo, engine));
        get(REGISTER, new GetRegisterRoute(manager, conf));
        post(REGISTER, new PostRegisterRoute(managerTwo));
        get(ADMIN, new GetAdminRoute(manager, conf));
        get(PROFESSOR, new GetProfessorRoute(manager, conf));
        get(LEARNER, new GetLearnerRoute(manager, conf));
        get(FAILED, new GetFailedRoute(manager, conf));
    }

    public void initialize(){
        this.managerTwo = new UserManagerTwo();
        Configuration conf = new Configuration(new Version(2, 3, 23));
        conf.setClassForTemplateLoading(WebServer.class, "/spark.template.freemarker");
        this.conf = conf;
        this.engine = new FreeMarkerEngine(conf);
        this.managerTwo = new UserManagerTwo();

        setRoutes();
    }

    public static void main(String[] args) {
        WebServer server = new WebServer();

        server.initialize();
        LOGGER.config("Initialization Complete: Ready to handle request");
    }
}
