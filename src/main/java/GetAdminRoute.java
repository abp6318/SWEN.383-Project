import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.*;

import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;


public class GetAdminRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetAdminRoute.class.getName());
    private final Configuration conf;

    private UserManager manager;

    public GetAdminRoute(UserManager manager, Configuration conf){
        this.manager = manager;
        this.conf = conf;
        LOGGER.config("GetAdminRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetAdmin Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)
            User user = request.session().attribute("User");


            List<Course> courses = manager.selectAdminClassesSQL(user.getEmail());

            Collection classes = new ArrayList();
            for (int count = 0; count<courses.size(); count++) {
                HashMap<String, String> course = courses.get(count).getHash();
                ((ArrayList) classes).add(course);
            }
            viewModel.put("classes", classes.iterator());

            // start of adding discussion groups to admin dashboard

            List<DiscussionGroup> discussionGroupsList = manager.selectDiscussionGroupsSQL(user.getEmail());

            Collection discussionGroups = new ArrayList();
            for (int index = 0; index<discussionGroupsList.size(); index++) {
                HashMap<String, String> discussionGroup = discussionGroupsList.get(index).getHash();
                ((ArrayList) discussionGroups).add(discussionGroup);
            }

            viewModel.put("discussionGroups", discussionGroups.iterator());

            // end of adding discussion groups to admin dashboard




            Template template = conf.getTemplate("admin.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
            Spark.halt(500);
        }
        return null;
    }

}
