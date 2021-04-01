import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;


public class PostDiscussionRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(PostDiscussionRoute.class.getName());

    private UserManager manager;

    public PostDiscussionRoute(UserManager manager, TemplateEngine engine){
        this.manager = manager;
        LOGGER.config("PostDiscussionRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /discussion");

        String groupName = request.queryParams("groupname");
        String email = request.queryParams("email");
        String search = request.queryParams("searchBar");

        // Aaron: this is where some discission actions can be taken
            // add new discussion data to db with email and groupName info
        System.out.println("Outside of the if");
        // will need to figure out searching somewhere here too probably
        if(!search.equals("")){
            System.out.println("Inside of the if");
            List<DiscussionGroup> temp = manager.searchDiscussionGroup(search);
            System.out.println("Second part");
            request.session().attribute("Search Results", temp);
            System.out.println(temp);
        }
        System.out.println("After the if");


        response.redirect(WebServer.DISCUSSION, HttpURLConnection.HTTP_MOVED_PERM);


        return null;
    }

}
