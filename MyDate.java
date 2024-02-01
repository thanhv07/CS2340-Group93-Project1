package CalendarShit;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A wrapper class of java.util.Calendar.
 * It represents a date and only provides necessary methods.
 * Only responsible for date (not the time in a day).
 */
public class MyDate implements Comparable<MyDate>, Serializable {

    // A SimpleDateFormat has parse() and format() methods which can help us to transform between a String and a Date.
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private Calendar calendar;

    /**
     * @param dateString a string in the format of MM/dd/yyyy
     */
    public MyDate(String dateString) {
        try {
            Date date = dateFormat.parse(dateString);
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        } catch (ParseException e) {
            throw new RuntimeException("invalid date format string. It should be MM/dd/yyyy");
        }
    }

    /**
     * Construct a MyDate instance using java.util.Date instance
     * @param date we will create a MyDate instance based on its information
     */
    public MyDate(Date date) {
        this(dateFormat.format(date));
    }

    /**
     * January is 1. September is 12.
     *
     * @return month
     */
    public int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * The first day is 1. 30th day is 30.
     *
     * @return the day in a month
     */
    public int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 2010 is 2010. 2023 is 2023.
     *
     * @return year
     */
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Sunday is 0. Monday is 1. Saturday is 6.
     *
     * @return the day in a week.
     */
    public int getDayOfWeek() {
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * @param date2 the object to be compared.
     * @return negative if this date is before date2,
     * positive if this date is after date2,
     * 0 if the two dates are the same
     */
    @Override
    public int compareTo(MyDate date2) {
        long result = calendar.getTimeInMillis() - date2.calendar.getTimeInMillis();
        if (result > 0) {
            return 1;
        } else if (result == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Override of equals()
     * @param obj another object to compare
     * @return true if obj is a MyDate and represents the same day as this.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyDate)) {
            return false;
        }
        return this.compareTo((MyDate) obj) == 0;
    }

    /**
     * it should return the same result as "this.comparedTo(date2) < 0".
     * May be easier to use.
     *
     * @param date2 the date to compare
     * @return if this date is before date2.
     */
    public boolean before(MyDate date2) {
        return calendar.getTime().before(date2.calendar.getTime());
    }

    /**
     * tomorrow!
     * @return a MyDate object that represents tomorrow of this date
     */
    public MyDate tomorrow(){
        Calendar clone = (Calendar) calendar.clone();
        clone.add(Calendar.DAY_OF_MONTH, 1);
        return new MyDate(clone.getTime());
    }

    /**
     * a week later!
     * @return a MyDate object that is seven days later.
     */
    public MyDate inOneWeek(){
        Calendar clone = (Calendar) calendar.clone();
        clone.add(Calendar.WEEK_OF_MONTH, 1);
        return new MyDate(clone.getTime());
    }

    /**
     * MM/dd/yyyy format string
     *
     * @return the string representation of the date.
     */
    @Override
    public String toString() {
        return dateFormat.format(calendar.getTime());
    }

    /**
     * return a equivalent Date representation
     * @return a equivalent Date representation
     */
    public Date toDate(){
        return calendar.getTime();
    }
}
