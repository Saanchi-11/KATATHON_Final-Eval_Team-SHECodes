
import java.util.*;

class User {
    String name;
    int age;
    String bloodType;
    double height; // in centimeters
    double weight; // in kg
    String location;
    boolean isAvailable; // For donation availability
    boolean hasDiabetes; // True if the user has diabetes
    double sugarLevel; // Fasting sugar level in mg/dL
    double hemoglobin; // Hemoglobin level in g/dL
    String bloodPressure; // Blood pressure in the format "120/80"

    public User(String name, int age, String bloodType, double height, double weight, String location,
            boolean hasDiabetes, double sugarLevel, double hemoglobin, String bloodPressure) {
        this.name = name;
        this.age = age;
        this.bloodType = bloodType;
        this.height = height; // in cm
        this.weight = weight; // in kg
        this.location = location;
        this.hasDiabetes = hasDiabetes;
        this.sugarLevel = sugarLevel;
        this.hemoglobin = hemoglobin;
        this.bloodPressure = bloodPressure;
        this.isAvailable = isFitToDonate();
    }

    public boolean isFitToDonate() {
        return weight >= 45 && // Minimum weight requirement
                !hasDiabetes &&
                sugarLevel >= 70 && sugarLevel <= 140 && // Normal fasting sugar range
                hemoglobin >= 12.5 && hemoglobin <= 17.5; // Normal hemoglobin range
    }

    public void updateDetails(double height, double weight, String location, boolean hasDiabetes, double sugarLevel,
            double hemoglobin, String bloodPressure) {
        this.height = height; // in cm
        this.weight = weight; // in kg
        this.location = location;
        this.hasDiabetes = hasDiabetes;
        this.sugarLevel = sugarLevel;
        this.hemoglobin = hemoglobin;
        this.bloodPressure = bloodPressure;
        this.isAvailable = isFitToDonate(); // Reevaluate donation eligibility
    }

    public String getHealthStatus() {
        String healthStatus = "";

        if (sugarLevel > 120) {
            healthStatus += "Diabetic. ";
        } else if (sugarLevel > 110) {
            healthStatus += "Prediabetic. ";
        }

        if (weight < 45) {
            healthStatus += "Underweight. ";
        }

        return healthStatus.isEmpty() ? "Healthy." : healthStatus;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Blood Type: " + bloodType +
                ", Height: " + height + " cm, Weight: " + weight + " kg, Location: " + location +
                ", Diabetes: " + (hasDiabetes ? "Yes" : "No") +
                ", Sugar Level: " + sugarLevel + " mg/dL, Hemoglobin: " + hemoglobin + " g/dL" +
                ", Blood Pressure: " + bloodPressure +
                ", Fit to Donate: " + (isAvailable ? "Yes" : "No") +
                ", Health Status: " + getHealthStatus();
    }
}

public class Main {
    private static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Blood Donation System ===");
            System.out.println("1. Register User");
            System.out.println("2. Update User Information");
            System.out.println("3. Act as Donor");
            System.out.println("4. Request Blood (Recipient)");
            System.out.println("5. View All Users");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser(sc);
                    break;
                case 2:
                    updateUserInfo(sc);
                    break;
                case 3:
                    markAsDonor(sc);
                    break;
                case 4:
                    requestBlood(sc);
                    break;
                case 5:
                    viewAllUsers();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void registerUser(Scanner sc) {
        System.out.print("Enter unique ID (e.g., email/phone): ");
        String uniqueID = sc.nextLine();
        if (users.containsKey(uniqueID)) {
            System.out.println("User already registered with this ID.");
            return;
        }

        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter blood type: ");
        String bloodType = sc.nextLine();
        System.out.print("Enter height (in cm): ");
        double height = sc.nextDouble();
        System.out.print("Enter weight (in kg): ");
        double weight = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.print("Enter location: ");
        String location = sc.nextLine();
        System.out.print("Do you have diabetes? (yes/no): ");
        boolean hasDiabetes = sc.nextLine().equalsIgnoreCase("yes");
        System.out.print("Enter fasting sugar level (mg/dL): ");
        double sugarLevel = sc.nextDouble();
        System.out.print("Enter hemoglobin level (g/dL): ");
        double hemoglobin = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.print("Enter blood pressure (e.g., 120/80): ");
        String bloodPressure = sc.nextLine();

        User user = new User(name, age, bloodType, height, weight, location, hasDiabetes, sugarLevel, hemoglobin,
                bloodPressure);
        users.put(uniqueID, user);
        System.out.println("User registered successfully!");
        System.out.println("Health Status: " + user.getHealthStatus());
    }

    private static void updateUserInfo(Scanner sc) {
        System.out.print("Enter unique ID: ");
        String uniqueID = sc.nextLine();
        User user = users.get(uniqueID);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.print("Enter new height (in cm): ");
        double height = sc.nextDouble();
        System.out.print("Enter new weight (in kg): ");
        double weight = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new location: ");
        String location = sc.nextLine();
        System.out.print("Do you have diabetes? (yes/no): ");
        boolean hasDiabetes = sc.nextLine().equalsIgnoreCase("yes");
        System.out.print("Enter fasting sugar level (mg/dL): ");
        double sugarLevel = sc.nextDouble();
        System.out.print("Enter hemoglobin level (g/dL): ");
        double hemoglobin = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.print("Enter blood pressure (e.g., 120/80): ");
        String bloodPressure = sc.nextLine();

        user.updateDetails(height, weight, location, hasDiabetes, sugarLevel, hemoglobin, bloodPressure);
        System.out.println("User information updated successfully!");
        System.out.println("Health Status: " + user.getHealthStatus());
    }

    private static void markAsDonor(Scanner sc) {
        System.out.print("Enter unique ID: ");
        String uniqueID = sc.nextLine();
        User user = users.get(uniqueID);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        user.isAvailable = user.isFitToDonate(); // Reevaluate donation eligibility
        if (user.isAvailable) {
            System.out.println("User is fit and marked as available for donation.");
        } else {
            System.out.println("User is not fit to donate blood due to health conditions.");
        }
    }

    private static void requestBlood(Scanner sc) {
        System.out.print("Enter your blood type: ");
        String bloodType = sc.nextLine();
        System.out.print("Enter your location: ");
        String location = sc.nextLine();

        boolean matchFound = false;
        for (User user : users.values()) {
            if (user.isAvailable && user.bloodType.equalsIgnoreCase(bloodType)
                    && user.location.equalsIgnoreCase(location)) {
                System.out.println("Match Found!");
                System.out.println("Donor Details: " + user);
                user.isAvailable = false; // Mark donor as unavailable
                matchFound = true;
                break;
            }
        }

        if (!matchFound) {
            System.out.println("No matching donor found.");
        }
    }

    private static void viewAllUsers() {
        System.out.println("\n=== All Registered Users ===");
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (Map.Entry<String, User> entry : users.entrySet()) {
                System.out.println("ID: " + entry.getKey() + ", " + entry.getValue());
            }
        }
    }
}
