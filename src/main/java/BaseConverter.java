import java.io.*;
import java.util.Scanner;

/**
 * BaseConverter opens a data file, reads, converts numbers, prints
 * @version Thursday 11/18/2021
 * @author 24wilber
 */
public class BaseConverter {
    public BaseConverter() {

    }

    public int strToInt(String num, String fromBase) {
        return -1
    }
    public int intToStr(String num, String toBase) {
        return -1
    }

    /**
     * onpens the file stream, inputs data on line at a time, converts,
     * prints the result to the console window and writes data to the
     * output stream.
     */
    public void inputConvertPrintWrite() {
        // create
        try {
            File file = new File("values10");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}