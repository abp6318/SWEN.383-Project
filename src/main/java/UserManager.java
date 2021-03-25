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
    private ResultSet rs;
    private Statement stmt;
    private String sql;
    private int col;
    private User userObj;
    private static User invalidUserObj = new User("", "", "", "", "false", "");

    final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";

    public UserManager(){}

    /**
     Establishes a connection to the database
     @return     True if connection is established
     */
    public boolean connect() {
        conn = null;
        String userName = "be068263fb79ee";
        String password = "a082b918";
        String url = "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_6cdec71a68a6b18";

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
            rs.close();
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
    /* **************************************************************************************** */

    /**
     * Inserts a user into the user table
     * @param email                 A user's email
     * @param fname                 A user's first name
     * @param lname                 A user's last name
     * @param passwordUnencrypted   A user's unencrypted password
     */
    public void insertUserSQL(String email, String fname, String lname, String passwordUnencrypted){
        int rows = 0;
        // encrypt users' password
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
     * Changes the user with the parameter email to be verified.
     * @param email     A user's email
     */
    public void updateUserVerification(String email){
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE user SET verified=? WHERE userEmail=?");
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, email);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");
        }catch(SQLException sqle){
            System.out.println("\n\nUPDATE USER VERIFICATION FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Confirms whether users have a verified account and should be allowed to log in
     * @param email                 The user's input email
     * @param passwordUnencrypted   The user's input password
     * @return                      True if the user is permitted, false otherwise
     */
    public User userLogin(String email, String passwordUnencrypted){
        String passwordEncrypted = this.encryptPassword(passwordUnencrypted);
        try{
            stmt = conn.createStatement();
            String query = "SELECT userEmail, fName, lName, accountType, verified, userPassword FROM user WHERE userEmail=\""+email+"\" AND userPassword=\""+passwordEncrypted+"\";";
            rs = stmt.executeQuery(query);
            String verifiedValue = "";

            if(rs.next()){
                String userEmailTemp = rs.getString(1);
                String fNameTemp = rs.getString(2);
                String lNameTemp = rs.getString(3);
                String accountTypeTemp = rs.getString(4);
                String verifiedTemp = rs.getString(5);
                String userPasswordTemp = rs.getString(6);
                userObj = new User(userEmailTemp, fNameTemp, lNameTemp, accountTypeTemp, verifiedTemp, userPasswordTemp);
                return userObj;
            }
        }catch(SQLException sqle){
            System.out.println("\n\nUSER LOGIN FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return invalidUserObj;
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

    /**
     * Creates a discussion group 
     @param    groupName    The name of the discussion group
     @param    email        The creator's email 
     */
    public void addDiscussionGroupSQL(String groupName, String email) {
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO discussionGroups(groupName, email) VALUES (?,?)");
            stmt.setString(1, groupName);
            stmt.setString(2, email);
            stmt.executeUpdate();
        }//end of try
        catch (Exception e) {
            System.out.println("Error while trying to add discussion group.");
            System.out.println("ERROR MESSAGE --> " + e);
        }//end of catch
    }//end of function

    /**
     * Deletes a discussion group
     @param    groupName    The name of the group to be deleted
     */
    public void deleteDiscussionGroupSQL(String groupName) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM discussionGroups WHERE groupName=?");
            stmt.setString(1, groupName);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("Error while trying to delete a discussion group.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end of catch
    }//end of function
}
