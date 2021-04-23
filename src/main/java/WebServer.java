import java.util.logging.Logger;
import static spark.Spark.*;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

public class WebServer {

    // general TODOs
    // TODO: add redirect in POST to new discussion group page if something is
    // added/updated

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
    public static final String FAILED = "/failed";
    public static final String DISCUSSIONA = "/discussionA";
    public static final String DISCUSSIONP = "/discussionP";
    public static final String DISCUSSIONL = "/discussionL";
    public static final String FEEDBACK = "/feedback";
    public static final String QUIZ = "/quiz";
    public static final String MULTIMEDIA = "/multimedia";
    public static final String MESSAGESDG = "/messagesDG";
    public static final String LECTURE = "/lecture";
    public static final String QUIZDISPLAY = "/quizDisplay";
    public static final String QUIZLEARNER = "/quizLearner";
    public static final String QUIZDISPLAYLEARNER = "/quizDisplayLearner";

    private Configuration conf;

    public WebServer() {
    }

    public void setRoutes() {
        // healthcheck
        get("/ping", (res, req) -> "pong");

        // routes
        get(HOMEPAGE, new GetHomepageRoute(conf));
        get(LOGIN, new GetLoginRoute(conf));
        post(LOGIN, new PostLoginRoute(manager, engine));
        get(REGISTER, new GetRegisterRoute(manager, conf));
        post(REGISTER, new PostRegisterRoute(manager));
        get(ADMIN, new GetAdminRoute(manager, conf));
        get(PROFESSOR, new GetProfessorRoute(manager, conf));
        get(LEARNER, new GetLearnerRoute(manager, conf));
        get(FAILED, new GetFailedRoute(manager, conf));
        get(DISCUSSIONA, new GetDiscussionARoute(manager, conf));
        post(DISCUSSIONA, new PostDiscussionARoute(manager, engine));
        get(DISCUSSIONP, new GetDiscussionPRoute(manager, conf));
        post(DISCUSSIONP, new PostDiscussionPRoute(manager, engine));
        get(DISCUSSIONL, new GetDiscussionLRoute(manager, conf));
        post(DISCUSSIONL, new PostDiscussionLRoute(manager, engine));
        get(FEEDBACK, new GetFeedbackRoute(manager, conf));
        post(FEEDBACK, new PostFeedbackRoute(manager, engine));
        post(ADMIN, new PostAdminRoute(manager, engine));
        get(QUIZ, new GetQuizRoute(manager, conf));
        post(QUIZ, new PostQuizRoute(manager, engine));
        get(MESSAGESDG, new GetMessagesRoute(manager, conf));
        post(MESSAGESDG, new PostMessagesRoute(manager, engine));
        get(LECTURE, new GetLectureRoute(manager, conf));
        post(LECTURE, new PostLectureRoute(manager, engine));
        get(MULTIMEDIA, new GetMultimediaRoute(manager, conf));
        post(MULTIMEDIA, new PostMultimediaRoute(manager, engine));
        get(QUIZDISPLAY, new GetQuizDisplayRoute(manager, conf));
        post(PROFESSOR, new PostProfessorRoute(manager,engine));
        get(QUIZLEARNER, new GetQuizLearnerRoute(manager, conf));
        post(QUIZLEARNER, new PostQuizLearnerRoute(manager, engine));
        get(QUIZDISPLAYLEARNER, new GetQuizDisplayLearnerRoute(manager, conf));
        post(QUIZDISPLAYLEARNER, new PostQuizDisplayLearnerRoute(manager, engine));
    }

    public void initialize() {
        this.manager = new UserManager();
        manager.connect();
        Configuration conf = new Configuration(new Version(2, 3, 23));
        conf.setClassForTemplateLoading(WebServer.class, "/spark.template.freemarker");
        this.conf = conf;
        this.engine = new FreeMarkerEngine(conf);

        setRoutes();
    }

    public static void main(String[] args) {
        WebServer server = new WebServer();

        server.initialize();
        LOGGER.config("Initialization Complete: Ready to handle request");
    }
}
