import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;

import java.io.StringWriter;
import java.util.*;
import java.util.logging.Logger;


public class GetLearnerRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetLearnerRoute.class.getName());

    private UserManager manager;
    private Configuration conf;

    public GetLearnerRoute(UserManager manager, Configuration c){
        this.manager = manager;
        this.conf = c;
        LOGGER.config("GetLearnerRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetLearner Called");
        try {
                Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)
                User user = request.session().attribute("User");
                viewModel.put("fname", user.getFirstName());
                viewModel.put("lname", user.getLastName());

                List<Course> courses = manager.selectLearnerClassesSQL(user.getEmail());
                Collection classes = new ArrayList();
                for (int count = 0; count < courses.size(); count++) {
                    HashMap<String, String> course = courses.get(count).getHash();
                    ((ArrayList) classes).add(course);
                }
                viewModel.put("classes", classes.iterator());

                // start of adding discussion groups to admin dashboard

                List<DiscussionGroup> discussionGroupsList = manager.selectDiscussionGroupsSQL(user.getEmail());

                Collection discussionGroups = new ArrayList();
                for (int index = 0; index < discussionGroupsList.size(); index++) {
                    HashMap<String, String> discussionGroup = discussionGroupsList.get(index).getHash();
                    ((ArrayList) discussionGroups).add(discussionGroup);
                }

                viewModel.put("discussionGroups", discussionGroups.iterator());

                Template template = conf.getTemplate("learner.ftl");
                StringWriter writer = new StringWriter();
                template.process(viewModel, writer);


                return writer;
            } catch (Exception e) {
                LOGGER.warning(e.getMessage());
            }
        return null;
    }

}



