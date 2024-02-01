package CalendarShit;
import java.util.Arrays;

/**
 * Simulates a class with the following attributes:
 * Dates & times, days of the week they repeat on,
 * professor, class section, location & room number
 * <p>
 * Version: 11.0.20
 * Author: Froilan Trix Sunga
 */
public class Course extends Activity {

    private CoursePeriod[] periods; //All periods of the course.
    String instructor; //Instructor
    String section; //Section of class.
    String location; //Location of class.

    /**
     * Constructor.
     *
        example: If we want to create a course with:
            name: CS3333
            instructor: Alpha Beta
            section: 23456
            location: College of Computing Room 100
            periods: Mon/Wed/Fri 12:30 - 13:45
        We can do the following:

        // firstDay and lastDay should be two static course-independent variable.
        // They only change when a new semester begins.
        MyDate firstDay = new MyDate("09/01/2023");
        MyDate lastDay = new MyDate("12/14/2023");

        MyTime startTime = new MyTime("12:30"); // start and end time for the course
        MyTime endTime = new MyTime("13:45");

        CoursePeriod period1 = new CoursePeriod(firstDay, lastDay, startTime, endTime, 1);
        CoursePeriod period2 = new CoursePeriod(firstDay, lastDay, startTime, endTime, 3);
        CoursePeriod period3 = new CoursePeriod(firstDay, lastDay, startTime, endTime, 5);

        Course course = new Course("CS3333", "Alpha Beta", "23456", "College of Computing Room 100", period1, period2, period3);

     *
     * @param name       name of the course
     * @param instructor name of the instructor
     * @param section    section number/name
     * @param location   building and room number
     * @param periods    all CoursePeriods for this course. The number of periods of a course can vary.
     */
    public Course(String name, String instructor, String section, String location, CoursePeriod... periods) {
        super(name);
        Arrays.sort(periods); // Sort by dayOfWeek and time: monday is before wednesday; 08:00 is before 14:00
        if (instructor == null) throw new IllegalArgumentException("instructor is null");
        if (section == null) throw new IllegalArgumentException("section is null");
        if (location == null) throw new IllegalArgumentException("location is null");
        if (periods.length == 0) throw new IllegalArgumentException("periods is empty");
        this.periods = periods;
        this.instructor = instructor;
        this.section = section;
        this.location = location;
    }

    /**
     * test if the course has class on a date
     *
     * @param date the date to check
     * @return true if the course has class on that date,
     * false otherwise.
     */
    public boolean hasClass(MyDate date) {
        for (CoursePeriod period : periods) {
            if (period.hasClass(date)) {
                return true;
            }
        }
        return false;
    }

    /**
     * To-string method. *Need to work on conversion from 12-hour clock to 24-hour clock, and vice versa.
     *
     * @return Description of the class.
     */
    public String toString() {
        String timeStr = "";
        for (CoursePeriod period : periods) {
            timeStr += (period.toString() + "\n");
        }
        return String.format("Course: %s |-| Instructor: %s |-| Section: %s |-| Location: %s\n%s",
                name, instructor, section, location, timeStr);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Course)) {
            return false;
        }
        Course course2 = (Course) obj;
        return (name.equals(course2.name) && instructor.equals(course2.instructor) && section.equals(course2.section)
                && location.equals(course2.location) && Arrays.equals(periods, course2.periods));
    }
}
