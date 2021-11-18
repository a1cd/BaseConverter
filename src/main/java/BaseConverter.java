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
//        System.out.println('"'+fromBase+'"');
//        System.out.println(Integer.parseInt(fromBase.strip()));
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
            int index = (num.length()-1)-i;
            count += (baseChars.indexOf(num.charAt(index)) * ( (int) Math.pow(fromBase,i) ));
        }
//        System.out.println("count = " + count);
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

    @SuppressWarnings("RedundantSuppression") // for BaseConverter.java#59:11
    public String intToStr(int num, int toBase) {
        int currentTotal = num;
        String out = "";
        int neededChars = 0;
        //noinspection StatementWithEmptyBody
        while (Math.pow(toBase,neededChars+1)<num) {neededChars++;}
//        System.out.println("needed chars =>> " + neededChars);
        for (int cChar = neededChars; cChar >= 0; cChar--) {
//            System.out.println("cChar =>> " + cChar);
            for (int i = toBase; i >= 0; i--) {
                if (i*Math.pow(toBase, cChar) > currentTotal) continue;
                currentTotal -= i*((int) Math.pow(toBase, cChar));
//                System.out.println("i =>> " + i);
//                System.out.println("eval value -<< " + (i*Math.pow(toBase, cChar)));
//                System.out.println("Current BaseChar -<< " + baseChars.get(i));
                //noinspection StringConcatenationInLoop
                out = out + baseChars.get(i);
                break;
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
        File fileIn = new File("./src/main/resources/values10.dat"); // just a convienence
        File fileOut = new File("./src/main/resources/converted.dat"); // correct file
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
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BaseConverter app = new BaseConverter();
        app.inputConvertPrintWrite();
    }
}