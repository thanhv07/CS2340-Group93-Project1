package CalendarShit;

import java.sql.SQLOutput;
import java.util.Arrays;

public class Driver {

    public static void main(String[] args) {
        TaskCalendar calendar =  new TaskCalendar();
        CourseCalendar calendar2 = new CourseCalendar();

        MyDate startDate = new MyDate("09/01/2023");
        MyDate endDate = new MyDate("12/14/2023");

        MyTime startTime1 = new MyTime("11:00");
        MyTime endTime1 = new MyTime("12:15");
        MyTime startTime2 = new MyTime("12:30");
        MyTime endTime2 = new MyTime("13:20");

        CoursePeriod period1 = new CoursePeriod(startDate, endDate, startTime1, endTime1, 1);
        CoursePeriod period2 = new CoursePeriod(startDate, endDate, startTime1, endTime1, 3);
        CoursePeriod period3 = new CoursePeriod(startDate, endDate, startTime1, endTime1, 5);
        Course course1 = new Course("CS3333", "Alpha Beta", "23456", "College of Computing Room 100", period1, period2, period3);

        period1 = new CoursePeriod(startDate, endDate, startTime2, endTime2, 1);
        period2 = new CoursePeriod(startDate, endDate, startTime2, endTime2, 3);
        Course course2 = new Course("MATH3333", "Beta Alpha", "7890", "Howey L4", period1, period2);

        Exam e1 = new Exam("CS1332 Exam 1", "09/25/2023", "11:00","11:45","Howey L1");
        Exam e2 = new Exam("CS1332 Exam 2", "10/26/2023", "10:59", "12:01", "Howey L2");
        Assignment a1 = new Assignment("HW01", "09/27/2023", "11:59", course1);
        Assignment a2 = new Assignment("HW02", "10/30/2023", "11:59", course1);

        calendar.add(e1);
        calendar.add(e2);
        calendar.add(a1);
        calendar.add(a2);
        e1.complete();
        a1.complete();
        calendar.sortByDate();
        calendar.sortByCompletion();

        calendar2.add(course1);
        calendar2.add(course2);

        Task[] taskArray = calendar.getTasks();
        for (Task task : taskArray) {
            System.out.println(task + "\n");
        }

//        Course[] courseArray = calendar2.getClassesOnDay(new MyDate("10/20/2023"));
//        for (Course course : courseArray) {
//            System.out.println(course);
//        }
    }
}
