import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 * BaseConverter opens a data file, reads, converts numbers, prints
 * @version Thursday 11/18/2021
 * @author 24wilber
 */
public class BaseConverter {
    /**
     * the base converter constructor
     */
    public BaseConverter() {

    }
    static List<Character> baseChars = List.of('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F');
    /**
     * converts string number in any base from 2 to 16 to a base 10 int
     *
     * this is a convenience method
     * @param num the number from which to convert to a decimal value
     * @param fromBase the base that num is in
     * @return the num in decimal form
     */
    public int strToInt(String num, String fromBase) {
//        System.out.println('"'+fromBase+'"');
//        System.out.println(Integer.parseInt(fromBase.strip()));
        return strToInt(num, Integer.parseInt(fromBase));
    }
    /**
     * converts string number in any base from 2 to 16 to a base 10 int
     *
     * this is a convenience method
     * @param num the number from which to convert to a decimal value
     * @param fromBase the base that num is in
     * @return the num in decimal form
     */
    public int strToInt(String num, Integer fromBase) {
        int count = 0;
        for (int i = 0; i < num.length(); i++) {
            int index = (num.length()-1)-i;
            count += (baseChars.indexOf(num.charAt(index)) * ( (int) Math.pow(fromBase,i) ));
        }
//        System.out.println("count = " + count);
        return count;
    }

    /**
     * convert int to string
     * @param num unconverted number
     * @param toBase the base to convert the number to
     * @return the converted number
     */
    public String intToStr(int num, String toBase) {
        return intToStr(num, Integer.parseInt(toBase));
    }

    /**
     * convert int in base 10 to a base from 2 to 16 and returning it as a string
     * @param num unconverted number
     * @param toBase the base to convert the number to
     * @return the converted number
     */
    public String intToStr(int num, int toBase) {
        if (num == 0) return "0"; // return 0 if it is 0
        int currentTotal = num; // i just dont like modifying argument variables
        String out = "";
        // the following is basically a copy of the code on the screen with slight modifications
        while (currentTotal > 0) {
            //noinspection StringConcatenationInLoop
            out = baseChars.get(currentTotal % toBase) + out;
            currentTotal /= toBase;
        }
        return out;
    }
    // this was my old code. I did not know how much it was alta vista style.
    /*
    public String intToStr(int num, int toBase) {
        int currentTotal = num;
        String out = "";
        int neededChars = 0;
        while (Math.pow(toBase,neededChars+1)<num) {neededChars++;}
        for (int cChar = neededChars; cChar >= 0; cChar--) {
            for (int i = toBase; i >= 0; i--) {
                if (i*Math.pow(toBase, cChar) > currentTotal) continue;
                currentTotal -= i*((int) Math.pow(toBase, cChar));
                //noinspection StringConcatenationInLoop
                out += baseChars.get(i);
                break;
            }
        }
        return out;
    }
    */

    /**
     * opens the file stream, inputs data on line at a time, converts,
     * prints the result to the console window and writes data to the
     * output stream.
     * this is a convenience method
     */
    public void inputConvertPrintWrite() {
        File fileIn = new File("./src/main/resources/values10.dat");
        File fileOut = new File("./src/main/resources/converted.dat");
        this.inputConvertPrintWrite(fileIn, fileOut);
    }
    /**
     * onpens the file stream, inputs data on line at a time, converts,
     * prints the result to the console window and writes data to the
     * output stream.
     * @param fileIn the input file
     * @param fileOut the output file
     */
    public void inputConvertPrintWrite(File fileIn, File fileOut) {
        Scanner scanner;
        // create
        try {
            scanner = new Scanner(fileIn);
            PrintWriter printWriter = new PrintWriter(fileOut);
            while(scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] currentArgs = currentLine.split("\t");
                int fromBase = Integer.parseInt(currentArgs[1]);
                int toBase = Integer.parseInt(currentArgs[2]);
                if (fromBase>16 || fromBase<2) {
                    System.out.println("invalid fromBase value");
                    continue;
                } else if (toBase > 16 || toBase < 2) {
                    System.out.println("invalid toBase value");
                    continue;
                }
                String converted = intToStr(strToInt(currentArgs[0], currentArgs[1]), currentArgs[2]);
                printWriter.println(currentArgs[0]+'\t'+currentArgs[1]+'\t'+converted+'\t'+currentArgs[2]);
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // may miss code coverage
        }
    }
    /**
     * the main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BaseConverter app = new BaseConverter();
        app.inputConvertPrintWrite();
    }
}
