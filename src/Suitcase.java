/**
 * Suitcase
 */
public class Suitcase implements Comparable<Suitcase>{

    /** The weight for this suitcase. */
    float weight;

    /**The distance between the terminal and passenger's point of arrival */
    long distance;

    public Suitcase() {
    }

    public Suitcase(float weight, long distance) {
        this.weight = weight;
        this.distance = distance;
    }

    /**
     * Set the suitcase weight.
     * 
     * @param weight The suitcase weight
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * Get the suitcase weight.
     * 
     * @return The suitcase weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Set the distance.
     * 
     * @param weight The distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Get the distance.
     * 
     * @return The distance
     */
    public long getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Suitcase o) {
        return (int)this.distance - (int)o.getDistance();
    }

    /**
     * String display of suitcase information. 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Suitcase: \nWeight: ");
        sb.append(weight);
        sb.append("\nDistance: ");
        sb.append(distance);

        return sb.toString();
    }
}
