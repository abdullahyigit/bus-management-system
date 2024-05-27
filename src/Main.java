import java.util.NavigableMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Queue;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Terminal terminal = new Terminal();

        Suitcase st = new Suitcase(5, 250);
        Passenger passenger = new Passenger(2614431, 23, 422614431, "Abdullah", "Yigit", st, 41);
        terminal.addPassenger(passenger);

        Driver dr4 = new Driver(1111114, "Hikmet", "Taş", 50, 111111114);
        Conductor cd4 = new Conductor(2222224, "Ramiz", "Pala", 40, 222222224);

        Driver dr = new Driver(1111111, "Ahmet", "Yıldız", 20, 111111111);
        Conductor cd = new Conductor(2222222, "Murat", "Bayraktar", 20, 222222221);
        ArrayList<Integer> route = new ArrayList<>();
        route.add(41);
        route.add(54);
        route.add(11);
        route.add(50);
        Bus bus = new Bus(dr, cd, route, "17:00", "21:00", "Ulusoy");
        dr.setNotAvailable();
        terminal.addBus(bus);

        Driver dr2 = new Driver(1111112, "Mehmet", "Güneş", 20, 111111112);
        Conductor cd2 = new Conductor(2222222, "Ali", "Kara", 20, 222222222);
        ArrayList<Integer> route2 = new ArrayList<>();
        route2.add(30);
        route2.add(50);
        route2.add(10);
        route2.add(5);
        Bus bus2 = new Bus(dr2, cd2, route2, "19:00", "23:00", "Kamilkoc");
        dr2.setNotAvailable();
        terminal.addBus(bus2);

        Driver dr3 = new Driver(1111113, "Mehmet", "Güneş", 20, 111111113);
        Conductor cd3 = new Conductor(2222223, "Ali", "Kara", 20, 222222223);
        ArrayList<Integer> route3 = new ArrayList<>();
        route3.add(30);
        route3.add(50);
        route3.add(10);
        route3.add(5);
        Bus bus3 = new Bus(dr3, cd3, route3, "08:00", "16:00", "Pamukkale");
        dr3.setNotAvailable();
        terminal.addBus(bus3);

        Platform p1 = new Platform(1, bus);
        Platform p2 = new Platform(2, bus2);
        Platform p3 = new Platform(3, null);
        Platform p4 = new Platform(4, null);
        p1.bus.setNotAvailable();
        p2.bus.setNotAvailable();

        terminal.addPlatform(p1);
        terminal.addPlatform(p2);
        terminal.addPlatform(p3);
        terminal.addPlatform(p4);
        terminal.addDriver(dr);
        terminal.addDriver(dr2);
        terminal.addDriver(dr3);
        terminal.addDriver(dr4);
        terminal.addConductor(cd);
        terminal.addConductor(cd2);
        terminal.addConductor(cd3);
        terminal.addConductor(cd4);

        menu(terminal);
    }

    //============================= ALL MENUS ==================================================================

    public static void menu(Terminal terminal) {
        System.out.println("Bus Terminal Management System");
        System.out.println("------------------------------\n");
        String selection = "";
        String subSelection = "";
        String ID = "";
        while (!selection.equals("6")) {
            System.out.println("Selection is : " + selection + " \n\n");
            System.out.println("1) Admin");
            System.out.println("2) Ticket Seller");
            System.out.println("3) Driver");
            System.out.println("4) Passanger");
            System.out.println("5) Conductor");
            System.out.println("6) Exit");
            System.out.print("Enter : ");
            selection = scanner.nextLine();
            if (selection.equals("1") || selection.equals("2")) {
                while (!subSelection.equals("3")) {
                    System.out.println("\n\n1) Log in");
                    System.out.println("2) Sign up");
                    System.out.println("3) Exit");
                    System.out.print("Enter : ");
                    subSelection = scanner.nextLine();
                    if (subSelection.equals("1")) {
                        Admin user = logIn(selection.equals("2"));
                        if (user != null) {
                            if (user.isSubAdmin()) {
                                System.out.println("Ticket seller menu ");
                            } else {
                                adminMenu(terminal);
                                scanner.nextLine();
                                break;
                            }
                        }
                    } else if (subSelection.equals("2")) {
                        signUp(selection.equals("2"));
                    } else if (!subSelection.equals("3")) {
                        System.out.println("Please enter a valid input !");
                        subSelection = "";
                    }
                }
                subSelection = "";

            } else if (selection.equals("3")) {
                System.out.print("Enter your id in order to access the system : ");
                ID = scanner.nextLine();
                Driver driver = terminal.drivers.find(new Driver(Integer.parseInt(ID)));
                if (driver == null) {
                    System.out.println("There is no such a driver !");
                } else {
                    driverMenu(terminal, driver);
                }
            } else if (selection.equals("4")) {
                System.out.print("Enter your id in order to access the system : ");
                ID = scanner.nextLine();
                Passenger passenger = terminal.passengers.get(Integer.parseInt(ID));
                if (passenger == null) {
                    System.out.println("There is no such a passenger !");
                } else {
                    passengerMenu(terminal, passenger);
                }
            } else if (selection.equals("5")) {
                System.out.print("Enter your id in order to access the system : ");
                ID = scanner.nextLine();
                Conductor conductor = terminal.conductors.find(new Conductor(Integer.parseInt(ID)));
                if (conductor == null) {
                    System.out.println("There is no such a conductor !");
                } else {
                    // condtorMenu(terminal, conductor);
                }
            } else if (!selection.equals("6")) {
                System.out.println("Please enter a valid input !");
                selection = "";
            }
            System.out.println();
        }
    }
    private static void signUp(boolean isSubAdmin) {
        String name = null, surname = null, age = null, phoneNumber = null, password = null,
                userName = null;
        System.out.print("\nPlease enter your name : ");
        name = scanner.nextLine();
        System.out.print("Please enter your surname : ");
        surname = scanner.nextLine();
        while (age == null) {
            System.out.print("Please enter your age : ");
            age = scanner.nextLine();
            if (!age.chars().allMatch(Character::isDigit)) {
                System.out.println("Please enter a numeric input ");
                age = null;
            }
        }
        System.out.print("Please enter your phone number : ");
        phoneNumber = scanner.nextLine();
        while (userName == null) {
            System.out.print("Please enter a username : ");
            userName = scanner.nextLine();
            if (isUserNameExist(userName)) {
                System.out.println("This username is alerady exists ! Please try something else !");
                userName = null;
            }
        }
        System.out.print("Please enter a password : ");
        password = scanner.nextLine();
        Admin member = new Admin(name, surname, age, phoneNumber, password, userName, isSubAdmin);
        writeToFile(member);
        System.out.println("\n\nSuccessfully signed up ! Now, you can log in");
    }

    private static Admin logIn(boolean isSubAdmin) {
        Admin user = null;
        String userName, password;
        System.out.print("\nEnter your username : ");
        userName = scanner.nextLine();
        System.out.print("Enter your password : ");
        password = scanner.nextLine();
        user = getUser(userName, password);
        if (user == null) {
            System.out.println("Username or password is wrong. Try again !");
            return null;
        }
        if (user.isSubAdmin() ^ isSubAdmin) {
            System.out.println("Username or password is wrong. Try again !");
            return null;
        }
        System.out.println("\nLogin successful !");
        return user;
    }

    private static Admin getUser(String userName, String password) {
        try {
            FileInputStream fis = new FileInputStream("./members.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                boolean strToBool = false;
                String[] splited = sc.nextLine().split("\\s+");
                if (splited[6].equals("true")) {
                    strToBool = true;
                }
                if (splited[0].equals(userName) && splited[1].equals(password)) {
                    sc.close();
                    return new Admin(splited[0], splited[1], splited[2], splited[3], splited[4],
                            splited[5],
                            strToBool);
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isUserNameExist(String userName) {
        try {
            FileInputStream fis = new FileInputStream("./members.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String[] splited = sc.nextLine().split("\\s+", 1);
                if (splited[0].equals(userName)) {
                    sc.close();
                    return true;
                }
            }
            sc.close();
        } catch (IOException e) {
        }
        return false;
    }

    private static void writeToFile(Admin member) {
        try {
            File membersFile = new File("./members.txt");
            membersFile.createNewFile();
            FileWriter fw = new FileWriter("./members.txt", true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(member.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void adminMenu(Terminal terminal) {
        int selection = 0;
        while (selection != 5) {
            System.out.println("\nAdmin Menu");
            System.out.println("-------------------------\n");
            System.out.println("1 - Add/Remove User");
            System.out.println("2 - Manages Shifts");
            System.out.println("3 - Manages Travel Informations");
            System.out.println("4 - Manages Platforms");
            System.out.println("5 - Back to the Main Menu\n");
            System.out.print("Choice : ");

            selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    userSelectionMenu(terminal);
                    break;
                case 2:
                    // Perform "encrypt number" case.
                    break;
                case 3:
                    manageTravelInformations(terminal);
                    break;
                case 4:
                    managePlatformMenu(terminal);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please enter a valid choice!\n");
            }
        }
    }

    public static void userSelectionMenu(Terminal terminal) {
        int selection = 0;
        while (selection != 9) {
            System.out.println("\nUser Type Selection Menu");
            System.out.println("-------------------------\n");
            System.out.println("1 - Add Bus");
            System.out.println("2 - Add Driver");
            System.out.println("3 - Add Conductor");
            System.out.println("4 - Add Passenger");
            System.out.println("5 - Remove Bus");
            System.out.println("6 - Remove Driver");
            System.out.println("7 - Remove Conductor");
            System.out.println("8 - Remove Passenger");
            System.out.println("9 - Back to Admin Menu\n");
            System.out.print("Choice : ");

            selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    busAdd(terminal);
                    break;
                case 2:
                    driverAdd(terminal);
                    break;
                case 3:
                    conductorAdd(terminal);
                    break;
                case 4:
                    passengerAdd(terminal, "admin");
                    break;
                case 5:
                    busRemove(terminal);
                    break;
                case 6:
                    driverRemove(terminal);
                    break;
                case 7:
                    conductorRemove(terminal);
                    break;
                case 8:
                    passengerRemove(terminal);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Please enter a valid choice!\n");
                    userSelectionMenu(terminal);
            }
        }
    }

    public static void passengerMenu(Terminal terminal, Passenger passenger) {
        int selection = 0;
        while (selection != 4) {
            System.out.println("\nPassenger Menu");
            System.out.println("-------------------------\n");
            System.out.println("1 - Enter Your Informations");
            System.out.println("2 - View Your Informations");
            System.out.println("3 - View Bus Time and Platform Informations");
            System.out.println("4 - Quit\n");
            System.out.print("Choice : ");
            selection = scanner.nextInt();
            System.out.println();

            switch (selection) {
                case 1:
                    passengerEdit(terminal, passenger);
                    Scanner menuInput = new Scanner(System.in);
                    int selection2 = 0;
                    System.out.println("\nYour informations are updated!");
                    while (selection2 != 1 && selection2 != 2) {
                        System.out.println("Press '1' for Passenger Menu, press '2' for Main Menu\n");
                        System.out.print("Choice : ");
                        selection2 = menuInput.nextInt();
                        if (selection2 == 1)
                            passengerMenu(terminal, passenger);
                        else if (selection2 == 2)
                            break;
                        else {
                            System.out.println("\nPlease enter a valid choice!");
                        }
                    }
                    break;
                case 2:
                    NavigableMap<Integer, Passenger> p = terminal.getPassengers();
                    System.out.println(p.get(passenger.getPassengerId()).toString());
                    System.out.println("\nRedirected to the Passenger Menu\n");
                    break;
                case 3:
                    passengerViewBusInfo(terminal, passenger);
                    System.out.println("\nRedirected to the Passenger Menu\n");
                    break;
                case 4:
                    break;
                default:
                    System.out.println("\nPlease enter a valid choice!\n");
            }
        }
    }

    private static void driverMenu(Terminal terminal, Driver driver) {
        String selection = "";
        while (!selection.equals("4")) {
            System.out.println("\nDriver Menu");
            System.out.println("-------------------------\n");
            System.out.println("1 - Update Your Informations ");
            System.out.println("2 - View Your Informations");
            System.out.println("3 - Check the attendace of the passengers");
            System.out.println("4 - Exit");
            System.out.print("Enter : ");
            selection = scanner.nextLine();
            if (selection.equals("1")) {
                driverEdit(terminal, driver);
            } else if (selection.equals("2")) {
                driverViewInfo(terminal, driver);
            } else if (selection.equals("3")) {
                passengerViewDriver(terminal,driver);
            } else if (!selection.equals("4")) {
                System.out.println("Please enter a valid input !");
                selection = "";
            }
        }

    }

    private static void conductorMenu(Terminal terminal, Conductor conductor) {
        String selection = "";
        while (!selection.equals("4")) {
            System.out.println("\nConductor Menu");
            System.out.println("-------------------------\n");
            System.out.println("1 - Update Your Informations ");
            System.out.println("2 - View Your Informations");
            System.out.println("3 - Check the attendace of the passengers");
            System.out.println("4 - Exit");
            System.out.print("Enter : ");
            selection = scanner.nextLine();
            if (selection.equals("1")) {
                conductorEdit(terminal,conductor);
            } else if (selection.equals("2")) {
                conductorViewInfo(terminal, conductor);
            } else if (selection.equals("3")) {
                passengerViewConductor(terminal, conductor);
            } else if (!selection.equals("4")) {
                System.out.println("Please enter a valid input !");
                selection = "";
            }
        }

    }

    public static void managePlatformMenu(Terminal terminal) {
        int selection = 0;
        while (selection != 3) {
            System.out.println("\nManage Platform Menu");
            System.out.println("-------------------------\n");
            System.out.println("1 - View Platforms");
            System.out.println("2 - Add a Bus to an available Platform");
            System.out.println("3 - Back to the Admin Menu");
            System.out.print("\nChoice : ");

            selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    platformView(terminal);
                    break;
                case 2:
                    platformAddBus(terminal);
                    break;
                case 3:
                    adminMenu(terminal);
                    break;
                default:
                    System.out.println("Please enter a valid choice!\n");
                    break;
            }
        }
    }

    public static void manageTravelInformations(Terminal terminal) {
        int selection;

        System.out.println("\nManage Travel Informations Menu");
        System.out.println("-------------------------\n");
        System.out.println("\nAll Filled Platforms and Buses");
        System.out.println("-------------------------\n");

        ArrayList<Platform> platforms = terminal.getPlatforms();

        if (platforms.size() != 0) {
            for (int i = 0; i < platforms.size(); i++) {
                if (platforms.get(i).bus != null) {
                    System.out.print("Platform " + platforms.get(i).platformNumber + " - " + platforms.get(i).bus.name
                            + " / " + platforms.get(i).bus.departureTime);
                    System.out.print("- " + platforms.get(i).bus.arrivalTime + " / " + platforms.get(i).bus.endPoint);
                    System.out.println();
                }
            }
        }
        System.out.println("\nPlease select a bus to rearrange it's time informations");

        selection = scanner.nextInt();

        if (selection > 0 && selection <= platforms.size()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter departure time : ");
            String departure = scanner.nextLine();
            Scanner scanner2 = new Scanner(System.in);
            System.out.print("Please enter arrival time : ");
            String arrival = scanner2.nextLine();
            platforms.get(selection - 1).bus.setDepartureTime(departure);
            platforms.get(selection - 1).bus.setArrivalTime(arrival);
            System.out.println("\nBus' travel informations are changed!");
            System.out.println("Redirected to the Admin Menu...");
            adminMenu(terminal);
        } else {
            System.out.println("\nYou entered wrong input!");
            System.out.println("Redirected to the Admin Menu...");
            adminMenu(terminal);
        }
    }

    //==================== ADD USER METHODS ==================================================================

    public static void passengerAdd(Terminal terminal, String editor){
        Scanner input = new Scanner(System.in);
        String[] inputType = {"Name", "Surname", "Age", "Phone Number", "Suitcase Weight", "Suitcase Distance", "Destination"};
        String[] inputInfo = new String[7];

        System.out.println("\nPlease Enter Passenger's Informations");
        System.out.println("-------------------------\n");

        for(int i = 0; i < 7; i++){
            System.out.print("Enter Passenger's " + inputType[i] + ": ");
            inputInfo[i] = input.nextLine();
        }
        int passengerID = Integer.parseInt(inputInfo[3].substring(2,9));
        Suitcase st = new Suitcase(Float.parseFloat(inputInfo[4]), Long.parseLong(inputInfo[5]));
        Passenger passenger = new Passenger(passengerID,Integer.parseInt(inputInfo[2]), Long.parseLong(inputInfo[3]), inputInfo[0], inputInfo[1], st, Integer.parseInt(inputInfo[6]));
        NavigableMap<Integer, Passenger> p = terminal.getPassengers();
        Queue<Bus> buses = terminal.getBuses();
        int busCount = 0;

        if(buses.isEmpty()){
            System.out.println("\nThere is no available bus!");

            if(editor.equals("admin")){
                System.out.print("\nRedirected to the Previous Menu\n");
            }
            else if(editor.equals("ticket seller")){
                System.out.print("\nRedirected to the PreviousMenu\n");
            }
        }

        else{

            if(p.get(passengerID) == null) {

                System.out.println("\nHere the departure times of buses");
                System.out.println("-------------------------\n");

                for(Bus bus : buses){
                    if(bus.endPoint.contains(Integer.parseInt(inputInfo[6])) && bus.seats.size() <= bus.seatNumber){
                        System.out.println((busCount+1) + " - " + bus.departureTime);
                        busCount++;
                    }
                }

                Scanner input2 = new Scanner(System.in);
                busCount = 0;
                System.out.print("\nPlease select a bus : ");
                int choice2 = input2.nextInt() - 1;

                for(Bus bus : buses){
                    if(bus.endPoint.contains(Integer.parseInt(inputInfo[6])) && bus.seats.size() <= bus.seatNumber){
                        if(busCount == choice2){
                            bus.addPassenger(passenger);
                        }
                        busCount++;
                    }
                }

                terminal.addPassenger(passenger);
                System.out.println("\nPassenger is added to the terminal!");
                System.out.println("Passenger's ID is : " + passengerID);

                if(editor.equals("admin")){
                    System.out.print("Redirected to the Previous Menu\n");
                }
                else if(editor.equals("ticket seller")){
                    System.out.print("\nRedirected to the Previous Menu...\n");
                    }

            }

            else {
                if(editor.equals("admin")){
                    System.out.print("\nThis passenger is already in the system!\n Redirected to the Previous Menu...\n");
                }
                else if(editor.equals("ticket seller")){
                    System.out.print("\nThis passenger is already in the system!\n Redirected to the Previous Seller Menu...\n");
                }
            }

        }

    }

    public static void driverAdd(Terminal terminal){
        Scanner input = new Scanner(System.in);
        String[] inputType = {"Name", "Surname", "Age", "Phone Number"};
        String[] inputInfo = new String[4];

        System.out.println("\nPlease Enter Driver's Informations");
        System.out.println("-------------------------\n");

        for(int i = 0; i < 4; i++){
            System.out.print("Enter Driver's " + inputType[i] + ": ");
            inputInfo[i] = input.nextLine();
        }

        Driver driver = new Driver(Integer.parseInt(inputInfo[3].substring(2,9)), inputInfo[0], inputInfo[1], Integer.parseInt(inputInfo[2]), Long.parseLong(inputInfo[3]));
        terminal.addDriver(driver);
        System.out.println("\nDriver added to the terminal!");
        System.out.print("\nRedirected to the Previous Menu...\n");
    }

    public static void busAdd(Terminal terminal){

        Scanner input = new Scanner(System.in);

        AVLTree<Driver> drivers = terminal.getDrivers();
        ArrayList<Driver> availableDrivers = new ArrayList<Driver>();
        findAvailableDrivers(terminal,drivers,availableDrivers);

        BinarySearchTree<Conductor> conductors = terminal.getConductors();
        ArrayList<Conductor> availableConductors = new ArrayList<Conductor>();
        findAvailableConductors(terminal,conductors,availableConductors);

        if(availableDrivers.size() == 0){
            System.out.println("\nThere is no driver available!");
            System.out.println("Redirected to the Previous Menu...\n");
        }

        else if(availableConductors.size() == 0){
            System.out.println("\nThere is no conductor available!");
            System.out.println("Redirected to the Previous Menu...\n");
        }

        else {

            String[] inputType = {"Departure Time", "Arrival Time", "Brand Name"};
            String[] inputInfo = new String[3];

            System.out.println("\nPlease Enter Bus' Informations");
            System.out.println("-------------------------\n");

            for (int i = 0; i < 3; i++) {
                System.out.print("Enter Bus' " + inputType[i] + ": ");
                inputInfo[i] = input.nextLine();
            }

            ArrayList<Integer> route = new ArrayList<>();
            route.add(41);
            route.add(54);
            route.add(14);
            route.add(6);

            System.out.println("Please select an available Driver :");


            for (int i = 0; i < availableDrivers.size(); i++) {
                System.out.println((i + 1) + " - " + availableDrivers.get(i).getName() + " " + availableDrivers.get(i).getSurname() +
                        " - " + availableDrivers.get(i).getDriverId());
            }

            int choice = input.nextInt();

            while (choice < 1 && choice > availableDrivers.size()) {
                System.out.println("Please enter valid choice :");
                choice = input.nextInt();
            }

            Driver driver = drivers.find(availableDrivers.get(choice - 1));

            System.out.println("Please select an available Conductor :");


            for (int i = 0; i < availableConductors.size(); i++) {
                System.out.println((i + 1) + " - " + availableConductors.get(i).getName() + " " + availableConductors.get(i).getSurname() +
                        " - " + availableConductors.get(i).getConductorId());
            }

            int choice2 = input.nextInt();

            while (choice2 < 1 && choice2 > availableDrivers.size()) {
                System.out.println("Please enter valid choice :");
                choice2 = input.nextInt();
            }

            Conductor conductor = conductors.find(availableConductors.get(choice2 - 1));

            Bus bus = new Bus(driver, conductor, route, inputInfo[0], inputInfo[1], inputInfo[2]);
            terminal.addBus(bus);
            System.out.println("\nBus is added to the terminal!");
            System.out.print("\nRedirected to the Admin Menu...\n");
        }
    }

    public static void conductorAdd(Terminal terminal){
        Scanner input = new Scanner(System.in);
        String[] inputType = {"Name", "Surname", "Age", "Phone Number"};
        String[] inputInfo = new String[4];

        System.out.println("\nPlease Enter Conductor's Informations");
        System.out.println("-------------------------\n");

        for(int i = 0; i < 4; i++){
            System.out.print("Enter Conductor's " + inputType[i] + ": ");
            inputInfo[i] = input.nextLine();
        }

        Conductor conductor = new Conductor(Integer.parseInt(inputInfo[3].substring(2,9)), inputInfo[0], inputInfo[1], Integer.parseInt(inputInfo[2]), Long.parseLong(inputInfo[3]));
        terminal.addConductor(conductor);
        System.out.println("\nConductor added to the terminal!");
        System.out.print("\nRedirected to the Previous Menu...\n");
    }

    public static void platformAddBus(Terminal terminal){

        System.out.println("\nAll Available Buses");
        System.out.println("---------------------");
        int busCount = 1;
        int platformCount = 1;
        Scanner input = new Scanner(System.in);

        ArrayList<Platform> platforms = terminal.getPlatforms();
        Queue<Bus> buses = terminal.getBuses();

        for(Bus bus : buses){
            if(bus.isAvailable){
                System.out.println(busCount + " - " + bus.toString());
                busCount++;
            }
        }

        if(busCount == 1){
            System.out.print("\nThere is no available bus!");
            System.out.print("\nRedirected to the Previous Menu...\n");
            managePlatformMenu(terminal);
        }

        System.out.print("\nPlease select  a bus : ");
        int choice = input.nextInt();

        while(choice < 1 && choice > busCount){
            System.out.println("\nYour choice is wrong!");
            System.out.print("Please select  a bus : ");
            choice = input.nextInt();
        }

        System.out.println("\nAll Available Platforms");
        System.out.println("---------------------");

        for(int i = 0; i < platforms.size(); i++){
            if(platforms.get(i).bus == null){
                System.out.println(platformCount + " - " + "Platform " + platforms.get(i).platformNumber);
                platformCount++;
            }
        }

        if(platformCount == 1){
            System.out.print("\nThere is no available platform!");
            System.out.print("\nRedirected to the Previous Menu...\n");
            managePlatformMenu(terminal);
        }

        System.out.print("\nPlease select  a platform : ");
        int choice2 = input.nextInt();

        while(choice < 1 && choice > busCount){
            System.out.println("\nYour choice is wrong!");
            System.out.print("Please select  a bus : ");
            choice2 = input.nextInt();
        }

        busCount = 0;
        platformCount = 0;

        for(Bus bus : buses){
            if(bus.isAvailable){
                busCount++;
                if(choice == busCount){
                    for(int i = 0; i < platforms.size(); i++){
                        if(platforms.get(i).bus == null){
                            platformCount++;

                            if(choice2 == platformCount){
                                platforms.get(i).setBus(bus);
                                bus.setNotAvailable();
                            }

                        }
                    }
                }

            }
        }
        managePlatformMenu(terminal);
    }

    //==================== REMOVE USER METHODS ==================================================================

    public static void passengerRemove(Terminal terminal){
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter a passenger's ID : ");

        int passengerID = input.nextInt();

        NavigableMap<Integer, Passenger> p = terminal.getPassengers();

        terminal.removePassenger(p.get(passengerID));
        System.out.println("\nPassenger is removed from the terminal!");
        System.out.print("Redirected to the Previous Menu\n");
    }

    public static void driverRemove(Terminal terminal){

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter a driver's ID : ");

        int driverID = input.nextInt();

        AVLTree<Driver> drivers = terminal.getDrivers();
        Driver tempDriver = new Driver(driverID);
        Driver driver = drivers.find(tempDriver);

        drivers.delete(driver);

        System.out.println("\nDriver is removed from the terminal!");
        System.out.print("Redirected to the Previous Menu\n");
    }

    public static void conductorRemove(Terminal terminal){

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter a conductor's ID : ");

        int conductorID = input.nextInt();

        BinarySearchTree<Conductor> conductors = terminal.getConductors();
        Conductor tempConductor = new Conductor(conductorID);
        Conductor conductor = conductors.find(tempConductor);

        conductors.remove(conductor);

        System.out.println("\nConductor is removed from the terminal!");
        System.out.print("Redirected to the Previous Menu\n");
    }

    public static void busRemove(Terminal terminal){
        terminal.removeBus();
        System.out.println("\nBus at the peek of queue is removed from the terminal!");
        System.out.print("Redirected to the Previous Menu\n");
    }

    //==================== EDIT USER METHODS ==================================================================

    public static void passengerEdit(Terminal terminal, Passenger passenger){
        Scanner input = new Scanner(System.in);
        String[] inputType = {"Name", "Surname", "Age", "Phone Number", "Suitcase Weight", "Suitcase Distance"};
        String[] inputInfo = new String[6];

        System.out.println("\nPlease Enter Your Informations");
        System.out.println("-------------------------\n");
        for(int i = 0; i < 6; i++){
            System.out.print("Enter Your " + inputType[i] + ": ");
            inputInfo[i] = input.nextLine();
        }
        Suitcase st = new Suitcase(Float.parseFloat(inputInfo[4]), Long.parseLong(inputInfo[5]));
        int passengerID = Integer.parseInt(inputInfo[3]);

        passenger.setAge(Integer.parseInt(inputInfo[2]));
        passenger.setName(inputInfo[0]);
        passenger.setSurname(inputInfo[1]);
        passenger.setSuitcase(st);
        passenger.setPhoneNumber(Long.parseLong(inputInfo[3]));
    }

    public static void driverEdit(Terminal terminal, Driver driver){
        Scanner input = new Scanner(System.in);
        String[] inputType = {"Name", "Surname", "Age","Phone Number"};
        String[] inputInfo = new String[4];

        System.out.println("\nPlease Enter Your Informations");
        System.out.println("-------------------------\n");

        for(int i = 0; i < 4; i++){
            System.out.print("Enter Your " + inputType[i] + ": ");
            inputInfo[i] = input.nextLine();
        }

        driver.setName(inputInfo[0]);
        driver.setSurname(inputInfo[1]);
        driver.setAge(Integer.parseInt(inputInfo[2]));
        driver.setPhoneNumber(Long.parseLong(inputInfo[3]));

        System.out.println("\nYour Informations are updated!");
        System.out.println("Redirected to the Previous Menu...\n");
    }

    public static void conductorEdit(Terminal terminal, Conductor conductor){
        Scanner input = new Scanner(System.in);
        String[] inputType = {"Name", "Surname", "Age", "Phone Number"};
        String[] inputInfo = new String[4];

        System.out.println("\nPlease Enter Your Informations");
        System.out.println("-------------------------\n");

        for(int i = 0; i < 4; i++){
            System.out.print("Enter Your " + inputType[i] + ": ");
            inputInfo[i] = input.nextLine();
        }

        conductor.setName(inputInfo[0]);
        conductor.setSurname(inputInfo[1]);
        conductor.setAge(Integer.parseInt(inputInfo[2]));
        conductor.setPhoneNumber(Long.parseLong(inputInfo[3]));

        System.out.println("\nYour Informations are updated!");
        System.out.println("Redirected to the Previous Menu...\n");
    }

    //==================== VIEW METHODS ==================================================================

    public static void passengerViewBusInfo(Terminal terminal, Passenger passenger){

        System.out.println("\nYour Bus' Informations");
        System.out.println("-------------------------\n");

        Queue<Bus> buses = terminal.getBuses();
        System.out.println("\nYour Bus' Informations   " + buses.size());

        for(Bus bus : buses){
            System.out.println("-------------------------\n");
            if(bus.passengers.contains(passenger)){
                System.out.println("Bus' Departure Time is : " + bus.departureTime);
                System.out.println("Bus' Arrival Time is : " + bus.arrivalTime);
                System.out.println("Bus' Arrival Destination is : " + bus.endPoint.get(bus.endPoint.size()-1));
                System.out.println();
            }
        }
    }
    /*
    public static void seatView(Terminal terminal, Bus bus){
        for(int i = 1; i <= bus.seatNumber; i++){
            if(i < 10){
                System.out.println();
            }
        }
    }*/

    public static void driverViewInfo(Terminal terminal, Driver driver){
        System.out.println("\nYour Informations:");
        System.out.println(driver.toString());
        System.out.println("\nRedirected to the Passenger Menu\n");
    }

    public static void conductorViewInfo(Terminal terminal, Conductor conductor){
        System.out.println("\nYour Informations:");
        System.out.println(conductor.toString());
        System.out.println("\nRedirected to the Passenger Menu\n");
    }

    public static void passengerViewDriver(Terminal terminal, Driver driver){

        if(driver.isAvailable()){
            System.out.println("\nYou have not been placed in any bus!");
            System.out.println("Redirected to the Previous Menu...\n");
        }
        else{
            Queue<Bus> buses = terminal.getBuses();

            for(Bus bus : buses){
                if(bus.driver == driver){
                    bus.printPassengersList();
                }
            }

            System.out.println("\nRedirected to the Previous Menu...\n");
        }
    }

    public static void passengerViewConductor(Terminal terminal, Conductor conductor){

        if(conductor.isAvailable()){
            System.out.println("\nYou have not been placed in any bus!");
            System.out.println("Redirected to the Previous Menu...\n");
        }
        else{
            Queue<Bus> buses = terminal.getBuses();

            for(Bus bus : buses){
                if(bus.conductor == conductor){
                    bus.printPassengersList();
                }
            }

            System.out.println("\nRedirected to the Previous Menu...\n");
        }
    }

    public static void platformView(Terminal terminal){

        System.out.println("\nAll Platforms and Buses");
        System.out.println("-------------------------\n");

        ArrayList<Platform> platforms = terminal.getPlatforms();

        if(platforms.size() != 0){
            for(int i = 0; i < platforms.size();i++){
                if(platforms.get(i).bus != null) {
                    System.out.print("Platform " + platforms.get(i).platformNumber + " - " + platforms.get(i).bus.name + " / " + platforms.get(i).bus.departureTime);
                    System.out.print("- " + platforms.get(i).bus.arrivalTime + " / " + platforms.get(i).bus.endPoint);
                    System.out.println();
                }
                else{
                    System.out.println("Platform " + platforms.get(i).platformNumber + " - Empty");
                }
            }
        }
        managePlatformMenu(terminal);
    }



    //==================== FIND METHODS ==================================================================

    public static void findAvailableDrivers(Terminal terminal, AVLTree<Driver> drivers, ArrayList<Driver> availableDrivers){
        if(drivers.root != null) {
            if (drivers.root.data.isAvailable()) {
                availableDrivers.add(drivers.root.data);
            }
            if(drivers.root.left != null){
                AVLTree<Driver> temp1 = new AVLTree<Driver>();
                temp1.root = drivers.root.left;
                findAvailableDrivers(terminal,temp1,availableDrivers);
            }
            if(drivers.root.right != null){
                AVLTree<Driver> temp2 = new AVLTree<Driver>();
                temp2.root = drivers.root.right;
                findAvailableDrivers(terminal,temp2,availableDrivers);
            }
        }
    }

    public static void findAvailableConductors(Terminal terminal, BinarySearchTree<Conductor> conductors, ArrayList<Conductor> availableConductors){
        if(conductors.root != null) {
            if (conductors.root.data.isAvailable()) {
                availableConductors.add(conductors.root.data);
            }
            if(conductors.root.left != null){
                BinarySearchTree<Conductor> temp1 = new BinarySearchTree<Conductor>();
                temp1.root = conductors.root.left;
                findAvailableConductors(terminal,temp1,availableConductors);
            }
            if(conductors.root.right != null){
                BinarySearchTree<Conductor> temp2 = new BinarySearchTree<Conductor>();
                temp2.root = conductors.root.right;
                findAvailableConductors(terminal,temp2,availableConductors);
            }
        }
    }


   /* public static void createObjects(Terminal terminal) {
        System.out.println("-------------------------------------------");
        int total = 0;
        for(int i = 0; i < 100; i++)
        {
            Suitcase st = new Suitcase(1, i*2);
            Passenger passenger = new Passenger(i, 20, 0000000, "name", "surname", st);
            long startTime = System.nanoTime();
            terminal.addPassenger(passenger);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            total += totalTime;
        }
        System.out.println("Time after adding 100 passengers to nm: " + total/100);
        total = 0;

        System.out.println("-------------------------------------------");
        for(int i = 0; i < 1000; i++)
        {
            Suitcase st = new Suitcase(5, i*2);
            Passenger passenger = new Passenger(i+100, 20, 0000000, "name", "surname", st);
            long startTime = System.nanoTime();
            terminal.addPassenger(passenger);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            total += totalTime;
        }
        System.out.println("Time after adding 1000 passengers to nm: " + total/1000);
        total = 0;

        System.out.println("-------------------------------------------");
        for(int i = 0; i < 10000; i++)
        {
            Suitcase st = new Suitcase(10, i*2);
            Passenger passenger = new Passenger(i+1100, 20, 0000000, "name", "surname", st);
            long startTime = System.nanoTime();
            terminal.addPassenger(passenger);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            total += totalTime;
        }
        System.out.println("Time after adding 10000 passengers to nm: " + total/10000);
        total = 0;

        System.out.println("-------------------------------------------");
        for(int i = 0; i < 1; i++)
        {
            Driver dr = new Driver(1, "name", "surname", 20);
            Conductor cd = new Conductor(1, "name", "surname", 20);
            ArrayList<Integer> route = new ArrayList<>();
            route.add(41);
            route.add(54);
            route.add(11);
            route.add(3);
            Bus bus = new Bus(dr, cd, route);
            for(int j = 0; j < 100; j++)
            {
                Suitcase st = new Suitcase(15, j*2);
                Passenger passenger = new Passenger(j, 20, 0000000, "name", "surname", st);
                long startTime = System.nanoTime();
                bus.addPassenger(passenger);
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                total += totalTime;
            }
            terminal.addBus(bus);
        }
        System.out.println("Time after adding 100 passengers to list: " + total/100);
        total = 0;

        System.out.println("-------------------------------------------");
        for(int i = 0; i < 1; i++)
        {
            Driver dr = new Driver(2, "name", "surname", 20);
            Conductor cd = new Conductor(2, "name", "surname", 20);
            ArrayList<Integer> route = new ArrayList<>();
            route.add(41);
            route.add(54);
            route.add(14);
            route.add(6);
            Bus bus = new Bus(dr, cd, route);
            for(int j = 0; j < 1000; j++)
            {
                Suitcase st = new Suitcase(20, j*2);
                Passenger passenger = new Passenger(j+100, 20, 0000000, "name", "surname", st);
                long startTime = System.nanoTime();
                bus.addPassenger(passenger);
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                total += totalTime;
            }
            terminal.addBus(bus);
        }
        System.out.println("Time after adding 1000 passengers to list: " + total/1000);
        total = 0;

        System.out.println("-------------------------------------------");
        for(int i = 0; i < 1; i++)
        {
            Driver dr = new Driver(3, "name", "surname", 20);
            Conductor cd = new Conductor(3, "name", "surname", 20);
            ArrayList<Integer> route = new ArrayList<>();
            route.add(41);
            route.add(54);
            route.add(10);
            route.add(35);
            Bus bus = new Bus(dr, cd, route);
            for(int j = 0; j < 10000; j++)
            {
                Suitcase st = new Suitcase(25, j*2);
                Passenger passenger = new Passenger(j+1100, 20, 0000000, "name", "surname", st);
                long startTime = System.nanoTime();
                bus.addPassenger(passenger);
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                total += totalTime;
            }
            terminal.addBus(bus);
        }
        System.out.println("Time after adding 10000 passengers to list: " + total/10000);
        System.out.println("-------------------------------------------");
    }

    public static void findPassenger(Terminal terminal)
    {
        NavigableMap<Integer, Passenger> p = terminal.getPassengers();
        Suitcase st = new Suitcase(10, 68); //DEFAULT-NOT IMPORTANT
        Passenger passenger1 = new Passenger(100, 20, 0000000, "name", "surname", st);
        Passenger passenger2 = new Passenger(1000, 20, 0000000, "name", "surname", st);
        Passenger passenger3 = new Passenger(10000, 20, 0000000, "name", "surname", st);

        long startTime = System.nanoTime();
        System.out.println(p.get(passenger1.getPassengerId()).toString());
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time after finding a passenger with ID 100 in bst: " + totalTime);
        System.out.println("-------------------------------------------");
        startTime = System.nanoTime();
        System.out.println(p.get(passenger2.getPassengerId()).toString());
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Time after finding a passenger with ID 1000 in bst: " + totalTime);
        System.out.println("-------------------------------------------");
        startTime = System.nanoTime();
        System.out.println(p.get(passenger3.getPassengerId()).toString());
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Time after finding a passenger with ID 10000 in bst: " + totalTime);
        System.out.println("-------------------------------------------");
    }

    public static void removeBus(Terminal terminal)
    {
        System.out.println("Remove the bus from the beginning of the queue");
        long startTime = System.nanoTime();
        terminal.removeBus();
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time: " + totalTime);
        System.out.println("-------------------------------------------");
    }

    public static void addBus(Terminal terminal)
    {
        Driver dr = new Driver(10, "name", "surname", 20);
        Conductor cd = new Conductor(10, "name", "surname", 20);
        ArrayList<Integer> route = new ArrayList<>();
        route.add(41);
        route.add(34);
        Bus bus = new Bus(dr, cd, route);
        for(int j = 0; j < 10; j++)
        {
            Suitcase st = new Suitcase(30, j*2);
            Passenger passenger = new Passenger(j+20000, 20, 0000000, "name", "surname", st);
            bus.addPassenger(passenger);
        }
        System.out.println("Add a bus to the end of the queue");
        long startTime = System.nanoTime();
        terminal.addBus(bus);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time: " + totalTime);
        System.out.println("-------------------------------------------");
    }*/
}
