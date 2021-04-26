import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.math.BigInteger;
import java.security.MessageDigest;

public class UserManager {

    private Connection conn;
    private ResultSet rs;
    private Statement stmt;
    private String sql;
    private int col;
    private User userObj;
    private static User invalidUserObj = new User("", "", "", "", "0", "");

    final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";

    public UserManager() {
    }

    /**
     * Establishes a connection to the database
     *
     * @return True if connection is established
     */
    public boolean connect() {
        conn = null;
        String userName = "b4c3c11a08a12d";
        String password = "36983285";
        String url = "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_3a5f011235c10c7?reconnect=true";

        try {
            Class.forName(DEFAULT_DRIVER);
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("\nCreated Connection!\nhttp://localhost:4567/login \n");
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
        } // end of catch

        return (conn != null);
    }

    /**
     * Closes the connection to the database
     */
    public void close() {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException sqle) {
            System.out.println("ERROR IN METHOD close()");
            System.out.println("ERROR MESSAGE -> " + sqle);
        } // end of catch
    }// end of method close

    /**
     * Inserts a user into the user table
     *
     * @param email               A user's email
     * @param fname               A user's first name
     * @param lname               A user's last name
     * @param passwordUnencrypted A user's unencrypted password
     */
    public void insertUserSQL(String email, String fname, String lname, String passwordUnencrypted) {
        int rows = 0;
        // encrypt users' password
        String passwordEncrypted = this.encryptPassword(passwordUnencrypted);
        try {
            // add them to the students table
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO user(userEmail, fName, lName, verified, userPassword) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, fname);
            preparedStatement.setString(3, lname);
            preparedStatement.setInt(4, 0);
            preparedStatement.setString(5, passwordEncrypted);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nINSERT USER FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Changes the user with the parameter email to be verified.
     *
     * @param email A user's email
     */
    public void updateUserVerification(String email) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE user SET verified=? WHERE userEmail=?");
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, email);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");
        } catch (SQLException sqle) {
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
    public User userLogin(String email, String passwordUnencrypted) {
        String passwordEncrypted = this.encryptPassword(passwordUnencrypted);
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT userEmail, fName, lName, accountType, verified, userPassword FROM user WHERE userEmail=? AND userPassword=?");
            System.out.println(email);
            System.out.println(passwordEncrypted);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwordEncrypted);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String userEmailTemp = rs.getString(1);
                String fNameTemp = rs.getString(2);
                String lNameTemp = rs.getString(3);
                String accountTypeTemp = rs.getString(4);
                String verifiedTemp = rs.getString(5);
                String userPasswordTemp = rs.getString(6);
                userObj = new User(userEmailTemp, fNameTemp, lNameTemp, accountTypeTemp, verifiedTemp,
                        userPasswordTemp);
                return userObj;
            }
        } catch (SQLException sqle) {
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
    public String encryptPassword(String secret) {

        String sha1 = "";
        String value = new String(secret);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(value.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        } // end of catch

        System.out.println("The sha1 of \"" + value + "\" is:");
        System.out.println("--->" + sha1);
        return sha1;
    }// end of function

    /**
     *  Inserts a class into the database 
     * @param classCode the class code 
     * @param creatorEmail the admin's email 
     * @param professorEmail the email of the professor of the class
     * @param className the name of the class 
     * @param learningObj the learning objective of the class
     * @param beginDate the date the class starts
     * @param endDate the date the class ends 
     */
    public void insertClassSQL(String classCode, String creatorEmail, String professorEmail, String className,
            String learningObj, String beginDate, String endDate) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, classCode);
            preparedStatement.setString(2, creatorEmail);
            preparedStatement.setString(3, professorEmail);
            preparedStatement.setString(4, className);
            preparedStatement.setString(5, learningObj);
            preparedStatement.setString(6, "");
            preparedStatement.setString(7, beginDate);
            preparedStatement.setString(8, endDate);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nINSERT CLASS FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Updates the beginDate of a class that is already in the database
     * @param beginDate the start date of the class
     * @param classCode the class to be updated
     */
    public void updateClassBeginDateSQL(String beginDate, String classCode) {
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE `class` SET beginDate = ? WHERE `classCode` = ?");
            preparedStatement.setString(1, beginDate);
            preparedStatement.setString(2, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nUPDATE CLASS BEGIN DATE FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * updateds the end date of a class aready in the database
     * @param endDate the end date of the class
     * @param classCode the class to be updated
     */
    public void updateClassEndDateSQL(String endDate, String classCode) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("UPDATE `class` SET endDate = ? WHERE `classCode` = ?");
            preparedStatement.setString(1, endDate);
            preparedStatement.setString(2, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nUPDATE CLASS END DATE FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    public void updateClassLearningObjectiveSQL(String learningObj, String classCode) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("UPDATE `class` SET learningObj = ? WHERE `classCode` = ?");
            preparedStatement.setString(1, learningObj);
            preparedStatement.setString(2, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nUPDATE CLASS LEARNING OBJECTIVE FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Updates the learning outcome of a class already in the database
     * @param learningOutcome the learning outcome to be changed
     * @param classCode the class to be updated
     */
    public void updateClassLearningOutcomeSQL(String learningOutcome, String classCode) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("UPDATE `class` SET learningOutcome = ? WHERE `classCode` = ?");
            preparedStatement.setString(1, learningOutcome);
            preparedStatement.setString(2, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nUPDATE CLASS LEARNING OUTCOME FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Updates the class name
     * @param className the new class name
     * @param classCode the class to be updated 
     */
    public void updateClassNameSQL(String className, String classCode) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("UPDATE `class` SET className = ? WHERE `classCode` = ?");
            preparedStatement.setString(1, className);
            preparedStatement.setString(2, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nUPDATE CLASS NAME FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Updates the professor email of a class
     * @param professorEmail the new professor email
     * @param classCode the class that is being updated
     */
    public void updateClassProfessorEmailSQL(String professorEmail, String classCode) {
        int rows = 0;
        try{
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE class SET professorEmail = ? WHERE classCode = ?");
            System.out.println(preparedStatement);
            preparedStatement.setString(1, professorEmail);
            preparedStatement.setString(2, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nUPDATE CLASS PROFESSOR EMAIL FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Deletes a class
     * @param classCode the class to be deleted
     */
    public void deleteClassSQL(String classCode) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM `class` WHERE `classCode` = ?");
            preparedStatement.setString(1, classCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nDELETE CLASS FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Adds a class rating
     * @param userEmail the user rating the class
     * @param classCode the class that is getting rated
     * @param rating the rating
     */
    public void insertClassRatingSQL(String userEmail, String classCode, int rating) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES (?, ?, ?)");
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, classCode);
            preparedStatement.setInt(3, rating);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nINSERT CLASS RATING FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Adds a class prereq
     * @param classCode the class to be updated
     * @param preReqClassCode the new prereq
     */
    public void insertClassPrerequisiteSQL(String classCode, String preReqClassCode) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO `prerequisitesLookup` (`classCode`, `preReqClassCode`) VALUES (?, ?)");
            preparedStatement.setString(1, classCode);
            preparedStatement.setString(2, preReqClassCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nINSERT CLASS PREREQUISITE FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Changes a class prereq
     * @param newPreReqClassCode the new prereq
     * @param classCode the class that is getting updated
     * @param oldPreReqClassCode the old prereq to be changed
     */
    public void updateClassPrerequisiteSQL(String newPreReqClassCode, String classCode, String oldPreReqClassCode) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE `prerequisitesLookup` SET `preReqClassCode` = ? WHERE `classCode` = ? AND `preReqClassCode` = ?");
            preparedStatement.setString(1, newPreReqClassCode);
            preparedStatement.setString(2, classCode);
            preparedStatement.setString(3, oldPreReqClassCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nUPDATE CLASS PREREQUISITE FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Deletes a prereq from a class
     * @param classCode the class to be updated
     * @param prereqClassCode the prereq to be deleted
     */
    public void deletePrerequisiteSQL(String classCode, String prereqClassCode) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM `prerequisitesLookup` WHERE `classCode` = ? AND `preReqClassCode` = ?");
            preparedStatement.setString(1, classCode);
            preparedStatement.setString(2, prereqClassCode);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nDELETE PREREQ FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Creates a discussion group
     @param    groupName    The name of the discussion group
     @param    email        The creator's email
     */
    public void addDiscussionGroupSQL(String groupName, String email) {
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO discussionGroups(groupName, userEmail) VALUES (?,?)");
            stmt.setString(1, groupName);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } // end of try
        catch (Exception e) {
            System.out.println("Error while trying to add discussion group.");
            System.out.println("ERROR MESSAGE --> " + e);
        } // end of catch
    }// end of function

    /**
     * Adds members to a discussion group
     * @param id the group to add members to
     * @param email the user to be added
     */
    public void addDiscussionGroupMembersSQL(String id, String email) {
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO discussionGroupsMembers(discussionID, userEmail) VALUES (?,?)");
            stmt.setString(1, id);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } // end of try
        catch (Exception e) {
            System.out.println("Error while trying to add discussion group member.");
            System.out.println("ERROR MESSAGE --> " + e);
        } // end of catch
    }

    /**
     * Removes a member from a discussion group
     * @param id the group to delete the member from
     * @param email the user to be removed
     */
    public void deleteDiscussionGroupMembersSQL(String id, String email) {
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("DELETE FROM discussionGroupsMembers WHERE discussionID=? AND userEmail=?");
            stmt.setString(1, id);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } // end of try
        catch (Exception e) {
            System.out.println("Error while trying to add discussion group member.");
            System.out.println("ERROR MESSAGE --> " + e);
        } // end of catch
    }

    /**
     * Gets the id of a discussion group
     * @param groupName the name of the group
     * @return the id of the discussion group
     */
    public String getDiscussionIdSQL(String groupName) {
        String id = "";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT discussionID FROM discussionGroups WHERE groupName = ?");
            preparedStatement.setString(1, groupName);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getString(1);
            }
        } catch (SQLException sqle) {
            System.out.println("Error while trying to get a discussion group id.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        } // end of catch
        return id;
    }

    /**
     * Deletes a discussion group
     @param    id    The name of the group to be deleted
     */
    public void deleteDiscussionGroupSQL(String id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM discussionGroups WHERE discussionID=?");
            stmt.setString(1, id);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("Error while trying to delete a discussion group.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end of catch
    }//end of function

    /**
     * Gets all discussion groups a user is in
     * @param email the user 
     * @return a list of discussion groups
     */
    public List<DiscussionGroup> selectDiscussionGroupsSQL(String email) {
        String discussionIDReturned = "";
        String groupNameReturned = "";
        String userEmailReturned = "";
        List<DiscussionGroup> discussionGroupsList = new ArrayList<DiscussionGroup>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT discussionID, groupName, discussionGroups.userEmail FROM discussionGroups JOIN discussionGroupsMembers USING(discussionID) WHERE discussionGroupsMembers.userEmail=?");
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                discussionIDReturned = rs.getString(1);
                groupNameReturned = rs.getString(2);
                userEmailReturned = rs.getString(3);
                System.out.println(discussionIDReturned + " " + groupNameReturned + " " + userEmailReturned);
                DiscussionGroup dg = new DiscussionGroup(discussionIDReturned, groupNameReturned, userEmailReturned);
                discussionGroupsList.add(dg);
            }
            return discussionGroupsList;

        } catch (SQLException sqle) {
            System.out.println("\n\nSELECT FROM DISCUSSION GROUPS FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the discussion group messages
     * @param discussionID the discussiom group
     */
    public void selectDiscussionMessagesSQL(int discussionID) {
        int discussionIDReturned;
        String messagesReturned;
        String userEmailReturned;
        String datePostedReturned;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT discussionID, messages, userEmail, datePosted FROM discussionMessages WHERE discussionID = ?");
            preparedStatement.setInt(1, discussionID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                discussionIDReturned = rs.getInt(1);
                messagesReturned = rs.getString(2);
                userEmailReturned = rs.getString(3);
                datePostedReturned = rs.getString(4);
                System.out.println(discussionIDReturned + " " + messagesReturned + " " + userEmailReturned + " "
                        + datePostedReturned);
            }

        } catch (SQLException sqle) {
            System.out.println("\n\nSELECT FROM DISCUSSION MESSAGES FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * gets the class ratings 
     * @return a list of all of the feedback
     */
    public List<Feedback> selectRatingClassesSQL() {
        String userEmailReturned;
        String classCodeReturned;
        String feedbackReturned;
        List<Feedback> feedback = new ArrayList<Feedback>();
        try {
            sql = "SELECT * FROM classRating";
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                userEmailReturned = rs.getString(1);
                classCodeReturned = rs.getString(2);
                feedbackReturned = rs.getString(3);
                System.out.println(userEmailReturned + " " + classCodeReturned + " " + feedbackReturned);
                Feedback temp = new Feedback(userEmailReturned, classCodeReturned, feedbackReturned);
                feedback.add(temp);
            }

        } catch (SQLException sqle) {
            System.out.println("\n\nSELECT ADMIN CLASSES FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return feedback;
    }

    /**
     * Gets all of the classes that an admin creator
     * @param creatorEmail the admin
     * @return a list of all of the classes 
     */
    public List<Course> selectAdminClassesSQL(String creatorEmail) {
        String classCodeReturned;
        String creatorEmailReturned;
        String professorEmailReturned;
        String classNameReturned;
        String learningObjReturned;
        String learningOutcomeReturned;
        String beginDateReturned;
        String endDateReturned;
        List<Course> courses = new ArrayList<Course>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT classCode, creatorEmail, professorEmail, className, learningObj, learningOutcome, beginDate, endDate FROM class WHERE creatorEmail = ?");
            preparedStatement.setString(1, creatorEmail);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                classCodeReturned = rs.getString(1);
                creatorEmailReturned = rs.getString(2);
                professorEmailReturned = rs.getString(3);
                classNameReturned = rs.getString(4);
                learningObjReturned = rs.getString(5);
                learningOutcomeReturned = rs.getString(6);
                beginDateReturned = rs.getString(7);
                endDateReturned = rs.getString(8);

                System.out.println(classCodeReturned + " " + creatorEmailReturned + " " + professorEmailReturned + " "
                        + classNameReturned + " " + learningObjReturned + " " + learningOutcomeReturned + " "
                        + beginDateReturned + " " + endDateReturned);
                Course courseTemp = new Course(classCodeReturned, creatorEmailReturned, professorEmailReturned,
                        classNameReturned, learningObjReturned, learningOutcomeReturned, beginDateReturned,
                        endDateReturned);
                courses.add(courseTemp);
            }
            return courses;

        } catch (SQLException sqle) {
            System.out.println("\n\nSELECT ADMIN CLASSES FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return courses;
    }

    /**
     * Gets all of the classes that an admin creator
     * @param professorEmail the admin
     * @return a list of all of the classes
     */
    public List<Course> selectProfessorClassesSQL(String professorEmail) {
        String classCodeReturned;
        String creatorEmailReturned;
        String professorEmailReturned;
        String classNameReturned;
        String learningObjReturned;
        String learningOutcomeReturned;
        String beginDateReturned;
        String endDateReturned;
        List<Course> courses = new ArrayList<Course>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT classCode, creatorEmail, professorEmail, className, learningObj, learningOutcome, beginDate, endDate FROM class WHERE professorEmail = ?");
            preparedStatement.setString(1, professorEmail);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                classCodeReturned = rs.getString(1);
                creatorEmailReturned = rs.getString(2);
                professorEmailReturned = rs.getString(3);
                classNameReturned = rs.getString(4);
                learningObjReturned = rs.getString(5);
                learningOutcomeReturned = rs.getString(6);
                beginDateReturned = rs.getString(7);
                endDateReturned = rs.getString(8);

                System.out.println(classCodeReturned + " " + creatorEmailReturned + " " + professorEmailReturned + " "
                        + classNameReturned + " " + learningObjReturned + " " + learningOutcomeReturned + " "
                        + beginDateReturned + " " + endDateReturned);
                Course courseTemp = new Course(classCodeReturned, creatorEmailReturned, professorEmailReturned,
                        classNameReturned, learningObjReturned, learningOutcomeReturned, beginDateReturned,
                        endDateReturned);
                courses.add(courseTemp);
            }
            return courses;

        } catch (SQLException sqle) {
            System.out.println("\n\nSELECT PROFESSOR CLASSES FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return courses;
    }

    /**
     * Updates the user verification
     * @param email the user to be verified
     * @param verifyCode the verification code
     */
    public void updateUserVerificationSQL(String email, String verifyCode) {
        int rows = 0;
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("UPDATE `user` SET `verificationCode` = ? WHERE `userEmail` = ?");
            preparedStatement.setString(1, verifyCode);
            preparedStatement.setString(2, email);

            rows = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rows + "\n");

        } catch (SQLException sqle) {
            System.out.println("\n\nUPDATE USER VERIFICATION FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
    }

    /**
     * Gets the verification code to check it is corret
     * @param email the user being verified
     * @return the verification code
     */
    public String selectVerifyCodeSQL(String email) {
        String verifyReturned;
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("SELECT verificationCode FROM user WHERE userEmail = ?");
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                verifyReturned = rs.getString(1);
                System.out.println(verifyReturned);
                return verifyReturned;
            }
        } catch (SQLException sqle) {
            System.out.println("\n\nSELECT FROM USER FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return "";
    }

    /**
     * Gets all of the classes that have ratings
     * @return a list of all of the class codes that have ratings
     */
    public List<String> selectRatedClassesSQL() {
        List<String> classCodes = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT DISTINCT classCode FROM classRating");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String classCode = rs.getString(1);
                System.out.println(classCode);
                classCodes.add(classCode);
            }

        } catch (SQLException sqle) {
            System.out.println("\n\nSELECT FROM USER FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return classCodes;
    }

    /**
     * Gets the average class rating
     * @param classCode the class
     * @return the average rating
     */
    public int selectClassAvgSQL(String classCode) {
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("SELECT AVG(rating) FROM classRating WHERE classCode = ?");
            preparedStatement.setString(1, classCode);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int avg = rs.getInt(1);
                System.out.println(avg);
                return avg;
            }
        } catch (SQLException sqle) {
            System.out.println("\n\nSELECT FROM USER FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return 0;
    }

    /**
     * Find a specific discussion group
     * @param name the name of discussion group to be found
     * @return a list of the search results
     */
    public List<DiscussionGroup> searchDiscussionGroup(String name) {
        List<DiscussionGroup> results = new ArrayList<DiscussionGroup>();
        DiscussionGroup result = new DiscussionGroup("", "", "");
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("SELECT * FROM discussionGroups WHERE groupName = ?");
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.setDiscussionID(rs.getString(1));
                result.setGroupName(rs.getString(2));
                result.setUserEmail(rs.getString(3));
                results.add(result);
            }
        } catch (SQLException sqle) {
            System.out.println("\n\nSELECT FROM USER FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return results;
    }

    /**
     * Deletes a lesson
     * @param lessonID the lesson to be deleted
     */
    public void deleteLessonSQL(String lessonID) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM lesson WHERE lessonID=?");
            stmt.setString(1, lessonID);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("Error while trying to delete a lesson.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end of catch
    } 

    public void insertQuizSQL(String name, String timeLimit, String creatorEmail, String classCode){
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO quiz(name, timeLimit, creatorEmail, classCode) VALUES (?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, timeLimit);
            stmt.setString(3, creatorEmail);
            stmt.setString(4, classCode);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("ERROR IN >>insertQuizSQL<< !!!!!");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end catch
    }

    public void insertQuizQuestionSQL(String quizID, String questionNum, String questionContent, String optionA, String optionB, String optionC, String optionD, String questionAnswer){
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO quizquestions(quizID, questionNum, questionContent, optionA, optionB, optionC, optionD, questionAnswer) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, quizID);
            stmt.setString(2, questionNum);
            stmt.setString(3, questionContent);
            stmt.setString(4, optionA);
            stmt.setString(5, optionB);
            stmt.setString(6, optionC);
            stmt.setString(7, optionD);
            stmt.setString(8, questionAnswer);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("ERROR IN >>insertQuizQuestionSQL<< !!!!!");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end catch
    }

    public void deleteQuizSQL(String quizID, String creatorEmail){
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM quiz WHERE quizID=? AND creatorEmail=?");
            stmt.setString(1, quizID);
            stmt.setString(2, creatorEmail);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("ERROR IN >>deleteQuiz<< !!!!!!");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end of catch
    }

    public void deleteQuizQuestionSQL(String quizID, String questionNum){
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM quizquestions WHERE quizID=? AND questionNum=?");
            stmt.setString(1, quizID);
            stmt.setString(2, questionNum);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("ERROR IN >>deleteQuizQuestionSQL<< !!!!!!");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end of catch
    }

    /**
     * Method that gets quiz questions 
     * @param quizID the id of the quiz to return
     * @return the quiz
     */
    public Quiz getQuizQuestionsSQL(String quizID,String quizName,String timeLimit, String classCode) {
        Quiz q = new Quiz(quizID,quizName,timeLimit,classCode);
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT questionContent, questionAnswer FROM quizQuestions WHERE quizID = ?");
            stmt.setString(1, quizID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                q.addQuizQuestion(rs.getString(1), rs.getString(2));
            }//end while
        }//end of try
        catch (SQLException sqle) {
            System.out.println("Error while trying to get quiz questions.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end of catch
        return q;
    }

    
    public List<Quiz> getUserQuizzes(String email) {
        List<Quiz> quizzes = new ArrayList<Quiz>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT quizID, name, timeLimit, classCode FROM quiz WHERE creatorEmail = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //not sure what type of join too tired to figure it out rn
                //think its left?? not sold on that tho 
                Quiz q = new Quiz(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                stmt = conn.prepareStatement("SELECT quizQuestions.questionContent, quizQuestions.questionAnswer, quizUserScore.score FROM quizQuestions LEFT JOIN quizUserScore USING(quizID) WHERE quizID = ?");
                stmt.setString(1, rs.getString(1));
                ResultSet rs2 = stmt.executeQuery();
                while (rs2.next()) {
                    q.addQuizQuestion(rs2.getString(1),rs2.getString(2));
                    q.setQuizScore(rs2.getString(3));
                }
                quizzes.add(q);
            }
        }
        catch (SQLException sqle) {
            System.out.println("Error while trying to get user quizzes.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }
        return quizzes;
    }

    //this method is a work in progress
    public List<Quiz> getStudentQuizzes(String email) {
        List<Quiz> quizzes = new ArrayList<Quiz>();
        try {
            Quiz q = new Quiz();;
            PreparedStatement stmt = conn.prepareStatement("SELECT quizID FROM studentAnswers WHERE studentEmail = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //not sure what type of join too tired to figure it out rn
                //think its left?? not sold on that tho 
                stmt = conn.prepareStatement("SELECT quizID, name, timeLimit, classCode FROM quiz WHERE quizID = ?");
                stmt.setString(1, rs.getString(1));
                ResultSet rs2 = stmt.executeQuery();
                while (rs2.next()) {
                    q.setQuizId(rs2.getString(1));
                    q.setQuizName(rs2.getString(2));
                    q.setTimeLimit(rs2.getString(3));
                    q.setQuizClass(rs2.getString(4));
                    stmt = conn.prepareStatement("SELECT quizQuestions.questionContent, quizQuestions.questionAnswer, quizUserScore.score FROM quizQuestions LEFT JOIN quizUserScore USING(quizID) WHERE quizQuestions.quizID = ? AND quizUserScore.userEmail = ?");
                    stmt.setString(1, rs2.getString(1));
                    stmt.setString(2, email);
                    ResultSet rs3 = stmt.executeQuery();
                    while (rs3.next()){
                        q.addQuizQuestion(rs3.getString(1),rs3.getString(2));
                        q.setQuizScore(rs3.getString(3)); 
                    } 
                    
                } 
                
            }
            quizzes.add(q); 
        }
        catch (SQLException sqle) {
            System.out.println("Error while trying to get student quizzes.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }
        return quizzes;
    }
    
    /**
     * Inserts the start time for a lesson
     * @param lessonID the lesson 
     * @param startTime the start time
     */ 
    public void insertLessonStartTimeSQL(String lessonID, String startTime) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO lesson(lessonID, startTime) VALUES (?,?)");
            stmt.setString(1, lessonID);
            stmt.setString(2, startTime);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("Error while trying to insert lesson start time.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end catch
    }

    /**
     * Inserts a lesson end time
     * @param lessonID the lesson
     * @param endTime the end time
     */ 
    public void insertLessonEndTimeSQL(String lessonID, String endTime) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO lesson(lessonID, endTime) VALUES (?,?)");
            stmt.setString(1, lessonID);
            stmt.setString(2, endTime);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("Error while trying to insert lesson end time.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end catch
    }

    /**
     * Updates the lesson start time
     * @param lessonID the lesson to be updated
     * @param startTime the new start time
     */
    public void updateLessonStartTimeSQL(String lessonID, String startTime) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE `lesson` SET `startTime` = ? WHERE `lessonID` = ?");
            stmt.setString(1, startTime);
            stmt.setString(2, lessonID);
            stmt.executeUpdate();
        }//end try
        catch (SQLException sqle) {
            System.out.println("Error while trying to update lesson start time.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end catch
    }

    /**
     * Updates the lesson end time
     * @param lessonID the lesson to be updated
     * @param endTime the new end time
     */
    public void updateLessonEndTimeSQL(String lessonID, String endTime) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE `lesson` SET `endTime` = ? WHERE `lessonID` = ?");
            stmt.setString(1, endTime);
            stmt.setString(2, lessonID);
            stmt.executeUpdate();
        }//end try
        catch (SQLException sqle) {
            System.out.println("Error while trying to update lesson end time.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end catch
    }

    /**
     * Deletes the lesson start time
     * @param lessonID the lesson 
     */
    public void deleteLessonStartTimeSQL(String lessonID) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE lesson SET startTime=NULL WHERE lessonID = ?");
            stmt.setString(1, lessonID);
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Error while trying to delete lesson start time.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }
    }

    /**
     * Deletes the lesson end time
     * @param lessonID the lesson 
     */
    public void deleteLessonEndTimeSQL(String lessonID) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE lesson SET endTime=NULL WHERE lessonID = ?");
            stmt.setString(1, lessonID);
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println("Error while trying to delete lesson end time.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }
    }

    /**
     * Insert quiz score
     * @param quizID the quiz
     * @param userEmail the user who took the quiz
     * @param score the score to be inserted
     */
    public void insertQuizScoreSQL (String quizID, String userEmail, String score) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO quizUserScore(quizID,userEmail,score) VALUES(?,?,?)");
            stmt.setString(1, quizID);
            stmt.setString(2, userEmail);
            stmt.setString(3, score);
            stmt.executeUpdate();
        }
        catch (SQLException sqle) {
            System.out.println("Error while trying to insert quiz grade.");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }
    }

    /**
     * Inserts a new class requirement (in the form of a lesson)
     * @param classCode     The class' unique code
     * @param lessonID      The lesson's unique code
     */
    public void insertLessonLookupSQL(String classCode, String lessonID){
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO lessonLookup(classCode, lessonID) VALUES (?,?)");
            stmt.setString(1, classCode);
            stmt.setString(2, lessonID);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("\n\nERROR insertLessonLookupSQL FAILED!!!");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end catch
    }

    /**
     * Deletes a class requirement (in the form of a lesson)
     * @param classCode     The class' unique code
     * @param lessonID      The lesson's unique ID
     */
    public void deleteLessonLookupSQL(String classCode, String lessonID){
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM lessonLookup WHERE classCode=? AND lessonID=?");
            stmt.setString(1, classCode);
            stmt.setString(2, lessonID);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("\n\nERROR deleteLessonLookupSQL FAILED!!");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end of catch
    }

    /**
     * Inserts a new lesson requirement (in the form of a lecture w/ media)
     * @param multimedia    Multimedia (link, text, etc.)
     * @param lessonID      A lesson's unique ID
     */
    public void insertLectureSQL(String multimedia, String lessonID, String name){
        try {
            System.out.println(multimedia);
            System.out.println(lessonID);
            System.out.println(name);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO lectures(lectureName, multimedia, lessonID) VALUES (?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, multimedia);
            stmt.setString(3, lessonID);
            System.out.println(stmt);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("\n\nERROR insertLectureSQL FAILED!!!");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end catch
    }

    /**
     * Deletes a lecture using it's unique ID
     * @param lectureID     A lecture's unique ID
     */
    public void deleteLectureSQL(String lectureID){
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM lectures WHERE lectureID=?");
            stmt.setString(1, lectureID);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("\n\nERROR deleteLectureSQL FAILED!!");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end of catch
    }

    /**
     * Inserts a new lesson
     * @param classCode     A class' unique code
     * @param lessonName    A lesson's name
     * @param startTime     A start time (ISO time)
     * @param endTime       An end time (ISO time)
     */
    public void insertLessonSQL(String classCode, String lessonName, String startTime, String endTime){
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO lesson(classCode, lessonName, startTime, endTime) VALUES (?, ?, ?, ?)");
            stmt.setString(1, classCode);
            stmt.setString(2, lessonName);
            stmt.setString(3, startTime);
            stmt.setString(4, endTime);
            stmt.executeUpdate();
        }//end of try
        catch (SQLException sqle) {
            System.out.println("\n\nERROR insertLessonSQL FAILED!!!");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end catch
    }

    /**
     * Updates a lecture's multimedia
     * @param lectureID     A lecture's unique ID
     * @param multimedia    Multimedia (link, text, etc.)
     */
    public void updateMultimediaSQL(String lectureID, String multimedia){
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE lectures SET multimedia=? WHERE lectureID=?");
            stmt.setString(1, multimedia);
            stmt.setString(2, lectureID);
            stmt.executeUpdate();
        }//end try
        catch (SQLException sqle) {
            System.out.println("\n\nERROR updateMultimediaSQL FAILED!!");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end catch
    }


    public List<QuizQuestion> selectQuizQuestionsSQL(String quizID){
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT quizID, questionNum, questionContent, optionA, optionB, optionC, optionD, questionAnswer FROM quizQuestions WHERE quizID=? ORDER BY questionNum");
            stmt.setString(1, quizID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String outQuizID = rs.getString(1);
                String questionNum = rs.getString(2);
                String questionContent = rs.getString(3);
                String optionA = rs.getString(4);
                String optionB = rs.getString(5);
                String optionC = rs.getString(6);
                String optionD = rs.getString(7);
                String questionAnswer = rs.getString(8);

                QuizQuestion qq = new QuizQuestion(outQuizID, questionNum, questionContent, optionA, optionB, optionC, optionD, questionAnswer);
                quizQuestions.add(qq);
            }

        } catch (SQLException sqle) {
            System.out.println("\n\nERROR IN >>selectQuizQuestionsSQL<< !!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return quizQuestions;
    }

    /**
     * Updates a lecture's name
     * @param lectureID     A lecture's unique ID
     * @param name          A lecture's name
     */
    public void updateLectureNameSQL(String lectureID, String name){
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE lectures SET lectureName=? WHERE lectureID=?");
            stmt.setString(1, name);
            stmt.setString(2, lectureID);
            stmt.executeUpdate();
        }//end try
        catch (SQLException sqle) {
            System.out.println("\n\nERROR updateLectureNameSQL FAILED!!");
            System.out.println("ERROR MESSAGE --> " + sqle);
        }//end catch
    }

    /**
     * Gets all discussion groups a user is in
     * @param classCode the class code
     * @return a list of lessons
     */
    public List<Lesson> selectLessonsSQL(String classCode) {
        String lessonIDReturned = "";
        String lessonNameReturned = "";
        String startDateReturned = "";
        String endDateReturned = "";

        List<Lesson> lessonList = new ArrayList<Lesson>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT lessonID, lessonName, startTime, endTime FROM lesson WHERE classCode=?");
            preparedStatement.setString(1, classCode);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                lessonIDReturned = rs.getString(1);
                lessonNameReturned = rs.getString(2);
                startDateReturned = rs.getString(3);
                endDateReturned = rs.getString(4);
                System.out.println(lessonIDReturned + " " + lessonNameReturned + " " + startDateReturned + " " + endDateReturned);
                Lesson l = new Lesson(lessonIDReturned, classCode, lessonNameReturned, startDateReturned, endDateReturned);
                lessonList.add(l);
            }
            return lessonList;

        } catch (SQLException sqle) {
            System.out.println("\n\nSELECT FROM LESSONS FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return null;
    }

    /**
     * Gets all discussion groups a user is in
     * @param lessonID the lesson ID
     * @return a list of lectures
     */
    public List<Lecture> selectLecturesSQL(String lessonID) {
        String multimediaReturned = "";
        String lectureID = "";
        String lectureName = "";

        List<Lecture> lectureList = new ArrayList<Lecture>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT lectureID, lectureName, multimedia FROM lectures WHERE lessonID=?");
            preparedStatement.setString(1, lessonID);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                lectureID = rs.getString(1);
                lectureName = rs.getString(2);
                multimediaReturned = rs.getString(3);
                System.out.println(lessonID + " " + lectureID + " " + lectureName + " " + multimediaReturned);
                Lecture l = new Lecture(lessonID, multimediaReturned, lectureID, lectureName);
                lectureList.add(l);
            }
            return lectureList;

        } catch (SQLException sqle) {
            System.out.println("\n\nSELECT FROM LECTURE FAILED!!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return null;
    }

    public void automaticallyGrade(String studentEmail, String quizID){
        // get the number of questions answered correctly
        int total = selectTotalNumQuestions(studentEmail, quizID);
        // get the number of questions total
        int correct = selectNumQuestionsCorrect(studentEmail, quizID);
        // divide the values, multiply by 100
        int grade = 0;

        try {
            grade = (correct / total) * 100;
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }finally {
            // insert grade into quizuserscore
            insertQuizScoreSQL(quizID, studentEmail, Integer.toString(grade));
        }
    }

    public int selectTotalNumQuestions(String studentEmail, String quizID){
        int totalNumQuestions = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT count(*) FROM quizquestions JOIN studentanswers USING(quizID, questionNum) WHERE studentEmail = ? AND quizID = ?");
            stmt.setString(1, studentEmail);
            stmt.setString(2, quizID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                totalNumQuestions = rs.getInt(1);
            }

        } catch (SQLException sqle) {
            System.out.println("\n\nERROR IN >>selectTotalNumQuestions<< !!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return totalNumQuestions;
    }

    public int selectNumQuestionsCorrect(String studentEmail, String quizID){
        int numQuestionsCorrect = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT count(*) FROM quizquestions JOIN studentanswers USING(quizID, questionNum) WHERE studentEmail = ? AND quizID = ? AND questionAnswer = studentAnswer");
            stmt.setString(1, studentEmail);
            stmt.setString(2, quizID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                numQuestionsCorrect = rs.getInt(1);
            }

        } catch (SQLException sqle) {
            System.out.println("\n\nERROR IN >>selectNumQuestionsCorrect<< !!!!");
            System.out.println("ERROR MESSAGE IS -> " + sqle);
            sqle.printStackTrace();
        }
        return numQuestionsCorrect;
    }


}
