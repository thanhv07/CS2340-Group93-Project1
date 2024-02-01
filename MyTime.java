package CalendarShit;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A wrapper class of java.util.Date. and Calendar.
 * It is only responsible for the time in a day.
 * In other words, it only knows about hour/minute/second,
 * and knows nothing about year/month/day
 */
public class MyTime implements Comparable<MyTime>, Serializable {

    public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private Date date;
    private Calendar calendar;

    /**
     * @param time a string in the format of HH:mm:ss
     */
    public MyTime(String time) {
        try {
            date = timeFormat.parse(time);
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        } catch (ParseException e) {
            throw new RuntimeException("invalid time format string. It needs to be "+timeFormat);
        }
    }

    /**
     * Construct a MyTime instance using a java.util.Date instance
     * @param date an MyTime instance will be constructed based on its information.
     */
    public MyTime(Date date) {
        this(timeFormat.format(date));
    }

    /**
     * getter of hour. The return value is in 24-hour clock.
     * 03:45 -> 3;
     * 21:22 -> 21;
     * @return the hour component of the time
     */
    public int getHour(){
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * getter of minute
     * @return the minute component of the time
     */
    public int getMinute(){
        return calendar.get((Calendar.MINUTE));
    }

    /**
     * It should return the same result as "this.compareTo(time2) < 0".
     *
     * @param time2 the time to compare.
     * @return true if this time is before time2
     */
    public boolean before(MyTime time2) {
        return date.before(time2.date);
    }

    /**
     * compare two MyTime objects
     *
     * @param time2 the object to be compared.
     * @return negative if this time is before time2,
     * positive if this time is after time2,
     * 0 if two times are equal
     */
    @Override
    public int compareTo(MyTime time2) {
        return (int) (date.getTime() - time2.date.getTime());
    }

    /**
     * In the format of HH:mm:ss
     *
     * @return a string representation of the time
     */
    @Override
    public String toString() {
        return timeFormat.format(date);
    }

    /**
     * return a equivalent Date representation
     * @return a equivalent Date representation
     */
    public Date toDate(){
        return calendar.getTime();
    }
}
