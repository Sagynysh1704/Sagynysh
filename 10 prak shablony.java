//class RoomBookingSystem
class RoomBookingSystem {
    public boolean bookRoom(String guestName) {
        System.out.println("Room booked for " + guestName);
        return true;
    }
    public boolean checkAvailability(String roomType) {
        System.out.println("Checking availability for " + roomType + " room");
        return true;
    }
    public void cancelBooking(String guestName) {
        System.out.println("Booking cancelled for " + guestName);
    }}
class RestaurantSystem {
    public boolean reserveTable(String guestName) {
        System.out.println("Table reserved for " + guestName);
        return true;
    }
 public void orderFood(String guestName, String[] foodItems) {
        System.out.println("Food ordered for " + guestName + ": " + String.join(", ", foodItems));
    }}
class EventManagementSystem {
    public boolean bookEventHall(String eventName) {
        System.out.println("Event hall booked for " + eventName);
        return true;
    }
    public void orderEquipment(String[] equipmentList) {
        System.out.println("Equipment ordered: " + String.join(", ", equipmentList));
    }}
class CleaningService {
    public void scheduleCleaning(String roomNumber) {
        System.out.println("Cleaning scheduled for room " + roomNumber);
    }
    public void performCleaning(String roomNumber) {
        System.out.println("Cleaning performed for room " + roomNumber);
    }}
// Класс фасада:
public class HotelFacade {
    private RoomBookingSystem roomBooking;
    private RestaurantSystem restaurant;
    private EventManagementSystem eventManagement;
    private CleaningService cleaningService;
    public HotelFacade() {
        this.roomBooking = new RoomBookingSystem();
        this.restaurant = new RestaurantSystem();
        this.eventManagement = new EventManagementSystem();
        this.cleaningService = new CleaningService();
    }  public void bookRoomWithServices(String guestName, String[] foodItems, boolean cleaning) {
        if (roomBooking.bookRoom(guestName)) {
            if (foodItems != null) {
                restaurant.orderFood(guestName, foodItems);
            }
            if (cleaning) {
                cleaningService.scheduleCleaning(guestName);
            }
            System.out.println("Room booked with additional services.");
        } else {
            System.out.println("Failed to book room.");
        } }
    public void organizeEvent(String eventName, String[] equipmentList, String[] guestNames) {
        if (eventManagement.bookEventHall(eventName)) {
            eventManagement.orderEquipment(equipmentList);
            for (String guest : guestNames) {
                roomBooking.bookRoom(guest);
            }  System.out.println("Event organized with room bookings.");
        } else {
            System.out.println("Failed to organize event.");
        }}
    public void reserveTableWithTaxi(String guestName) {
        if (restaurant.reserveTable(guestName)) {
            System.out.println("Taxi called for " + guestName);
        } else {
            System.out.println("Failed to reserve table.");
        }  } }
// class Main
public class Main {
    public static void main(String[] args) {
        HotelFacade hotel = new HotelFacade();
        hotel.bookRoomWithServices("John Doe", new String[] {"Pasta", "Salad"}, true);
        hotel.organizeEvent("Tech Conference", new String[] {"Projector", "Microphone"}, new String[] {"Alice", "Bob"});
        hotel.reserveTableWithTaxi("Charlie");
    }}



//class Employee
class Employee extends OrganizationComponent {
    private String name;
    private String position;
    private double salary;
    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    public String getName() {
        return name;
    }
    public double getBudget() {
        return salary;
    }
    public int getEmployeeCount() {
        return 1;
    }
    public String toString() {
        return position + ": " + name + " (Salary: $" + salary + ")";
    }
}
//class Department
import java.util.ArrayList;
import java.util.List;
class Department extends OrganizationComponent {
    private String name;
    private List<OrganizationComponent> components;
    public Department(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }
    public void addComponent(OrganizationComponent component) {
        components.add(component);
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBudget() {
        double totalBudget = 0;
        for (OrganizationComponent component : components) {
            totalBudget += component.getBudget();
        }
        return totalBudget;
    }
    @Override
    public int getEmployeeCount() {
        int totalEmployees = 0;
        for (OrganizationComponent component : components) {
            totalEmployees += component.getEmployeeCount();
        }
        return totalEmployees;
    }
    public void displayStructure(String indent) {
        System.out.println(indent + "Department: " + name);
        for (OrganizationComponent component : components) {
            if (component instanceof Department) {
                ((Department) component).displayStructure(indent + "  ");
            } else {
                System.out.println(indent + "  " + component);
            }} }}
//class Main
public class Main {
    public static void main(String[] args) {
        Department headOffice = new Department("Head Office");
        headOffice.addComponent(new Employee("Alice", "Manager", 70000));
        headOffice.addComponent(new Employee("Bob", "Assistant", 50000));

        Department itDepartment = new Department("IT Department");
        itDepartment.addComponent(new Employee("Charlie", "Developer", 80000));
        itDepartment.addComponent(new Employee("Dave", "Tester", 60000));

        headOffice.addComponent(itDepartment);

        headOffice.displayStructure("");
        System.out.println("Total budget: $" + headOffice.getBudget());
        System.out.println("Total employees: " + headOffice.getEmployeeCount());
    }
}
