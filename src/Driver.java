/**
 * Driver
 */
public class Driver implements Comparable<Driver> {

    private int driverId;
    private String name;
    private String surname;
    private int age;
    private long phoneNumber;
    private boolean isAvailable = true;

    public Driver()
    {
    }

    public Driver(int driverId)
    {
        this.driverId = driverId;
    }

    public Driver(int driverId, String name, String surname, int age, long phoneNumber)
    {
        this.driverId = driverId;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public boolean isAvailable(){return isAvailable;}

    public int getAge() {
        return age;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setNotAvailable(){this.isAvailable=false;}

    public void setAge(int age) {
        this.age = age;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Driver o) {
        return this.driverId - o.getDriverId();
    }

    /**
     * String display of driver information. 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(name);
        sb.append("\nSurname: ");
        sb.append(surname);
        sb.append("\nDriver ID: ");
        sb.append(driverId);
        sb.append("\nAge: ");
        sb.append(age);
        sb.append("\nPhone Number: ");
        sb.append(phoneNumber);

        return sb.toString();
    }

}
