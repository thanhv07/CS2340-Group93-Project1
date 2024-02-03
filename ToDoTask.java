/**
 * A simple subclass of Task
 * The only thing new is that a task has a "note". The note is the detailed explanation of the task.
 * A ToDoTask can look like:
 *      name: "Reading textbook"
 *      dueDate: "02/10/2024"
 *      dueTime: "13:43"
 *      note: "from page 65 to page 89"
 */
public class ToDoTask extends Task{
    private String note; // note of the task, can be null
    private String course; // name of its course (if it has), and be null. It is a String not a Course instance
    // because no further info about the course is needed.

    /**
     * Constructor without note and course
     * @param name name of task
     * @param dueDate due date of task
     * @param dueTime due time of task
     */
    public ToDoTask(String name, String dueDate, String dueTime){
        super(name, dueDate, null, dueTime);
    }

    /**
     * Constructor with note and course
     * @param name name of task
     * @param dueDate due date of task
     * @param dueTime due time of task
     * @param note note of the task
     * @param course the associated course of the task
     */
    public ToDoTask(String name, String dueDate, String dueTime, String note, String course){
        super(name, dueDate, null, dueTime);
        this.note = note;
        this.course = course;
    }

    public String getNote(){
        return note;
    }

    public String getCourse(){
        return course;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDoTask task2) {
            return super.equals(task2) && note.equals(task2.note);
        }else {
            return false;
        }
    }

    @Override
    public String toString() {
        String courseStr = "";
        if (course != null) {
            courseStr = " |-| Course: " + course;
        }
        String noteStr = "";
        if (note != null ) {
            noteStr = note + "\n";
        }
        return name + courseStr + "\n" + noteStr + "Due: " + date + ": " + timeDue;
    }
}
