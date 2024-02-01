package CalendarShit;
import java.io.Serializable;

/**
 * Simulates an event/activity.
 *
 * Version: 11.0.20
 * Author: Froilan Trix Sunga
 */
abstract public class Activity implements Serializable {
    String name; //Self-explanatory
    MyDate date; //Should be formatted as xx/xx/xxxx or UMTWRFS (Day codes)
                         //Potentially change to a class?

    /**
     * Constructor. Takes in
     *
     * @param name name of the activity
     * @param date date for the activity. formatted as "MM/dd/yyyy"
     */
    protected Activity(String name, String date) {
        if (name == null) throw new IllegalArgumentException("Name is null.");
        if (date == null) throw new IllegalArgumentException("Date is null.");
        this.name = name.trim();
        this.date = new MyDate(date);
    }

    /**
     * Only name constructor. It is used for some subclass which do not have a specific "date"
     * @param name name of the Activity
     */
    protected Activity(String name){
        if (name == null) throw new IllegalArgumentException("Name is null.");
        this.name = name.trim();
    }

    /**
     * No-args constructor. Don't think it is needed, but set up just in case.
     * Name is set to "n/a".
     * Date is set to "n/a".
     */
    protected Activity() {
        this("n/a", "n/a");
    }

    /**
     * Getter method for name.
     *
     * @return name
     */
    protected String getName() {
        return name;
    }

    /**
     * Getter method for date
     *
     * @return date
     */
    protected MyDate getDate() {
        return date;
    }

    /**
     * To-string method.
     *
     * @return description of the activity in the form of
     *         "name |-| date(s)/days"
     */
    @Override
    public String toString() {
        return String.format("%s |-| %s",
                name,
                date);
    }
}