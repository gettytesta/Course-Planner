/* Getty Testa
* 115217416
* Recitation 01
*/

public class Planner {
    static int MAX_COURSES = 50;
    Course courses[] = new Course[MAX_COURSES];
    int size = 0; // Size of the Planner

    /**
     * Constructs an instance of the Planner with no Course objects in it.
     * Postcondition: This Planner has been initialized to an empty list of Courses.
     */
    public Planner() {
    }

    /**
     * Determines the number of courses currently in the list.
     * Preconditions: This Planner has been instantiated.
     * 
     * @return the number of Courses in this Planner
     */
    public int size() {
        return size;
    }

    /**
     * Adds a new Course to the planner.
     * Preconditions: This Course object has been instantiated and 1 ≤ position
     * ≤ items_currently_in_list + 1. The number of Course objects in this
     * Planner is less than MAX_COURSES.
     * Postconditions: The new Course is now listed in the correct preference
     * on the list. All Courses that were originally greater than or equal to
     * position are moved back one position. (e.g. If there are 5 Courses in
     * a Planner, positioned 1-5, and you insert a Course in position 4, the
     * new Course would be placed in position 4, the Course that was in
     * position 4 will be moved to position 5, and the Course that was in
     * position 5 will be moved to position 6.)
     * 
     * @param newCourse the new Course to be added to the Planner.
     * @param position  the position (preference) for the Course being added.
     * @throws IllegalArgumentException if position is not within the valid range.
     * @throws FullPlannerException     if there is no more room in the Planner.
     */
    public void addCourse(Course newCourse, int position) {
        try {
            if (position > this.size() + 1 || position < 1) {
                throw new IllegalArgumentException();
            } else if (this.size() >= MAX_COURSES) {
                throw new FullPlannerException();
            } else {
                // Below algorithm works for all cases, success dialogue goes first
                // to avoid a very long printf statement
                System.out.printf("%s %d.%02d successfully added to planner.\n",
                        newCourse.getDepartment(), newCourse.getCode(), (int) newCourse.getSection());

                // Course object to swap current course in array and newCourse
                Course swapCourse = newCourse;
                for (int i = 0; swapCourse != null; i++) {
                    swapCourse = courses[position - 1 + i];
                    courses[position - 1 + i] = newCourse;
                    newCourse = swapCourse;
                }
                size++;
            }
        } catch (IllegalArgumentException iae) {
            System.out.println("ERROR: Position is not within the valid range!");
        } catch (FullPlannerException fpe) {
            System.out.println("ERROR: Planner is full!");
        }
    }

    /**
     * Adds a new Course to the end of the Planner.
     * 
     * @param newCourse the new Course to be added to the Planner.
     * @throws FullPlannerException if there is no more room in the Planner.
     */
    public void addCourse(Course newCourse) {
        try {
            if (this.size() >= MAX_COURSES) {
                throw new FullPlannerException();
            } else {
                this.addCourse(newCourse, this.size() + 1);
            }
        } catch (FullPlannerException fpe) {
            System.out.println("ERROR: Planner is full!");
        }
    }

    /**
     * Removes the course at the desired position.
     * Preconditions: This Planner has been instantiated and 1 ≤ position ≤
     * items_currently_in_list.
     * Postconditions: The Course at the desired position has been removed.
     * All Courses that were originally greater than or equal to position are
     * moved backward one position. (e.g. If there are 5 Courses in a Planner,
     * positioned 1-5, and you remove the Course in position 4, the item that
     * was in position 5 will be moved to position 4.)
     * 
     * @param position the position in the Planner where the Course will be removed.
     * @throws IllegalArgumentException if position is not within the valid range.
     */
    public void removeCourse(int position) {
        try {
            if (position > this.size() || position < 1) {
                throw new IllegalArgumentException();
            } else {
                // Below algorithm works for all cases, success dialogue placed here
                // to avoid a very long printf statement
                System.out.printf("%s %d.%02d successfully removed from planner.\n",
                        this.courses[position - 1].getDepartment(),
                        this.courses[position - 1].getCode(),
                        this.courses[position - 1].getSection());

                if (position == 50) {
                    courses[49] = null;
                } else {
                    for (int i = position - 1; i < this.size(); i++) {
                        courses[i] = courses[i + 1];
                    }
                }
                size--;
            }
        } catch (IllegalArgumentException iae) {
            System.out.println("ERROR: Position is not within the valid range!");
        }
    }

    /**
     * Returns the course at the specified position.
     * Preconditions: The Planner object has been instantiated and
     * 1 ≤ position ≤ items_currenyly_in_list.
     * 
     * @param position the position of the Course to retrieve.
     * @return the Course at the specified position in the Planner.
     * @throws IllegalArgumentException if position is not within the valid range.
     */
    public Course getCourse(int position) {
        try {
            if (position > this.size() || position < 1) {
                throw new IllegalArgumentException();
            } else {
                return courses[position - 1];
            }
        } catch (IllegalArgumentException iae) {
            System.out.println("ERROR: Position is not within the valid range!");
            return null;
        }
    }

    /**
     * Prints all Courses that are within the specified department.
     * Preconditions: This Planner object has been instantiated.
     * Postconditions: Displays a neatly formatted table of each course
     * filtered from the Planner. Keep the preference numbers the same.
     * 
     * @param planner    the list of Courses to search within.
     * @param department the 3 letter department code to search for.
     */
    public static void filter(Planner planner, String department) {
        Course.printHeading();
        for (int i = 0; i < planner.size(); i++) {
            if (planner.courses[i].getDepartment().equals(department)) {
                System.out.print(planner.courses[i].toString(i + 1));
            }
        }
    }

    /**
     * Checks whether a certain Course alredy exists in the list.
     * Preconditions: This Planner and Course has both been instantiated.
     * 
     * @param course the Course we're looking for.
     * @return true if the planner contains the Course, false otherwise.
     */
    public boolean exists(Course course) {
        for (int i = 0; i < this.size(); i++) {
            if (courses[i].equals(course)) {
                System.out.printf("%s %d.%02d found in the planner at position %d.\n",
                        courses[i].getDepartment(), courses[i].getCode(),
                        courses[i].getSection(), i + 1);
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a copy of this Planner. Subsequent changes to the copy will not
     * affect the other.
     * Preconditions: This Planner object has been instantiated.
     * 
     * @return the copy of the original Planner.
     */
    public Object clone() {
        Planner clonedPlanner = new Planner();
        for (int i = 0; i < this.size(); i++) {
            clonedPlanner.addCourse((Course) courses[i].clone(), i + 1);
        }
        return clonedPlanner;
    }

    /**
     * Prints a neatly formatted table of each item in the list. Calls on the
     * toString() function in the Course class.
     * Preconditions: This Planner has been instantiated.
     * Postconditions: Displays a neatly formatted table of each course from the
     * Planner.
     */
    public void printAllCourses() {
        Course.printHeading();
        System.out.print(this.toString());
    }

    /**
     * Gets the String representation of this Planner object, which is a neatly
     * formatted table of each Course in the Planner on its own line. Calls on
     * the toString() function from the Course class.
     * 
     * @return the String representation of the Planner.
     */
    public String toString() {
        String tableOfCourses = "";
        for (int i = 0; i < this.size(); i++) {
            tableOfCourses += courses[i].toString(i + 1);
        }
        return tableOfCourses;
    }
}