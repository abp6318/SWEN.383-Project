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


            // TODO: need a database call to get all of the admin's classes so they can by dynamically put into the ftl file
            List<Course> courses = manager.selectAdminClassesSQL(user.getEmail());
            // iterate through courses and add attributes to hashmap -- Vicky is making a method to convert to HashMap and then we can do the thing!

            // TODO: need to figure out some way to dynamically put classes into the ftl file (there may be some kind of for-loop we can use with the html table elements

            // TODO: add some logic in the admin ftl file that lets people move from this page to the discussion group page by clicking some 'discussion group button'

            HashMap<String, String> one = new HashMap<>();
            one.put("classcode", "CLASS1234");
            one.put("adminEmail","admin@admin.com");
            one.put("profEmail","professor@prof.com");
            one.put("className", "ClassName");
            one.put("objective", "objective");
            one.put("outcome","outcome");
            one.put("start","start");
            one.put("end","end");

            HashMap<String, String> two = new HashMap<>();
            two.put("classcode", "ABDUL123");
            two.put("adminEmail","abdul@abdul.abdul");
            two.put("profEmail","abdul@abdul.abdul");
            two.put("className","abdul");
            two.put("objective","abdulObj");
            two.put("outcome","abdulOut");
            two.put("start","abdulStart");
            two.put("end","abdulEnd");

            Collection classes = new ArrayList();
            ((ArrayList) classes).add(one);
            ((ArrayList) classes).add(two);
            viewModel.put("classes", classes.iterator());

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
