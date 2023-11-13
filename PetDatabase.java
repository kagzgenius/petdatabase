//I certify, that this computer program submitted by me is all of my own work. Signed: Matthew Obwaya
package WeekOne.Program;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStream;

public class PetDatabase {
    //instance variables
    // add ArrayList in java below
    private static final ArrayList<Pet> pets = new ArrayList<Pet>();
    static int petCount = 0;
    static Scanner s = new Scanner(System.in);

    //methods
    private static int getUserChoice() {
        System.out.printf("What would you like to do?%n1) View all pets%n2) Add more pets%n3) Remove an existing pet%n4) Exit program%nYour choice: ");
        return s.nextInt();
    }

    private static void showAllPets() {
        printTableHeader();
        int id = 0;
        for (Pet p : pets) {
            if (p == null) {
                break;
            } else if (p.getName() == null) {
                continue;
            }
            printTableRow(id, p.getName(), p.getAge());
            id++;
        }
        printTableFooter(petCount);
    }

    private static void addPets() {
        String name;
        int age;

        while (true) {
            //throw error if number of pets in db is greater than 5
            if (petCount >= 5) {
                System.out.printf("Error: You can only have 5 pets." + "%n");
                break;
            }
            System.out.print("add pet (name, age): ");
            name = s.next();
            if (name.equals("done")) {
                break;
            }

            //throw error if token provided is empty
            if (Objects.equals(name, "")) {
                System.out.printf("Error: You must provide a name and age." + "%n");
                continue;
            }

            age = s.nextInt();

            // throw error if age > 20 or < 1
            if (age > 20 || age < 1) {
                System.out.printf("Error: Age must be between 1 and 20." + "%n");
                continue;
            }

            //throw error if trying to add more input
            if (s.hasNext()) {
                System.out.printf("Error: You can only input two values (name, age)." + "%n");
                continue;
            }

            Pet newPet = new Pet(name, age);
            pets.add(petCount, newPet);
            petCount++;
        }
        System.out.printf("%d pets added.%n%n", petCount);
    }

    private static void deletePet() {
        System.out.print("Enter the pet ID to delete: ");
        int id = s.nextInt();
        System.out.printf("%s %d", pets.get(id).getName(), pets.get(id).getAge());
        pets.get(id).setName(null);
        petCount--;

    }

    // Save text data to a file
    private static void saveData() {
        System.out.print("Enter the file name: ");
        String fileName = s.next();

        try {
            PrintStream out = new PrintStream(new FileOutputStream(fileName));
            for (Pet p : pets) {
                if (p == null) {
                    break;
                } else if (p.getName() == null) {
                    continue;
                }
                out.printf("%s %d%n", p.getName(), p.getAge());
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

    // Load text data from a file
    private static void loadData() {
        System.out.print("Enter the file name: ");
        String fileName = s.next();
        try {
            InputStream in = new FileInputStream(fileName);
            Scanner s = new Scanner(in);
            while (s.hasNext()) {
                String name = s.next();
                int age = s.nextInt();
                Pet newPet = new Pet(name, age);
                pets.add(petCount, newPet);
                petCount++;
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }


    private static void printTableHeader() {
        System.out.printf("+----------------------+%n| ID | NAME     | AGE  |%n+----------------------+%n");

    }

    private static void printTableRow(int id, String name, int age) {
        System.out.printf("|%3s | %-9s|%5d |%n", id, name, age);
    }

    private static void printTableFooter(int rowCount) {
        System.out.printf("+----------------------+%n");
        System.out.printf("%d rows in set.%n%n", rowCount);


    }

    //main
    public static void main(String[] args) {
        System.out.printf("Pet Database Program%n%n");
        int x = 0;
        while (x != 4) {
            switch (x = getUserChoice()) {
                case 1 -> showAllPets();
                case 2 -> addPets();
                case 3 -> deletePet();
            }
        }
        System.out.println("Goodbye!");
    }
}

//dataclass
class Pet {
    private String name;
    private int age;

    Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

