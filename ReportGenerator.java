import java.util.ArrayList;

public class ReportGenerator {

    public static void generateReport(ArrayList<StudentGrade> students) {

        if (students.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        double highest = 0;
        double lowest = 100;
        String topper = "";

        for (StudentGrade student : students) {

            double avg =
                    GradeCalculator.calculateAverage(
                            student.getMarks());

            if (avg > highest) {
                highest = avg;
                topper = student.getName();
            }

            if (avg < lowest) {
                lowest = avg;
            }
        }

        System.out.println("\n===== REPORT =====");
        System.out.println("Total Students: " + students.size());
        System.out.println("Top Performer : " + topper);
        System.out.println("Highest Average : " + highest);
        System.out.println("Lowest Average : " + lowest);
    }
}
