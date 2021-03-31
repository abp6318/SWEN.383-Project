import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.StringWriter;
import java.util.*;
import java.util.logging.Logger;


public class GetFeedbackRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetFeedbackRoute.class.getName());

    private final Configuration conf;
    private UserManager manager;

    public GetFeedbackRoute(UserManager manager, Configuration conf){
        this.conf = conf;
        this.manager = manager;
        LOGGER.config("GetFeedbackRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetFeedback Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)

            //adding ratings to admin dashboard
            List<Feedback> feedbackList = manager.selectRatingClassesSQL();
            Collection feedback = new ArrayList();
            for (int index = 0; index<feedbackList.size(); index++) {
                HashMap<String, String> rating = feedbackList.get(index).getHash();
                ((ArrayList) feedback).add(rating);
            }

            viewModel.put("feedback", feedback.iterator());

            HashMap<String, String> classAvgs = new HashMap<>();
            List<String> classCodes = manager.selectRatedClassesSQL();
            for (String code : classCodes){
                int avg = manager.selectClassAvgSQL(code);
                classAvgs.put(code, Integer.toString(avg));
            }

            viewModel.put("avgs", classAvgs);

            Template template = conf.getTemplate("rating.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
        }
        return null;
    }

}
