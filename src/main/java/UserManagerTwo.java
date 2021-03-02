import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.logging.Logger;

public class UserManagerTwo {

    private static final Logger LOGGER = Logger.getLogger(GetLearnerRoute.class.getName());

    private final String DATAPATH = "src/main/resources/data/users.csv";

    // register
    public void userRegister(String email, String password, String fname, String lname){
        try{

            File users = new File(DATAPATH);

            if(!users.exists()){
                users.createNewFile();
                LOGGER.info("PLEASE");
            }

            FileWriter write = new FileWriter(users);
            BufferedWriter buff = new BufferedWriter(write);

            if(lname.toLowerCase().equals("admin") && fname.toLowerCase().equals("admin")){
                buff.write(email.toLowerCase() + "," + encryptPassword(password) + "," + fname + "," + lname + ",admin,0");
            }
            else if(fname.toLowerCase().contains("professor")){
                buff.write(email.toLowerCase() + "," + encryptPassword(password) + "," + fname + "," + lname + ",professor,0");
            }
            else{
                buff.write(email.toLowerCase() + "," + encryptPassword(password) + "," + fname + "," + lname + ",learner,0");
            }
            buff.close();
            write.close();

        } catch (IOException e){
            LOGGER.warning(e.getMessage());
        }

    }

    //signin
    public boolean validUser(String email, String password){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(DATAPATH));
            String line = reader.readLine();
            while(line != null){
                String[] dataLine = line.split(",");
                if(dataLine[0].equals(email.toLowerCase()) && dataLine[1].equals(encryptPassword(password))){
                    return true;
                }
            }
            reader.close();

        } catch (FileNotFoundException e){
            LOGGER.warning(e.getMessage());
        } catch (IOException ex){
            LOGGER.warning(ex.getMessage());
        }

        return false;
    }

    //signin
    public String[] getRole(String email){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(DATAPATH));
            String line = reader.readLine();
            String[] userInfo = new String[4];
            while(line != null){
                String[] dataLine = line.split(",");
                if(dataLine[0].toLowerCase().equals(email.toLowerCase())){
                    userInfo[0] = dataLine[2];
                    userInfo[1] = dataLine[3];
                    userInfo[2] = dataLine[4];
                    userInfo[3] = dataLine[5];
                    return userInfo;
                }
            }
            reader.close();

        } catch (FileNotFoundException e){
            LOGGER.warning(e.getMessage());
        } catch (IOException ex){
            LOGGER.warning(ex.getMessage());
        }

        return null;
    }


    /**
     Encrypts a password using SHA1
     @param   secret   The user's password
     @return           The user's passsword now encrypted
     */
    public String encryptPassword(String secret){

        String sha1 = "";
        String value = secret;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(value.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        }catch(Exception e){
            e.printStackTrace();
        }// end of catch


        return sha1;
    }//end of function
}
