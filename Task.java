import java.io.Serializable;

/**
 * Simulates a task. Has the following attributes:
 * Task name, date, start time (if wanted), and time due.
 * May want to add a class variable, indicating what class it is for.
 *
 * Author: Froilan Trix Sunga
 * Version: 11.0.20
 */
public class Task extends Activity implements Comparable<Task> {
    MyTime timeStart; //If null, then no specific time to start task
    MyTime timeDue; //time due
    boolean completed;

    /**
     * Constructor that takes in all information.
     *
     * @param name task name
     * @param date due date for task
     * @param timeStart time to start task (if wanted) *Only applicable if on same date, may want to refactor later
     * @param timeDue time task is due
     */
    Task(String name, String date,
         String timeStart, String timeDue) {
        super(name, date);
        if (timeDue == null) throw new IllegalArgumentException("timeDue is null.");
        if (timeStart != null) {
            this.timeStart = new MyTime(timeStart); //timeStart can be null; indicates no specific time window to start
        }
        this.timeDue = new MyTime(timeDue);
        completed = false;
    }

    /**
     * Marks the task as complete.
     */
    protected void complete() {
        completed = true;
    }

    /**
     * To-string method.
     *
     * @return description of the task.
     */
    public String toString() {
        return String.format("Task: %s\n%s", super.toString(),
                timeStart == null ? String.format("Time due: %s", timeDue)
                : String.format("Time: %s to %s", timeStart, timeDue));
    }

    /**
     * Compares one task to the other.
     *
     * @param o the object to be compared.
     * @return compare due date first. If due dates are the same, compare dueTime.
     */
    @Override
    public int compareTo(Task o) {
        int result = date.compareTo(o.date);
        if (result != 0) {
            return result;
        }else {
            return timeDue.compareTo(o.timeDue);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task task2) {
            return this.name.equals(task2.name) && this.date.equals(task2.date) && this.timeDue.equals(task2.timeDue);
        }else {
            return false;
        }
    }
}
