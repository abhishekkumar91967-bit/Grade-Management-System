import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GradeManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<StudentGrade> students =
            new ArrayList<>();

    static final int SUBJECTS = 5;

    public static void main(String[] args) {

        loadData();

        while (true) {

            System.out.println("\n========== GRADE MANAGEMENT SYSTEM ==========");
            System.out.println("1. Add Student Marks");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Calculate Grades");
            System.out.println("5. Generate Report");
            System.out.println("6. Exit");

            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    calculateGrades();
                    break;

                case 5:
                    ReportGenerator.generateReport(students);
                    break;

                case 6:
                    saveData();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void addStudent() {

        System.out.print("Student Name: ");
        String name = sc.nextLine();

        double[] marks = new double[SUBJECTS];

        for (int i = 0; i < SUBJECTS; i++) {

            while (true) {

                System.out.print("Subject "
                        + (i + 1)
                        + " Marks: ");

                double mark = sc.nextDouble();

                if (mark >= 0 && mark <= 100) {

                    marks[i] = mark;
                    break;
                }

                System.out.println("Enter marks between 0 and 100.");
            }
        }

        sc.nextLine();

        students.add(new StudentGrade(name, marks));

        System.out.println("Student Added Successfully!");
    }

    static void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No Records Found.");
            return;
        }

        for (StudentGrade s : students) {

            double avg =
                    GradeCalculator.calculateAverage(
                            s.getMarks());

            System.out.println("\nName : "
                    + s.getName());

            System.out.println("Average : "
                    + avg);

            System.out.println("Grade : "
                    + GradeCalculator.getGrade(avg));
        }
    }

    static void searchStudent() {

        System.out.print("Enter Name: ");

        String name = sc.nextLine();

        for (StudentGrade s : students) {

            if (s.getName().equalsIgnoreCase(name)) {

                double avg =
                        GradeCalculator.calculateAverage(
                                s.getMarks());

                System.out.println("Found!");
                System.out.println("Average : " + avg);
                return;
            }
        }

        System.out.println("Student Not Found.");
    }

    static void calculateGrades() {

        for (StudentGrade s : students) {

            double avg =
                    GradeCalculator.calculateAverage(
                            s.getMarks());

            System.out.println(
                    s.getName()
                            + " -> "
                            + GradeCalculator.getGrade(avg));
        }
    }

    static void saveData() {

        try {

            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdir();
            }

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter("data/grades.txt"));

            for (StudentGrade s : students) {

                writer.print(s.getName());

                for (double m : s.getMarks()) {
                    writer.print("," + m);
                }

                writer.println();
            }

            writer.close();

        } catch (Exception e) {

            System.out.println("Error Saving Data");
        }
    }

    static void loadData() {

        try {

            File file =
                    new File("data/grades.txt");

            if (!file.exists())
                return;

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data =
                        line.split(",");

                String name = data[0];

                double[] marks =
                        new double[SUBJECTS];

                for (int i = 0; i < SUBJECTS; i++) {
                    marks[i] =
                            Double.parseDouble(
                                    data[i + 1]);
                }

                students.add(
                        new StudentGrade(
                                name,
                                marks));
            }

            br.close();

        } catch (Exception e) {

            System.out.println("Error Loading Data");
        }
    }
}
