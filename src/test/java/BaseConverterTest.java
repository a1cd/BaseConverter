import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseConverterTest {
    @Test
    void strToIntStringConvienience() {
        BaseConverter bc = new BaseConverter();
//        assertEquals(164,bc.strToInt("A4","16"));
    }

    @Test
    void strToInt() {
        BaseConverter bc = new BaseConverter();
        assertEquals(164,bc.strToInt("A4",16));
        assertEquals(17,bc.strToInt("11",16));
        assertEquals(198,bc.strToInt("C6",16));
        assertEquals(248,bc.strToInt("F8",16));
        assertEquals(255,bc.strToInt("FF",16));
    }

    @Test
    void intToStr() {
    }

    @Test
    void inputConvertPrintWrite() {
    }

    @Test
    void main() {
    }
}