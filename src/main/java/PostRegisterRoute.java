import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Logger;


public class PostRegisterRoute implements Route{

    private static final Logger LOGGER = Logger.getLogger(PostRegisterRoute.class.getName());

    private UserManagerTwo manager;

    public PostRegisterRoute(UserManagerTwo manager){
        this.manager = manager;
        LOGGER.config("PostRegisterRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /register");

        String email = request.queryParams("email");
        String password = request.queryParams("password");
        String fname = request.queryParams("fname");
        String lname = request.queryParams("lname");

        manager.userRegister(email, password, fname, lname);

        //sendValidationEmail("dmolee@gmail.com");

        response.redirect(WebServer.LOGIN, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }


    public void sendValidationEmail(String to) {

        // Sender's email ID needs to be mentioned
        String from = "dmolee@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);
            LOGGER.info("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
