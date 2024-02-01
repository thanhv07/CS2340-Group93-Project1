package CalendarShit;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class CourseCalendar {
    //number of items in the calendar
    private int size;
    private final static int INITIAL_CAPACITY = 5; //initial capacity of the backing array
    private Course[] calendar; //stores all Courses

    /**
     * Constructor.
     * Creates an empty calendar.
     */
    CourseCalendar() {
        calendar = new Course[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Constructor that takes in a set of courses.
     * Gets rid of any null-pointers.
     *
     * @param courses Courses to be populated into calendar.
     */
    CourseCalendar(Course[] courses) {
        this();
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] != null) add(courses[i]);
        }
    }

    /**
     * Adds the specified course
     *
     * @param data the specific data
     * @exception IllegalArgumentException if data is null
     */
    void add(Course data) {
        if (data == null) throw new IllegalArgumentException("Data is null.");
        if (size == calendar.length) {
            calendar = Arrays.copyOf(calendar, calendar.length * 2);
        }
        calendar[size] = data;
        size++;
    }

    /**
     * Removes the specified course.
     *
     * @param data course to be removed
     * @exception IllegalArgumentException if data is null
     * @exception NoSuchElementException if calendar is empty or if data is not in the list
     * @return removed element
     */
    Course remove(Course data) {
        if (data == null) throw new IllegalArgumentException("Trying to delete null data.");
        if (size == 0) throw new NoSuchElementException("Calendar is empty.");
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (data.getName().equals(calendar[i].getName())) index = i;
        }
        if (index == -1) throw new NoSuchElementException("Specified data not in the list.");
        Course out = calendar[index];
        for (int i = index; i < size - 1; i++) {
            calendar[i] = calendar[i + 1];
        }
        calendar[size] = null;
        size--;
        return out;
    }

    /**
     * Gets all courses in the calendar.
     *
     * @return array containing all courses.
     */
    Course[] getCourses() {
        Course[] out = new Course[size];
        for (int i = 0; i < size; i++) {
            out[i] = calendar[i];
        }
        return out;
    }

    /**
     * Gets the classes which occur on that day.
     *
     * @param date date which you're trying to get classes for
     * @return an array containing all the courses which occur on that day
     */
    Course[] getClassesOnDay(MyDate date) {
        Integer[] indices = new Integer[size];
        int coursesIndex = 0;

        for (int i = 0; i < size; i++) {
            if (calendar[i].hasClass(date)) {
                indices[coursesIndex] = i;
                coursesIndex++;
            }
        }

        Course[] courses = new Course[coursesIndex];
        for (int i = 0; i < indices.length; i++) {
            if (indices[i] == null) break;
            courses[i] = calendar[indices[i]];
        }

        return courses;
    }
}
