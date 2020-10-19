import org.junit.Test;
import org.w3c.dom.NodeList;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class UnitTestConverter {

    @Test
    public void testGetBigDecimalAmount() {
        JTextField testField;
        testField = new JTextField("12345");
        BigDecimal testBigDecimal = new BigDecimal(12345);

        assertEquals(testBigDecimal, Converter.getBigDecimalAmount(testField));
    }

    @Test
    public void testGetNameCurrencyFrom() {

        DefaultComboBoxModel<String> testComboBoxModel = new DefaultComboBoxModel<>();
        String[] elements = new String[]{"Тестовое значение 1", "Тестовое значение 2"};
        for (String s : elements) testComboBoxModel.addElement(s);

        JComboBox<String> testComboBox = new JComboBox<>(testComboBoxModel);

        String testString = Converter.getNameCurrencyFrom(testComboBox);

        assertEquals("Тестовое значение 1", testString);

    }

    @Test

    public void testGetNameCurrencyTo() {
        String[] elements = new String[]{"Тестовое значение 2", "Тестовое значение 1"};
        DefaultComboBoxModel<String> testComboBoxModel = new DefaultComboBoxModel<>();
        for (String s : elements) testComboBoxModel.addElement(s);
        JComboBox<String> testComboBox = new JComboBox<>(testComboBoxModel);

        String testString = Converter.getNameCurrencyTo(testComboBox);

        assertEquals("Тестовое значение 2", testString);
    }

    @Test
    public void testGetNominal() {

        NodeList testNodeList = DOMxmlReader.connectToCBR();

        String nameCurrencyFrom = "Армянских драмов";

        int testNominal = DOMxmlReader.findNominal(nameCurrencyFrom, Objects.requireNonNull(DOMxmlReader.reader(testNodeList)));

        assertEquals(100, testNominal);
    }

    @Test
    public void testGetNominalTo() {

        NodeList testNodeList = DOMxmlReader.connectToCBR();

        String nameCurrencyTo = "Доллар США";

        int testNominal = DOMxmlReader.findNominal(nameCurrencyTo, Objects.requireNonNull(DOMxmlReader.reader(testNodeList)));

        assertEquals(1, testNominal);
    }
}
