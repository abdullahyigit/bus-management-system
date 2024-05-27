import javax.print.attribute.standard.Destination;

/**
 * Passenger
 */
public class Passenger implements Comparable<Passenger>{

    /** The ID number for this passenger. */
    private int passengerId;

    /** The age for this passenger. */
    private int age;

    /** The phone number for this passenger. */
    private long phoneNumber;

    /** The name for this passenger. */
    private String name;

    /** The surname for this passenger. */
    private String surname;

    /** The suitcase for this passenger. */
    private Suitcase suitcase;

    private int destination;

    private int seat = 0;

    public Passenger()
    {

    }

    public Passenger(int passengerId, int age, long phoneNumber, 
                    String name, String surname, Suitcase suitcase, int destination)
    {
        this.passengerId = passengerId;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
        this.suitcase = suitcase;
        this.destination = destination;
    }

    /**
     * Get the passenger ID.
     * 
     * @return The passenger ID
     */
    public int getPassengerId() {
        return passengerId;
    }

    public int getSeat() {
        return seat;
    }

    /**
     * Get the passenger age.
     * 
     * @return The passenger age
     */
    public int getAge() {
        return age;
    }

    /**
     * Get the passenger phone number.
     * 
     * @return The passenger phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Get the passenger name.
     * 
     * @return The passenger name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the passenger surname.
     * 
     * @return The passenger surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Get the passenger suitcase.
     * 
     * @return The passenger suitcase
     */
    public Suitcase getSuitcase() {
        return suitcase;
    }

    /**
     * Get the passenger destination.
     *
     * @return The passenger destination
     */
    public int getDestination() {
        return destination;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    /**
     * Set the passenger age.
     * 
     * @param age The passenger age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Set the passenger ID.
     * 
     * @param passengerId The passenger ID
     */
    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    /**
     * Set the passenger phone number.
     * 
     * @param phoneNumber The passenger phone number
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Set the passenger name.
     * 
     * @param name The passenger name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the passenger surname.
     * 
     * @param surname The passenger surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Set the passenger suitcase.
     * 
     * @param suitcase The passenger suitcase
     */
    public void setSuitcase(Suitcase suitcase) {
        this.suitcase = suitcase;
    }

    /**
     * Set the passenger destination.
     *
     * @return The passenger destination
     */
    public void setDestination() { this.destination = destination; }


    /**
     * String display of passenger information. 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(name);
        sb.append("\nSurname: ");
        sb.append(surname);
        sb.append("\nPassenger ID: ");
        sb.append(passengerId);
        sb.append("\nPhone Number: ");
        sb.append(phoneNumber);
        sb.append("\nAge: ");
        sb.append(age);

        return sb.toString();
    }

    @Override
    public int compareTo(Passenger o) {
        return this.passengerId - o.getPassengerId();
    }

}
