public class TicketSeller {
    private int ticketsellerId;
    private String name;
    private String surname;
    private int age;

    public int getAge() {
        return age;
    }

    public int getTicketSellerId() {
        return ticketsellerId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTicketSellerId(int ticketsellerId) {
        this.ticketsellerId = ticketsellerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    /**
     * String display of ticket seller information. 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Conductor: \nName: ");
        sb.append(name);
        sb.append("\nSurname: ");
        sb.append(surname);
        sb.append("\nTicket Seller ID: ");
        sb.append(ticketsellerId);
        sb.append("\nAge: ");
        sb.append(age);

        return sb.toString();
    }

}
