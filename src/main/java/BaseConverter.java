import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
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
        return -1;
    }
    public int intToStr(String num, String toBase) {
        return -1;
    }

    /**
     * onpens the file stream, inputs data on line at a time, converts,
     * prints the result to the console window and writes data to the
     * output stream.
     */
    public void inputConvertPrintWrite() {
        Scanner scanner;
        // create
        try {
            File file = new File("./src/main/resources/values10.dat");
               scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BaseConverter baseConverter = new BaseConverter();
        baseConverter.inputConvertPrintWrite();
    }
}