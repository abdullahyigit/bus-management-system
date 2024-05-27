import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String surname;
    private String age;
    private String phoneNumber;
    private String password;
    private String userName;
    private UserType userType;

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public User(String name, String surname, String age, String phoneNumber, String password, String userName,
            UserType userType) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userName = userName;
        this.userType = userType;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(userName + " " + password + " " + name + " " + surname + " " + age + " " + phoneNumber + " "
                + userType.toString());
        return sb.toString();
    }
}
