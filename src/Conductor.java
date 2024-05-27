public class Conductor implements Comparable<Conductor> {
    private int conductorId;
    private String name;
    private String surname;
    private int age;

    private long phoneNumber;

    private boolean isAvailable = true;

    public Conductor() {

    }

    public Conductor(int ID) {
        this.conductorId = ID;
    }

    public Conductor(int conductorId, String name, String surname, int age, long phoneNumber) {
        this.conductorId = conductorId;
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

    public int getConductorId() {
        return conductorId;
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

    public void setConductorId(int conductorId) {
        this.conductorId = conductorId;
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
    /**
     * String display of conductor information. 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Conductor: \nName: ");
        sb.append(name);
        sb.append("\nSurname: ");
        sb.append(surname);
        sb.append("\nConductor ID: ");
        sb.append(conductorId);
        sb.append("\nAge: ");
        sb.append(age);
        sb.append("\nPhone Number: ");
        sb.append(phoneNumber);

        return sb.toString();
    }

    @Override
    public int compareTo(Conductor o) {
        return 0;
    }
}
