import java.util.ArrayList;

/**
 * Bus
 */
public class Bus {
    SkipList<Passenger> passengers;
    PriorityQueue<Suitcase> suitcases;
    Driver driver;
    Conductor conductor;
    ArrayList<Integer> endPoint;
    String departureTime;
    String arrivalTime;
    String name;
    boolean isAvailable = true;
    int seatNumber = 40;
    ArrayList<Integer> seats;

    public Bus() {
        suitcases = new PriorityQueue<Suitcase>();
        passengers = new SkipList<Passenger>();
        driver = new Driver();
        conductor = new Conductor();
        endPoint = new ArrayList<>();
    }

    public Bus(Driver driver, Conductor conductor, ArrayList<Integer> endPoint, String departureTime, String arrivalTime, String name) {
        suitcases = new PriorityQueue<Suitcase>();
        passengers = new SkipList<Passenger>();
        seats = new ArrayList<Integer>();
        this.name = name;
        this.driver = driver;
        this.conductor = conductor;
        this.endPoint = endPoint;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public void setNotAvailable(){this.isAvailable=false;}
    public void setArrivalTime(String arrivalTime){this.arrivalTime=arrivalTime;}
    public void setDepartureTime(String departureTime){this.departureTime=departureTime;}

    /**
     * Add the passenger to the passengers list
     * 
     * @param passenger The passenger to be added
     * @return if the passenger already exists in passengers list then return false;
     *         otherwise, return true
     */
    public boolean addPassenger(Passenger passenger) {
        if (!passengers.contains(passenger)) {
            passengers.add(passenger);
            suitcases.insert(passenger.getSuitcase());
            return true;
        }
        return false;
    }

    /**
     * Print the passenger list.
     */
    public void printPassengersList() {
        for (Passenger passenger : passengers) {
            System.out.println(passenger.toString());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" / ");
        sb.append(departureTime);
        sb.append("-");
        sb.append(arrivalTime);
        sb.append(" / ");
        sb.append(endPoint);


        return sb.toString();
    }

}