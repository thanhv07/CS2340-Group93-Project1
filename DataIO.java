import java.io.*;

public class DataIO {

    // You need to create a "data" folder manually under current project.
    public static String activityArrayPath = "data" + File.separator + "activityArray.ser";
    public static String classArrayPath = "data" + File.separator + "classArray.ser";
    public static String taskArrayPath = "data" + File.separator + "taskArray.ser";
    public static String examArrayPath = "data" + File.separator + "examArray.ser";
    public static String assignmentArrayPath = "data" + File.separator + "assignmentArray.ser";

    /**
     * store an array in a file
     * @param arr the array to write
     * @param filePath the path to store the array
     */
    public static void serialize(Activity[] arr, String filePath) {
        try {
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(filePath));
            oss.writeObject(arr);
            oss.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * read an array from a file
     * @param filePath the file to read
     * @return the array stored in the file
     */
    public static Activity[] deserialize(String filePath) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            return (Activity[]) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
