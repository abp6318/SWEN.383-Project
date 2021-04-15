import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

public class PostDiscussionRoute implements Route {

    private static final Logger LOGGER = Logger.getLogger(PostDiscussionRoute.class.getName());

    private UserManager manager;

    public PostDiscussionRoute(UserManager manager, TemplateEngine engine) {
        this.manager = manager;
        LOGGER.config("PostDiscussionRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /discussion");

        String addGroupName = request.queryParams("addDiscussionName");
        String addDiscussionEmail = request.queryParams("addDiscussionEmail");

        String deleteGroupID = request.queryParams("DeleteDiscussionID");

        String search = request.queryParams("searchBar");

        // will need to figure out searching somewhere here too probably
        if (!search.equals("")) {
            List<DiscussionGroup> temp = manager.searchDiscussionGroup(search);
            request.session().attribute("Search Results", temp);
        }

        // creating discussion group
        if (addGroupName != null && !addGroupName.equals("") && addDiscussionEmail != null
                && !addDiscussionEmail.equals("")) {
            manager.addDiscussionGroupSQL(addGroupName, addDiscussionEmail);

            String id = manager.getDiscussionIdSQL(addGroupName);

            System.out.println(id);

            manager.addDiscussionGroupMembersSQL(id, addDiscussionEmail);

        }

        // delete discussion group
        if (deleteGroupID != null && !deleteGroupID.equals("")) {
            manager.deleteDiscussionGroupSQL(deleteGroupID);

        }

        response.redirect(WebServer.DISCUSSION, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }

}
