import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DOMxmlReader {

    public static NodeList connectToCBR() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document;

        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new URL("http://www.cbr.ru/scripts/XML_daily.asp").openStream());
            document.getDocumentElement().normalize();

            // XML полностью загружен в память
            // в виде объекта Document

            NodeList nodeList;
            nodeList = document.getElementsByTagName("Valute");

            return nodeList;
        } catch (Exception ignored) {

        }

        return null;
    }

    public static List<Currency> reader(NodeList nodeList) {

        try {
            // создадим из него список объектов Currency
            List<Currency> currencyList = new ArrayList<>();

            Currency rub = new Currency("RUB", 1, "Российский рубль", "1");
            currencyList.add(rub);
            for (int i = 0; i < nodeList.getLength(); i++) {
                currencyList.add(getCurrency(nodeList.item(i)));
            }

            return currencyList;
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return null;
    }

    public static int findNominal(String name, List<Currency> currencies) {

        for (Currency currency : currencies) {
            if (currency.getName().equals(name)) {
                return currency.getNominal();
            }
        }
        return 0;
    }

    public static String findValue(String name, List<Currency> currencies) {

        for (Currency currency : currencies) {
            if (currency.getName().equals(name)) {
                return currency.getValue();
            }
        }
        return null;
    }

    public static Currency getCurrency(Node node) {
        Currency currency = new Currency();

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            currency.setCharCode(getTagValue("CharCode", element));
            currency.setNominal(Integer.parseInt(getTagValue("Nominal", element)));
            currency.setName(getTagValue("Name", element));
            currency.setValue(getTagValue("Value", element));
        }

        return currency;
    }

    // получаем значение элемента по указанному тегу
    public static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

}
