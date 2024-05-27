import java.util.*;

/**
 * Terminal
 */
public class Terminal {
    Queue<Bus> buses;
    NavigableMap<Integer, Passenger> passengers;
    AVLTree<Driver> drivers;
    ListGraph routeGraph;
    BinarySearchTree<Conductor> conductors;
    ArrayList<Platform> platforms;
    double[][] dist = new double[82][82];


    public Terminal() {
        buses = new LinkedList<Bus>();
        passengers = new TreeMap<Integer, Passenger>();
        drivers = new AVLTree<Driver>();
        routeGraph = new ListGraph(82, true);
        conductors = new BinarySearchTree<Conductor>();
        platforms = new ArrayList<Platform>();
        
        Random rand = new Random();
        for(int i = 0; i < 82; i++)
        {
            for(int j = 0; j < 82; j++)
                dist[i][j] = rand.nextDouble()*1000;
        }
    }

    /**
     * 
     * @return
     */
    public Queue<Bus> getBuses() {
        return buses;
    }

    public BinarySearchTree<Conductor> getConductors() {
        return conductors;
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }
    /**
     * 
     * @return
     */
    public NavigableMap<Integer, Passenger> getPassengers() {
        return passengers;
    }

    /**
     * 
     * @return
     */
    public AVLTree<Driver> getDrivers() {
        return drivers;
    }

    /**
     * 
     * @param p
     * @return
     */
    public boolean addPassenger(Passenger p) {
        if (passengers.get(p.getPassengerId()) == null) {
            passengers.put(p.getPassengerId(), p);
            return true;
        }
        return false;
    }

    /**
     * 
     * @param p
     * @return
     */
    public boolean removePassenger(Passenger p) {
        if (passengers.get(p.getPassengerId()) != null) {
            passengers.remove(p.getPassengerId());
            return true;
        }
        return false;
    }

    /**
     * 
     * @param b
     * @return
     */
    public boolean addBus(Bus b) {
        if (!buses.contains(b)) {
            buses.add(b);
            Iterator<Integer> i = b.endPoint.iterator();
            int prev, next;
            if(i.hasNext())
            {
                prev = i.next();
                while(i.hasNext())
                {
                    next = i.next();
                    if(!(routeGraph.isEdge(prev, next)))
                    {
                        Edge edge = new Edge(prev, next, dist[prev][next]);
                        routeGraph.insert(edge);
                    }
                    prev = next;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 
     * @return
     */
    public boolean removeBus() {
        if (!buses.isEmpty()) {
            Bus b = buses.remove();
            Iterator<Integer> i = b.endPoint.iterator();
            int prev, next;
            if(i.hasNext())
            {
                prev = i.next();
                while(i.hasNext())
                {
                    next = i.next();
                    if(!(routeGraph.isEdge(prev, next)))
                    {
                        Edge edge = new Edge(prev, next);
                        routeGraph.removeEdge(edge);
                    }
                    prev = next;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 
     * @param d
     * @return
     */
    public boolean addDriver(Driver d) {
        if (!drivers.contains(d)) {
            drivers.add(d);
            return true;
        }
        return false;
    }

    /**
     * 
     * @param d
     * @return
     */
    public boolean removeDriver(Driver d) {
        if (drivers.contains(d)) {
            drivers.remove(d);
            return true;
        }
        return false;
    }

    public boolean addConductor(Conductor c) {
        if(!conductors.contains(c)){
            conductors.add(c);
            return true;
        }
        return false;
    }

    public boolean removeConductor(Conductor c) {
        if(conductors.contains(c)){
            conductors.remove(c);
            return true;
        }
        return false;
    }

    public boolean addPlatform(Platform p){
        if(!platforms.contains(p)){
            platforms.add(p);
            return true;
        }
        return false;
    }

    public boolean removePlatform(Platform p){
        if(platforms.contains(p)){
            platforms.remove(p);
            return true;
        }
        return false;
    }

    public void printRoutes()
    {
        routeGraph.print();
    }
}
