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



public class GetDiscussionPRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(GetDiscussionPRoute.class.getName());

    private final Configuration conf;
    private UserManager manager;

    public GetDiscussionPRoute(UserManager manager, Configuration conf){
        this.conf = conf;
        this.manager = manager;
        LOGGER.config("GetDiscussionRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("GetDiscussion Called");
        try {
            Map<String, Object> viewModel = new HashMap<>(); // mapping dynamic variables for ftl files (freemarker template)

            List<DiscussionGroup> discussionGroupsList = request.session().attribute("Search Results");
            if (discussionGroupsList!=null) {
                Collection discussionGroups = new ArrayList();
                for (int index = 0; index<discussionGroupsList.size(); index++) {
                    HashMap<String, String> discussionGroup = discussionGroupsList.get(index).getHash();
                    ((ArrayList) discussionGroups).add(discussionGroup);
                }
                viewModel.put("discussionGroups", discussionGroups.iterator());
            }

            User user = request.session().attribute("User");
            List<DiscussionGroup> discussionGroups = manager.selectDiscussionGroupsSQL(user.getEmail());

            Collection allGroups = new ArrayList();
            for (int index = 0; index<discussionGroups.size(); index++) {
                HashMap<String, String> discussionGroup = discussionGroups.get(index).getHash();
                ((ArrayList) allGroups).add(discussionGroup);
            }

            viewModel.put("allGroups", allGroups.iterator());


            Template template = conf.getTemplate("discussionP.ftl");
            StringWriter writer = new StringWriter();
            template.process(viewModel, writer);

            return writer;
        } catch (Exception e){
            LOGGER.warning(e.getMessage());
        }
        return null;
    }

}
