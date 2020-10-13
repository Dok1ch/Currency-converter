import org.junit.*;
import static org.junit.Assert.*;

public class UnitTestCurrency {

    @Test
    public void createCurrency() {
        new Currency("RUB", 1, "Российский рубль", "1");
    }

    @Test
    public void testGetCharCode() {
        System.out.println("getCharCode");
        Currency currency = new Currency("RUB", 1, "Российский рубль", "1");
        assertEquals("RUB", currency.getCharCode());
    }

    @Test
    public void testSetCharCode() {
        System.out.println("setCharCode");
        String charCode = "RUB";
        Currency currency = new Currency();
        currency.setCharCode(charCode);
        assertEquals(currency.getCharCode(), charCode);
    }

    @Test
    public void testGetNominal() {
        System.out.println("getNominal");
        Currency currency = new Currency("RUB", 1, "Российский рубль", "1");
        assertEquals(1, currency.getNominal());
    }

    @Test
    public void testSetNominal(){
        System.out.println("setNominal");
        int nominal = 1;
        Currency currency = new Currency();
        currency.setNominal(1);
        assertEquals(currency.getNominal(), nominal);
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        Currency currency = new Currency("RUB", 1, "Российский рубль", "1");
        assertEquals("Российский рубль", currency.getName());
    }

    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Российский рубль";
        Currency currency = new Currency();
        currency.setCharCode(name);
        assertEquals(currency.getCharCode(), name);
    }

    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Currency currency = new Currency("RUB", 1, "Российский рубль", "1");
        assertEquals("1", currency.getValue());
    }

    @Test
    public void testSetValue() {
        System.out.println("setValue");
        String value = "1";
        Currency currency = new Currency();
        currency.setCharCode(value);
        assertEquals(currency.getCharCode(), value);
    }
}
