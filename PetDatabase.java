//I certify, that this computer program submitted by me is all of my own work. Signed: Matthew Obwaya
package WeekOne.Program;

import java.util.Scanner;

public class PetDatabase {
    //instance variables
    static Pet[] pets = new Pet[100];
    static int petCount = 0;
    static Scanner s = new Scanner(System.in);
    //methods
    private static int getUserChoice(){
        System.out.printf("Pet Database Program%n%nWhat would you like to do?%n1) View all pets%n2) Add more pets%n3) Update an existing pet%n4) Remove an existing pet%n5) Search pets by name%n6) Search pets by age%n7) Exit program%nYour choice: ");
        return s.nextInt();
    }
    private static void addPets(){
        String name;
        int age ;
        while(true) {
            System.out.print("add pet (name, age): ");
            name = s.next();
            if(name.equals("done")){break;}
            age = s.nextInt();

            Pet newPet = new Pet(name, age);
            pets[petCount] = newPet;
            petCount++;
        }
        System.out.printf("%d pets added.%n%n", petCount);

    }
    private static void showAllPets(){
        printTableHeader();
        int id = 0;
        for(Pet p: pets ) {
            if(p == null){
                break;
            }else if(p.getName() == null){
                continue;
            }
            printTableRow(id, p.getName(), p.getAge());
            id++;
        }
        printTableFooter(petCount);
    }
    private static void updatePet(){
        showAllPets();

        System.out.print("Enter the pet ID to update: ");
        int id = s.nextInt();

        System.out.print("Enter new name and new age: ");
        String name = s.next();
        int age = s.nextInt();

        pets[id].setName(name);
        pets[id].setAge(age);
    }

    private static void deletePet(){
        System.out.print("Enter the pet ID to delete: ");
        int id = s.nextInt();
        System.out.printf("%s %d",pets[id].getName(), pets[id].getAge());
        pets[id].setName(null);
        petCount--;

    }
    private static void searchPetsByName(){
        System.out.print("Enter a name to search: ");
        String name = s.next();
        int id = 0;

        printTableHeader();
        for (Pet p: pets) {
            if(p == null){
                break;
            }else if(p.getName().equals(name)){
                printTableRow(id, p.getName(), p.getAge());
                id++;
            }
        }
        printTableFooter(id);

    }
    private static void searchPetsByAge(){
        System.out.print("Enter age to search: ");
        int age = s.nextInt();
        int id = 0;
        printTableHeader();
        for (Pet p: pets) {
            if (p == null) {
                break;
            } else if (p.getAge() == age) {
                printTableRow(id, p.getName(), p.getAge());
                id++;
            }
        }
        printTableFooter(id);
    }

    private static void printTableHeader(){
        System.out.printf("+----------------------+%n| ID | NAME     | AGE  |%n+----------------------+%n");

    }

    private static void printTableRow(int id, String name, int age){
        System.out.printf("|%3s | %-9s|%5d |%n",id, name, age);
    }

    private static void printTableFooter(int rowCount){
        System.out.printf("+----------------------+%n");
        System.out.printf("%d rows in set.%n%n", rowCount);


    }
    //main
    public static void main(String[] args) {
        int x = 0;
        while(x != 7){
            switch (x = getUserChoice()) {
                case 1 -> showAllPets();
                case 2 -> addPets();
                case 3 -> updatePet();
                case 4 -> deletePet();
                case 5 -> searchPetsByName();
                case 6 -> searchPetsByAge();
            }
        }
        System.out.println("Goodbye!");
    }
}
//dataclass
class Pet {
    private String name;
    private int age;
    Pet(String name, int age){
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

