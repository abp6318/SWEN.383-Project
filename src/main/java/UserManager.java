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

    // TODO: Have it return everything, rather than just the verfied status & store in User object
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

    public void insertClassSQL(String classCode, String creatorEmail, String professorEmail, String className, String learningObj, String learningOutcome, String beginDate, String endDate){
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, classCode);
            preparedStatement.setString(2, creatorEmail);
            preparedStatement.setString(3, professorEmail);
            preparedStatement.setString(4, className);
            preparedStatement.setString(5, learningObj);
            preparedStatement.setString(6, learningOutcome);
            preparedStatement.setString(7, beginDate);
            preparedStatement.setString(8, endDate);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        }catch(SQLException sqle){
            System.out.println("\n\nINSERT CLASS FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    public void updateClassBeginDateSQL(String beginDate, String classCode){
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `class` SET beginDate = ? WHERE `classCode` = ?");
            preparedStatement.setString(1, beginDate);
            preparedStatement.setString(2, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        }catch(SQLException sqle){
            System.out.println("\n\nUPDATE CLASS BEGIN DATE FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    public void updateClassEndDateSQL(String endDate, String classCode){
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `class` SET endDate = ? WHERE `classCode` = ?");
            preparedStatement.setString(1, endDate);
            preparedStatement.setString(2, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        }catch(SQLException sqle){
            System.out.println("\n\nUPDATE CLASS END DATE FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    public void updateClassLearningObjectiveSQL(String learningObj, String classCode){
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `class` SET learningObj = ? WHERE `classCode` = ?");
            preparedStatement.setString(1, learningObj);
            preparedStatement.setString(2, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        }catch(SQLException sqle){
            System.out.println("\n\nUPDATE CLASS LEARNING OBJECTIVE FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    public void updateClassLearningOutcomeSQL(String learningOutcome, String classCode){
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `class` SET learningOutcome = ? WHERE `classCode` = ?");
            preparedStatement.setString(1, learningOutcome);
            preparedStatement.setString(2, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        }catch(SQLException sqle){
            System.out.println("\n\nUPDATE CLASS LEARNING OUTCOME FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    public void updateClassNameSQL(String className, String classCode){
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `class` SET className = ? WHERE `classCode` = ?");
            preparedStatement.setString(1, className);
            preparedStatement.setString(2, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        }catch(SQLException sqle){
            System.out.println("\n\nUPDATE CLASS NAME FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    public void updateClassProfessorEmailSQL(String professorEmail, String classCode){
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `class` SET professorEmail = ? WHERE `classCode` = ?");
            preparedStatement.setString(1, professorEmail);
            preparedStatement.setString(2, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        }catch(SQLException sqle){
            System.out.println("\n\nUPDATE CLASS PROFESSOR EMAIL FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    public void deleteClassSQL(String classCode){
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM `class` WHERE `classCode` = ?");
            preparedStatement.setString(1, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        }catch(SQLException sqle){
            System.out.println("\n\nDELETE CLASS FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    public void insertClassRatingSQL(String userEmail, String classCode, int rating){
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES (?, ?, ?)");
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, classCode);
            preparedStatement.setInt(3, rating);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        }catch(SQLException sqle){
            System.out.println("\n\nINSERT CLASS RATING FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    public void insertClassPrerequisiteSQL(String classCode, String preReqClassCode){
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `prerequisitesLookup` (`classCode`, `preReqClassCode`) VALUES (?, ?)");
            preparedStatement.setString(1, classCode);
            preparedStatement.setString(2, preReqClassCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        }catch(SQLException sqle){
            System.out.println("\n\nINSERT CLASS PREREQUISITE FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    public void updateClassPrerequisiteSQL(String newPreReqClassCode, String classCode, String oldPreReqClassCode){
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `prerequisitesLookup` SET `preReqClassCode` = ? WHERE `classCode` = ? AND `preReqClassCode` = ?");
            preparedStatement.setString(1, newPreReqClassCode);
            preparedStatement.setString(2, classCode);
            preparedStatement.setString(3, oldPreReqClassCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        }catch(SQLException sqle){
            System.out.println("\n\nUPDATE CLASS PREREQUISITE FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }
}
