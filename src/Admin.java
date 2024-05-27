import java.io.Serializable;

public class Admin implements Serializable {
    private String name;
    private String surname;
    private String age;
    private String phoneNumber;
    private String password;
    private String userName;
    private boolean subAdmin;

    public Admin(String name, String surname, String age, String phoneNumber, String password, String userName,
                 boolean subAdmin) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userName = userName;
        this.subAdmin = subAdmin;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isSubAdmin() {
        return this.subAdmin;
    }

    public boolean getSubAdmin() {
        return this.subAdmin;
    }

    public void setSubAdmin(boolean subAdmin) {
        this.subAdmin = subAdmin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(userName + " " + password + " " + name + " " + surname + " " + age + " " + phoneNumber + " "
                + isSubAdmin());
        return sb.toString();
    }
}