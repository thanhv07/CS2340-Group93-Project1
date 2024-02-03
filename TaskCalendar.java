package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Simulates a user's calendar. Populated with tasks, assignments, and exams.
 *
 * Author: Froilan Trix Sunga
 * Version: 11.0.20
 */
public class TaskCalendar {
    private int size; //number of items in calendar
    private final static int INITIAL_CAPACITY = 5; //initial capacity of the backing array
    private Task[] calendar; //stores all Courses

    /**
     * Creates an empty TaskCalendar.
     */
    TaskCalendar() {
        calendar = new Task[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Constructor that takes in a set of tasks.
     * Gets rid of any null-pointers.
     *
     * @param tasks Tasks to be populated into calendar.
     */
    TaskCalendar(Task[] tasks) {
        this();
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) add(tasks[i]);
        }
    }

    /**
     * Adds a piece of data.
     *
     * @param data
     */
    void add(Task data) {
        if (size == calendar.length) {
            calendar = Arrays.copyOf(calendar, calendar.length * 2);
        }
        calendar[size] = data;
        size++;
    }

    /**
     * Removes data from backing array.
     *
     * @param data data to be removed. (maybe remove by name instead?)
     * @exception IllegalArgumentException if data is null
     * @exception NoSuchElementException if data does not exist or if calendar is empty.
     * @return data removed
     */
    Task remove(Task data) {
        if (data == null) throw new IllegalArgumentException("Trying to delete null data.");
        if (size == 0) throw new NoSuchElementException("Empty calendar.");
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (data.getName().equals(calendar[i].getName())) index = i; //should be replaced by proper method
        }
        if (index == -1) throw new NoSuchElementException("No such data in the list.");
        Task out = calendar[index];
        for (int i = index; i < size - 1; i++) {
            calendar[i] = calendar[i + 1];
        }
        calendar[size] = null;
        size--;
        return out;
    }

    /**
     * Gets all tasks in the calendar.
     *
     * @return an array with all tasks
     */
    Task[] getTasks() {
        Task[] out = new Task[size];
        for (int i = 0; i < size; i++) {
            out[i] = calendar[i];
        }
        return out;
    }

    /**
     * Returns all tasks in the form of their toString() methods
     * in an ArrayList<String> of the appropriate size.
     *
     * @return ArrayList w/all tasks in their toString() forms.
     */
    ArrayList<String> toStringArrayList() {
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            out.add(calendar[i].toString());
        }
        return out;
    }

    /**
     * Sorts by date, ascending.
     */
    void sortByDate() {
        if (size == 0) throw new NoSuchElementException("Calendar is empty.");
        int end = size - 1;
        while (end != 0) {
            for (int i = 0; i < end; i++) {
                if (calendar[i].getDate().compareTo(calendar[i+1].getDate()) > 0) {
                    swap(i, i + 1);
                }
            }
            end--;
        }
    }

    /**
     * Sorts tasks in calendar by completion.
     * Incomplete tasks receive priority, complete tasks go in the back.
     */
    void sortByCompletion() {
        Task[] incomplete = new Task[size];
        Task[] complete = new Task[size];
        int indexIncomplete = 0, indexComplete = 0;
        for (int i = 0; i < size; i++) {
            if (calendar[i].completed) complete[indexComplete++] = calendar[i];
            else incomplete[indexIncomplete++] = calendar[i];
        }
        for (int i = 0; i < indexIncomplete; i++) {
            calendar[i] = incomplete[i];
        }
        for (int i = indexIncomplete; i < size; i++) {
            calendar[i] = complete[i - indexComplete];
        }
    }

    /**
     * Helper method. Swaps two entries in the backing array.
     *
     * @param i1 index of first object
     * @param i2 index of second object
     */
    private void swap(int i1, int i2) {
        Task temp = calendar[i1];
        calendar[i1] = calendar[i2];
        calendar[i2] = temp;
    }
}
