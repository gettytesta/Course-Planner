/* Getty Testa
* 115217416
* Recitation 01
*/

public class Course {
    // Member variables
    private String name = "";
    private String department = "";
    private int code;
    private byte section;
    private String instructor = "";

    /**
     * Constructs an instance of a Course with empty fields.
     */
    public Course() {
    }

    /**
     * Constructs an instance of a Course.
     * 
     * @param n The name of the course.
     * @param d The 3 letter department code.
     * @param c The class code.
     * @param s The section of the class.
     * @param i The instructor.
     */
    public Course(String n, String d, int c, byte s, String i) {
        name = n;
        department = d;
        code = c;
        section = s;
        instructor = i;
    }

    /**
     * Returns the name of the Course.
     * 
     * @return the name of the Course
     */
    String getName() {
        return name;
    }

    /**
     * Changes the name of the Course.
     * 
     * @param n the new name for the Course
     */
    void setName(String n) {
        name = n;
    }

    /**
     * Returns the department of the Course.
     * 
     * @return the department of the Course
     */
    String getDepartment() {
        return department;
    }

    /**
     * Changes the department of the Course.
     * 
     * @param d the new department for the Course
     */
    void setDepartment(String d) {
        department = d;
    }

    /**
     * Returns the code of the Course.
     * 
     * @return the code of the Course
     */
    int getCode() {
        return code;
    }

    /**
     * Changes the code of the Course.
     * 
     * @param c the new code for the Course
     */
    void setCode(int c) {
        code = c;
    }

    /**
     * Returns the section of the Course.
     * 
     * @return the section of the Course
     */
    byte getSection() {
        return section;
    }

    /**
     * Changes the section of the Course.
     * 
     * @param s the new section for the Course
     */
    void setSection(byte s) {
        section = s;
    }

    /**
     * Returns the instructor of the Course.
     * 
     * @return the instructor of the Course
     */
    String getInstructor() {
        return instructor;
    }

    /**
     * Changes the instructor of the Course.
     * 
     * @param i the new instructor for the Course
     */
    void setInstructor(String i) {
        instructor = i;
    }

    /**
     * Returns a copy of the Course that called the function. Subsequent changes to
     * one Course will not affect the other. The return value should be typecasted
     * to a Course before usage.
     */
    public Object clone() {
        return new Course(name, department, code, section, instructor);
    }

    /**
     * Checks to see if two Courses are equal. A return value of true indicates
     * the two Courses are equal.
     * 
     * @param obj the object to be compared with the Course calling the function.
     * @return true if the two Courses are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            Course checkCourse = (Course) obj;
            if (checkCourse.getName().equals(name)
                    && checkCourse.getDepartment().equals(department)
                    && checkCourse.getCode() == code
                    && checkCourse.getSection() == section
                    && checkCourse.getInstructor().equals(instructor)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a String representation of the Course. Prints the position, name,
     * department, class code, class section, and instructor in a table.
     * 
     * @param position the position of the Course in the Planner.
     * @return the String representation of the Course.
     */
    public String toString(int position) {
        String tableOfCourses = "";
        tableOfCourses += String.format("%3d %-26s%-10s%5d%8d %s\n",
                position, name, department, code, section, instructor);
        return tableOfCourses;
    }

    /**
     * Prints out the heading for the list of Courses.
     */
    public static void printHeading() {
        System.out.println("No. Course Name               Department Code Sec"
          + "tion Instructor\n------------------------------------------"
          + "-------------------------------------");
    }
}