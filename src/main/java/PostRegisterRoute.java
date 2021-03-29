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

    private UserManager manager;

    public PostRegisterRoute(UserManager manager){
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

        manager.insertUserSQL(email, password, fname, lname);


        response.redirect(WebServer.LOGIN, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }


    public void sendValidationEmail(String to) {

        final String username = "iste383dreamteam";
        final String password = "abdulisourteacher";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("iste383dreamteam@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("**insert email**")
            );
            message.setSubject("Verify your email");
            int code = (int)(Math.random()*10000)+1;
            message.setText("Your verification code is " + code);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
