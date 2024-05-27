public class Platform {

    int platformNumber;

    Bus bus;


    public Platform(int platformNumber, Bus bus){
        this.bus = bus;
        this.platformNumber = platformNumber;
    }

    public void setBus(Bus bus){this.bus = bus;}
}
