package n17dtha4.notemanagesystem.model;

public class Account_Model {
    private int Id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String PassWord;

    public Account_Model(){}

    public Account_Model(int id, String firstName, String lastName, String email, String passWord) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        PassWord = passWord;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
