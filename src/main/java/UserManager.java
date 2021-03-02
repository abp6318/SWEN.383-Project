import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;

public class UserManager{

    private Connection conn;
    // private ResultSet rs;
    private Statement stmt;
    private String sql;
    private int col;

    final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";

    public UserManager(){}

    /**
     Establishes a connection to the database
     @return     True if connection is established
     */
    public boolean connect() {
        conn = null;
        String userName = "root";
        String password = "student?";
        String url = "jdbc:mysql://localhost/mypls";

        // comment out the line below if you are not using a mac
        url = url + "?serverTimezone=UTC"; //added 8/27

        try {
            Class.forName(DEFAULT_DRIVER);
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("\nCreated Connection!\n");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("ERROR, CAN NOT CONNECT!!");
            System.out.println("Class");
            System.out.println("ERROR MESSAGE-> " + cnfe);
            System.exit(0);
        } catch (SQLException sqle) {
            System.out.println("ERROR SQLExcepiton in connect()");
            System.out.println("ERROR MESSAGE -> " + sqle);
            sqle.printStackTrace();
            System.exit(0);
        }//end of catch

        return (conn!=null);
    }

    /**
     Closes the connection to the database
     */
    public void close(){
        try {
            // rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException sqle){
            System.out.println("ERROR IN METHOD close()");
            System.out.println("ERROR MESSAGE -> "+sqle);
        }// end of catch
    }//end of method close

    /* **************************************************************************************** */
    /* **************************************************************************************** */
    /*
          userEmail VARCHAR(100) NOT NULL,
          fName VARCHAR(75) NOT NULL,
          lName VARCHAR(75) NOT NULL,
          accountType CHAR (1),
          verified BOOLEAN NOT NULL,
          userPassword VARCHAR(20) NOT NULL ,
     */
    /* **************************************************************************************** */

    /**
     Inserts a user into the user table
     */
    public void insertUserSQL(String email, String fname, String lname, String passwordUnencrypted){
        int rows = 0;
        // encrypt student's password
        String passwordEncrypted = this.encryptPassword(passwordUnencrypted);
        try{
            // add them to the students table
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO user(userEmail, fName, lName, verified, userPassword) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, fname);
            preparedStatement.setString(3, lname);
            preparedStatement.setInt(4, 0);
            preparedStatement.setString(5, passwordEncrypted);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        }catch(SQLException sqle){
            System.out.println("\n\nINSERT USER FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }




    /**
     Encrypts a password using SHA1
     @param   secret   The user's password
     @return           The user's passsword now encrypted
     */
    public String encryptPassword(String secret){

        String sha1 = "";
        String value = new String(secret);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(value.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        }catch(Exception e){
            e.printStackTrace();
        }// end of catch

        System.out.println( "The sha1 of \""+ value + "\" is:");
        System.out.println("--->" + sha1 );
        return sha1;
    }//end of function
}
