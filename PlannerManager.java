/* Getty Testa
* 115217416
* Recitation 01
*/

import java.util.Scanner;

public class PlannerManager {
    /**
     * A menu application for users to edit a planner. Any erroneous input
     * simply cancels the current action.
     * 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        boolean systemIsTerminated = false; // Checks whether the user quit
        Planner userPlanner = new Planner();
        Planner backupPlanner = null;
        Scanner inputScanner = new Scanner(System.in);

        while (!systemIsTerminated) {
            System.out.println("(A) Add Course\n(G) Get Course\n(R) Remove Cou"
                    + "rse\n(P) Print Courses in Planner\n(F) Filter by Department C"
                    + "ode\n(L) Look For Course\n(S) Size\n(B) Backup\n(PB) Print Co"
                    + "urses in Backup\n(RB) Revert to Backup\n(Q) Quit");
            System.out.print("Enter a selection: ");
            String userSelection = inputScanner.nextLine();
            userSelection = userSelection.toLowerCase();
            if (userSelection.equals("a")) {
                Course newCourse = new Course();

                System.out.print("Enter course name: ");
                userSelection = inputScanner.nextLine();
                newCourse.setName(userSelection);

                System.out.print("Enter department: ");
                userSelection = inputScanner.nextLine();
                if (userSelection.matches("[a-zA-Z]+") && userSelection.length() == 3) {
                    newCourse.setDepartment(userSelection);
                } else {
                    System.out.println("ERROR: Invalid department\n");
                    continue;
                }

                System.out.print("Enter course code: ");
                userSelection = inputScanner.nextLine();
                if (userSelection.matches("[0-9]+")) {
                    newCourse.setCode(Integer.valueOf(userSelection));
                } else {
                    System.out.println("ERROR: Invalid course code\n");
                    continue;
                }

                System.out.print("Enter course section: ");
                userSelection = inputScanner.nextLine();
                if (userSelection.matches("[0-9]+")) {
                    newCourse.setSection((byte) Integer.valueOf(userSelection).intValue());
                } else {
                    System.out.println("ERROR: Invalid course section\n");
                    continue;
                }

                System.out.print("Enter instructor: ");
                userSelection = inputScanner.nextLine();
                newCourse.setInstructor(userSelection);

                System.out.print("Enter position: ");
                userSelection = inputScanner.nextLine();
                if (userSelection.matches("[0-9]+")) {
                    userPlanner.addCourse(newCourse, Integer.valueOf(userSelection));
                } else {
                    System.out.println("ERROR: Invalid position\n");
                    continue;
                }
            } else if (userSelection.equals("g")) {
                System.out.print("Enter position: ");
                userSelection = inputScanner.nextLine();
                if (userSelection.matches("[0-9]+")) {
                    int integerPosition = Integer.valueOf(userSelection);
                    Course newCourse = userPlanner.getCourse(integerPosition);
                    if (newCourse != null) {
                        Course.printHeading();
                        System.out.print(newCourse.toString(integerPosition));
                    }
                } else {
                    System.out.println("ERROR: Invalid position\n");
                    continue;
                }
            } else if (userSelection.equals("r")) {
                System.out.print("Enter position: ");
                userSelection = inputScanner.nextLine();
                if (userSelection.matches("[0-9]+")) {
                    userPlanner.removeCourse(Integer.valueOf(userSelection));
                } else {
                    System.out.println("ERROR: Invalid position.");
                }
            } else if (userSelection.equals("p")) {
                userPlanner.printAllCourses();
            } else if (userSelection.equals("f")) {
                System.out.println("Enter a department code: ");
                userSelection = inputScanner.nextLine();
                Planner.filter(userPlanner, userSelection);
            } else if (userSelection.equals("l")) {
                Course newCourse = new Course();

                System.out.print("Enter course name: ");
                userSelection = inputScanner.nextLine();
                newCourse.setName(userSelection);

                System.out.print("Enter department: ");
                userSelection = inputScanner.nextLine();
                if (userSelection.matches("[a-zA-Z]+") && userSelection.length() == 3) {
                    newCourse.setDepartment(userSelection);
                } else {
                    System.out.println("ERROR: Invalid department\n");
                    continue;
                }

                System.out.print("Enter course code: ");
                userSelection = inputScanner.nextLine();
                if (userSelection.matches("[0-9]+")) {
                    newCourse.setCode(Integer.valueOf(userSelection));
                } else {
                    System.out.println("ERROR: Invalid course code\n");
                    continue;
                }

                System.out.print("Enter course section: ");
                userSelection = inputScanner.nextLine();
                if (userSelection.matches("[0-9]+")) {
                    newCourse.setSection((byte) Integer.valueOf(userSelection).intValue());
                } else {
                    System.out.println("ERROR: Invalid course section\n");
                    continue;
                }

                System.out.print("Enter instructor: ");
                userSelection = inputScanner.nextLine();
                newCourse.setInstructor(userSelection);

                if (!userPlanner.exists(newCourse)) {
                    System.out.println("Course not found in planner");
                }
            } else if (userSelection.equals("s")) {
                System.out.println(userPlanner.size());
            } else if (userSelection.equals("b")) {
                backupPlanner = (Planner) userPlanner.clone();
                System.out.println("Created a backup of the current planner.");
            } else if (userSelection.equals("pb")) {
                if (backupPlanner != null) {
                    backupPlanner.printAllCourses();
                } else {
                    System.out.println("ERROR: No backup planner to print\n");
                }
            } else if (userSelection.equals("rb")) {
                if (backupPlanner != null) {
                    userPlanner = (Planner) backupPlanner.clone();
                    System.out.println("Planner successfully reverted to the"
                            + "backup copy.");
                } else {
                    System.out.println("ERROR: No backup planner to restore\n");
                }
            } else if (userSelection.equals("q")) {
                System.out.println("Program terminating successfully...");
                inputScanner.close();
                systemIsTerminated = true;
            } else {
                System.out.println("ERROR: Invalid selection");
            }
            System.out.print("\n");
        }
    }
}
