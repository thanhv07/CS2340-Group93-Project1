package CalendarShit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * One CoursePeriod object represents a meeting in a week.
 * If a course has meetings at 12:30 to 13:45 on Mon/Wed/Fri,
 * it needs to have three CoursePeriod:
 * new CoursePeriod(startDate, endDate, "12:30", "13:45", 1); i.e. "Mon: 12:30 - 13:45";
 * new CoursePeriod(startDate, endDate, "12:30", "13:45", 3); i.e. "Wed: 12:30 - 13:45";
 * new CoursePeriod(startDate, endDate, "12:30", "13:45", 5); i.e. "Fri: 12:30 - 13:45";
 */
public class CoursePeriod implements Comparable<CoursePeriod>, Serializable {

    public static String[] week = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //weekday strings array

    private MyDate startDate; // the first day of a semester
    private MyDate endDate; // the last day of a semester
    private int dayOfWeek; // The day of class. Sunday is 0; Monday is 1; Saturday is 6.
    private MyTime startTime; // the start time of the course
    private MyTime endTime;// the end time of the course

    /*
      A list containing all class dates for this period.
      For example, if startDate is 09/01/2023, endDate is 12/14/2023, and dayOfWeek is 1 (Monday),
      then the list will contain all mondays from 09/01/2023 to 12/14/2023.
     */
    private List<MyDate> dateList;

    /**
     *
     * @param startDate the first day of a semester
     * @param endDate the last day of a semester
     * @param startTime the start time for the course
     * @param endTime the end time for the course
     * @param dayOfWeek the course in on which day of a week
     */
    public CoursePeriod(MyDate startDate, MyDate endDate, MyTime startTime, MyTime endTime, int dayOfWeek) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
        dateListInit();
    }

    /**
     * Initialize dateList
     */
    private void dateListInit() {
        dateList = new ArrayList<>();

        MyDate currDate = startDate;
        while (currDate.getDayOfWeek() != dayOfWeek ){
            currDate = currDate.tomorrow();
        }

        while (currDate.compareTo(endDate) <= 0) {
            dateList.add(currDate);
            currDate = currDate.inOneWeek();
        }
    }

    /**
     * getter of dateList
     * @return dateList
     */
    public List<MyDate> getDateList() {
        return dateList;
    }

    /**
     * Test if there is class on a certain date
     * @param date the date to check
     * @return true if there is class on that date
     */
    public boolean hasClass(MyDate date) {
        return (date.getDayOfWeek() == dayOfWeek && date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0);
    }

    /**
     * getter of dayOfWeek
     * @return dayOfWeek
     */
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * getter of startTime
     * @return startTime
     */
    public MyTime getStartTime(){
        return startTime;
    }

    /**
     * getter of endTime
     * @return endTime
     */
    public MyTime getEndTime(){
        return endTime;
    }

    /**
     * override toString()
     * @return a string looking like "Wed: 12:30 - 13:45"
     */
    @Override
    public String toString() {
        return String.format("%s: %s - %s", week[dayOfWeek], startTime, endTime);
    }

    /**
     * compare two CoursePeriods.
     * @param period2 the object to be compared.
     * @return If two periods have different dayOfWeeks, the one with lower dayOfWeek is "smaller".
     * When the dayOfWeeks are the same, the one with earlier startTime is "smaller".
     * "Sun: 21:00 - 22:00".compareTo("Mon: 08:00 - 09:00") is negative.
     * "Mon: 12:00 - 13:00".compareTo("Mon: 08:00 - 09:00") is positive.
     */
    @Override
    public int compareTo(CoursePeriod period2) {
        if (dayOfWeek != period2.dayOfWeek) {
            return dayOfWeek - period2.dayOfWeek;
        }
        return startTime.compareTo(period2.startTime);
    }
}
