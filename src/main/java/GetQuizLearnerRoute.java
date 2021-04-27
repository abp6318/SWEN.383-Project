import spark.Route;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GetQuizLearnerRoute implements Route {
    private static final Logger LOGGER = Logger.getLogger(GetQuizLearnerRoute.class.getName());

    private final Configuration conf;
    private UserManager manager;

    public GetQuizLearnerRoute(UserManager manager, Configuration conf) {
        this.conf = conf;
        this.manager = manager;
        LOGGER.config("GetQuizLearnerRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetQuizLearner Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)  
            User user = request.session().attribute("User");     
            
            List<Quiz> stuQuizzesList = manager.getAllLearnerQuizzes(user.getEmail());

            //very much not done here
            Collection studentQuizzes = new ArrayList();
            for (int index = 0; index<stuQuizzesList.size(); index++) {
                HashMap<String, String> quiz = stuQuizzesList.get(index).getHash();
                ((ArrayList) studentQuizzes).add(quiz);
            }
            viewModel.put("studentQuizzes", studentQuizzes.iterator());

            Template template = conf.getTemplate("quizLearner.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
        return null;
    }
}
