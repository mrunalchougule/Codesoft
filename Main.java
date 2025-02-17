import java.io.*;
import java.util.*;

// Student class to store student details
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void display() {
        System.out.println("Roll Number: " + rollNumber + ", Name: " + name + ", Grade: " + grade);
    }
}

// Student Management System class
class StudentManagementSystem {
    private List<Student> students;
    private static final String FILE_NAME = "students.dat";
    private Scanner scanner = new Scanner(System.in);

    public StudentManagementSystem() {
        students = loadStudents();
    }

    // Add a new student
    public void addStudent() {
        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Roll Number: ");
        int rollNumber = scanner.nextInt();
        System.out.print("Enter Grade: ");
        String grade = scanner.next();

        students.add(new Student(name, rollNumber, grade));
        saveStudents();
        System.out.println("Student added successfully!\n");
    }

    // Remove a student
    public void removeStudent() {
        System.out.print("Enter Roll Number to Remove: ");
        int rollNumber = scanner.nextInt();

        students.removeIf(student -> student.getRollNumber() == rollNumber);
        saveStudents();
        System.out.println("Student removed successfully!\n");
    }

    // Search for a student
    public void searchStudent() {
        System.out.print("Enter Roll Number to Search: ");
        int rollNumber = scanner.nextInt();

        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Student Found:");
                student.display();
                return;
            }
        }
        System.out.println("Student not found!\n");
    }

    // Edit student details
    public void editStudent() {
        System.out.print("Enter Roll Number to Edit: ");
        int rollNumber = scanner.nextInt();

        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.print("Enter New Name: ");
                student.setName(scanner.next());
                System.out.print("Enter New Grade: ");
                student.setGrade(scanner.next());
                saveStudents();
                System.out.println("Student details updated successfully!\n");
                return;
            }
        }
        System.out.println("Student not found!\n");
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!\n");
        } else {
            System.out.println("Student List:");
            for (Student student : students) {
                student.display();
            }
            System.out.println();
        }
    }

    // Save students to file
    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving students!");
        }
    }

    // Load students from file
    @SuppressWarnings("unchecked")
    private List<Student> loadStudents() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading students!");
            return new ArrayList<>();
        }
    }

    // Main menu
    public void menu() {
        while (true) {
            System.out.println("1. Add Student\n2. Remove Student\n3. Edit Student\n4. Search Student\n5. Display All Students\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: removeStudent(); break;
                case 3: editStudent(); break;
                case 4: searchStudent(); break;
                case 5: displayAllStudents(); break;
                case 6: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice! Try again.\n");
            }
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.menu();
    }
}
