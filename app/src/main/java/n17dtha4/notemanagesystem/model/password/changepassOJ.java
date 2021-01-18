package n17dtha4.notemanagesystem.model.password;

public class changepassOJ {
    private int id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;

    public changepassOJ(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Password = password;
    }


    public changepassOJ() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "ProfileOJ{" +
                "id=" + id +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
