import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * BaseConverter opens a data file, reads, converts numbers, prints
 * @version Thursday 11/18/2021
 * @author 24wilber
 * @extra a thing that does some stuff
 *        and some other stuff
 */
public class BaseConverter {
    public BaseConverter() {

    }
    static List<Character> baseChars = List.of('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F');
    /**
     * @param num the number from which to convert to a decimal value
     * @param fromBase the base that num is in
     * @return the num in decimal form
     */
    public int strToInt(String num, String fromBase) {
        return strToInt(num, Integer.getInteger(fromBase));
    }
    /**
     * @param num the number from which to convert to a decimal value
     * @param fromBase the base that num is in
     * @return the num in decimal form
     */
    public int strToInt(String num, Integer fromBase) {
        int count = 0;
        for (int i = 0; i < num.length(); i++) {
            int index=((i-num.length())*-1)-1;
            count += baseChars.indexOf(num.charAt(index)) * ( (int) Math.pow(fromBase,i) );
            System.out.println(i);
            System.out.println(index);
            System.out.println(baseChars.indexOf(num.charAt(index)));
            System.out.println(fromBase);
            System.out.println(baseChars.indexOf(num.charAt(index)) * ( (int) Math.pow(fromBase,i) ));
            System.out.println();
        }
        return count;
    }

    /**
     * @param num unconverted number
     * @param toBase the base to convert the number to
     * @return the converted number
     */
    public int intToStr(int num, int toBase) {
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
        BaseConverter app = new BaseConverter();
        app.inputConvertPrintWrite();
    }
}