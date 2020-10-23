/*import org.junit.Test;*/
import org.junit.jupiter.api.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;*/
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestDOMxmlReader {

    @Test
    public void testConnectionToCBR() {

        NodeList testNodeList = DOMxmlReader.connectToCBR();
        assert testNodeList != null;
        assertEquals(34, testNodeList.getLength());

    }

    @Test
    public void testReader() {

        NodeList testNodeList = DOMxmlReader.connectToCBR();
        List<Currency> testList = DOMxmlReader.reader(testNodeList);
        assert testNodeList != null;
        assert testList != null;
        assertFalse(testList.isEmpty());

    }

    @Test
    public void testFindNominal() {

        List<Currency> testList = new ArrayList<>();

        String nameRub = "Российский рубль";
        String nameHuf = "Венгерский форинт";
        Currency currencyRub = new Currency("RUB", 1, nameRub, "1");
        Currency currencyAud = new Currency("AUD", 100, nameHuf, "25,0295");
        testList.add(currencyRub);
        testList.add(currencyAud);

        int checkRub = DOMxmlReader.findNominal(nameRub, testList);
        int checkHuf = DOMxmlReader.findNominal(nameHuf, testList);

        assertEquals(1, checkRub);
        assertEquals(100, checkHuf);

    }

    @Test
    public void testFindValue() {

        List<Currency> testList = new ArrayList<>();

        String nameRub = "Российский рубль";
        String nameHuf = "Венгерский форинт";
        Currency currencyRub = new Currency("RUB", 1, nameRub, "1");
        Currency currencyAud = new Currency("AUD", 100, nameHuf, "25,0295");
        testList.add(currencyRub);
        testList.add(currencyAud);

        String checkRub = DOMxmlReader.findValue(nameRub, testList);
        String checkHuf = DOMxmlReader.findValue(nameHuf, testList);

        assertEquals("1", checkRub);
        assertEquals("25,0295", checkHuf);
    }

    @Test
    public void testGetCurrency() {

        NodeList testNodeList = DOMxmlReader.connectToCBR();
        assert testNodeList != null;

        Currency currencyAUD = DOMxmlReader.getCurrency(testNodeList.item(0));

        Currency testCurrency = new Currency("AUD", 1, "Австралийский доллар", "55");
        currencyAUD.setValue("55");

        assertEquals(currencyAUD.getCharCode(), testCurrency.getCharCode());
        assertEquals(currencyAUD.getNominal(), testCurrency.getNominal());
        assertEquals(currencyAUD.getName(), testCurrency.getName());
        assertEquals(currencyAUD.getValue(), testCurrency.getValue());

    }

    @Test
    public void testGetTagValue() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        NodeList testNodeList = null;
        Node firstNode = null;

        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new URL("http://www.cbr.ru/scripts/XML_daily.asp").openStream());
            document.getDocumentElement().normalize();

            testNodeList = document.getElementsByTagName("Valute");

        } catch (Exception ignored) {

        }

        assert testNodeList != null;

        if (testNodeList.item(1).getNodeType() == Node.ELEMENT_NODE) {

            String tag = "CharCode";
            testNodeList = document.getElementsByTagName(tag).item(0).getChildNodes();
            firstNode = testNodeList.item(0);
        }

        Currency testCurrency = new Currency("AUD", 1, "Австралийский доллар", "55");

        assert firstNode != null;
        assertEquals(firstNode.getNodeValue(), testCurrency.getCharCode());

    }
}
