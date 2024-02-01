package CalendarShit;

/**
 * Simulates an assignment. Has the following attributes:
 * Assignment name, due date & time, and if it is complete.
 * <p>
 * Author: Froilan Trix Sunga
 * Version: 11.0.20
 */
public class Assignment extends Task {

    private Course course; // the associated course

    /**
     * Constructor. Takes in all elements except for complete.
     *
     * @param name    assignment name
     * @param date    assignment due date
     * @param timeDue time assignment is due
     */
    Assignment(String name, String date, String timeDue, Course course) {
        super(name, date, null, timeDue);
        this.course = course;
        completed = false;
    }

    /**
     * To-string method.
     *
     * @return description of assignment
     */
    public String toString() {
        return String.format("Assignment: %s |-| Course: %s\nDue: %s: %s",
                name, course.getName(), date, timeDue);
//                timeStart == null ? String.format("Time due: %s", timeDue)
//                        : String.format("Time: %s to %s", timeStart, timeDue));
    }
}
