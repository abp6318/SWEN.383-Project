import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;

import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;


public class GetQuizDisplayRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetQuizDisplayRoute.class.getName());
    private final Configuration conf;

    private UserManager manager;

    public GetQuizDisplayRoute(UserManager manager, Configuration conf){
        this.manager = manager;
        this.conf = conf;
        LOGGER.config("GetAdminRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetQuizDisplay Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)
            User user = request.session().attribute("User");
            String quizID = request.queryParams("DisplayQuizID");

            List<QuizQuestion> quizQuestions = manager.selectQuizQuestionsSQL(quizID);

            Collection questions = new ArrayList();
            for (int count = 0; count<quizQuestions.size(); count++) {
                HashMap<String, String> question = quizQuestions.get(count).getHash();
                ((ArrayList) questions).add(question);
            }
            viewModel.put("questions", questions.iterator());

            Template template = conf.getTemplate("quizDisplay.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;


            // get quizID from session
            // call selectQuizQuestionsSQL( quizID )
            // convert List of quiz questions to HashMap
            // write to page




        } catch (Exception e){
            LOGGER.warning(e.getMessage());
            Spark.halt(500);
        }
        return null;
    }

}
