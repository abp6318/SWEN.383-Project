public class User {

    private final String fname;
    private final String lname;
    private final String email;
    private final String role;

    public User(String fname, String lname, String email, String role){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.role = role;
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
}
