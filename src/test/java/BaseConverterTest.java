import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BaseConverterTest {
    BaseConverter bc = new BaseConverter();
    @Test
    void strToIntStringConvienience() {
        BaseConverter bc = new BaseConverter();
//        assertEquals(164,bc.strToInt("A4","16"));
    }

    @Test
    void strToInt() {
        assertEquals(164,bc.strToInt("A4","16"));
        assertEquals(17,bc.strToInt("11","16"));
        assertEquals(198,bc.strToInt("C6","16"));
        assertEquals(248,bc.strToInt("F8","16"));
        assertEquals(255,bc.strToInt("FF","16"));
    }

    @Test
    void intToStr() {

    }

    @SuppressWarnings("StringConcatenationInLoop") // otherwise i cannot commit
    @Test
    void inputConvertPrintWrite() {
        try {
            File output = new File("src/test/resources/testResults30.dat");
            bc.inputConvertPrintWrite(new File("src/test/resources/values30.dat"), output);
            Scanner outScanner = new Scanner(output);
            String fullText = "";
            while (outScanner.hasNextLine()) {
                fullText += outScanner.nextLine();
                fullText += "\n";
            }
            outScanner.close();

            Scanner answerScanner = new Scanner(new File("src/test/resources/converted30.dat"));
            String answerKey = "";
            while (answerScanner.hasNextLine()) {
                answerKey += answerScanner.nextLine();
                answerKey += "\n";
            }
            answerScanner.close();

            System.out.println("\n\n\n\nnow\n\n\n\n");
            System.out.println(answerKey);

            assertEquals(answerKey, fullText);
        } catch (Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    void main() {
    }
}