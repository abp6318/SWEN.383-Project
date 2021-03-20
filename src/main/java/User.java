public class User {

    private final String fname;
    private final String lname;
    private final String email;
    private final String role;
    private final String verified;
    private final String passwordEncrypted;

    public User(String email, String fname, String lname, String role, String verified, String passwordEncrypted){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.role = role;
        this.verified = verified;
        this.passwordEncrypted = passwordEncrypted;
    }

    public String getFirstName() {
        return fname;
    }

    public String getLastName() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getVerified() {
        return verified;
    }

    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    @Override
    public String toString(){
        return "First name: " + fname + ", Last name: " + lname + ", Email: " + email + ", Role: " + role + ", Verified: " + verified;
    }

}