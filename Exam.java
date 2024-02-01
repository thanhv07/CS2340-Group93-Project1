package CalendarShit;

/**
 * Simulates an exam. Can also be used for quizzes & tests. Has the following attributes:
 *
 *
 * Author: Froilan Trix Sunga
 * Version: 11.0.20
 */
public class Exam extends Task {
    String location;

    /**
     * Constructor that takes in all information.
     *
     * @param name exam name
     * @param date date of exam
     * @param timeStart time exam starts
     * @param timeDue time exam ends
     */
    Exam(String name, String date, String timeStart, String timeDue,
         String location) {
        super(name, date, timeStart, timeDue);
        if (location == null) throw new IllegalArgumentException("Location is null.");

        this.location = location;
    }

    public String toString() {
        return String.format("Exam: %s |-| %s\n%s |-| Location: %s",
                name,
                date,
                timeDue.equals("xx:xx") ? "Start time: " + timeStart :
                        String.format("Time: %s to %s", timeStart, timeDue),
                location);
    }
}
