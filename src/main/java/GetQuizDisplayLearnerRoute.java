import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;

import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;


public class GetQuizDisplayLearnerRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetQuizDisplayLearnerRoute.class.getName());
    private final Configuration conf;

    private UserManager manager;

    public GetQuizDisplayLearnerRoute(UserManager manager, Configuration conf){
        this.manager = manager;
        this.conf = conf;
        LOGGER.config("GetQuizDisplayLearner Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetQuizDisplayLearner Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)
            User user = request.session().attribute("User");
            //i think the problem is here 
            String quizID = request.session().attribute("DisplayQuizID");

            List<QuizQuestion> quizQuestionsList = manager.selectQuizQuestionsSQL(quizID);

            Collection questionsL = new ArrayList();
            for (int count = 0; count<quizQuestionsList.size(); count++) {
                HashMap<String, String> question = quizQuestionsList.get(count).getHash();
                ((ArrayList) questionsL).add(question);
            }
            viewModel.put("questionsL", questionsL.iterator());

            Template template = conf.getTemplate("quizDisplayLearner.ftl");
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
