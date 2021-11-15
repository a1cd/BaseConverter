import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        System.out.println('"'+fromBase+'"');
        System.out.println(Integer.parseInt(fromBase.strip()));
        return strToInt(num, Integer.parseInt(fromBase));
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
        }
        return count;
    }

    /**
     * @param num unconverted number
     * @param toBase the base to convert the number to
     * @return the converted number
     */
    public String intToStr(int num, String toBase) {
        return intToStr(num, Integer.parseInt(toBase));
    }
    public String intToStr(int num, int toBase) {
        int currentTotal = num;
        String out = "";
        int neededChars = 0;
        //noinspection StatementWithEmptyBody
        for (neededChars++; Math.pow(toBase,neededChars)>num;) {}
        for (int cChar = neededChars; cChar >= 0; cChar--) {
            for (int i = toBase; i*Math.pow(toBase,cChar) >= currentTotal; i--) {
                currentTotal=-i*((int) Math.pow(toBase, cChar));
                //noinspection StringConcatenationInLoop
                out = out + baseChars.get(i);
            }
        }
        return out;
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
            File fileIn = new File("./src/main/resources/values10.dat");
            File fileOut = new File("./src/main/resources/converted10.dat");
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
                printWriter.write(converted+'\t'+currentArgs[1]+'\t'+currentArgs[2]);
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