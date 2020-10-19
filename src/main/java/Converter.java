import org.w3c.dom.NodeList;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Converter {

    private static final NodeList nodeList = DOMxmlReader.connectToCBR();
    private static String nameCurrencyFrom;
    private static String nameCurrencyTo;
    private static BigDecimal bigDecimalAmount;
    private static BigDecimal bigDecimalFrom;
    private static BigDecimal bigDecimalTo;
    private static int nominalFrom;
    private static int nominalTo;

    public static String convert() {
        try {
            BigDecimal convertibleCurrency = new BigDecimal(String.valueOf((((bigDecimalAmount.multiply(bigDecimalFrom)).divide(BigDecimal.valueOf(nominalFrom), 4, RoundingMode.HALF_DOWN).divide((bigDecimalTo.divide(BigDecimal.valueOf(nominalTo), 4, RoundingMode.HALF_DOWN)), 4, RoundingMode.HALF_DOWN)))));
            return convertibleCurrency.toString();
        } catch (NumberFormatException ignored) {

        }
        return "";
    }

    public static BigDecimal getBigDecimalAmount(JTextField valueTextFrom) {
        bigDecimalAmount = new BigDecimal(valueTextFrom.getText());
        return bigDecimalAmount;
    }

    public static String getNameCurrencyFrom(JComboBox<?> comboBoxFrom) {
        nameCurrencyFrom = (String) comboBoxFrom.getSelectedItem();
        return nameCurrencyFrom;
    }

    public static String getNameCurrencyTo(JComboBox<?> comboBoxTo) {
        nameCurrencyTo = (String) comboBoxTo.getSelectedItem();
        return nameCurrencyTo;
    }

    public static void getNominalFrom() {
        nominalFrom = DOMxmlReader.findNominal(nameCurrencyFrom, Objects.requireNonNull(DOMxmlReader.reader(nodeList)));
    }

    public static void getNominalTo() {
        nominalTo = DOMxmlReader.findNominal(nameCurrencyTo, (Objects.requireNonNull(DOMxmlReader.reader(nodeList))));
    }

    public static void getValueStringFrom() {

        String valueStringFrom = (Objects.requireNonNull(DOMxmlReader.findValue(nameCurrencyFrom, Objects.requireNonNull(DOMxmlReader.reader(nodeList))))).replace(",", ".");
        bigDecimalFrom = new BigDecimal(valueStringFrom);
    }

    public static void getValueStringTo() {

        String valueStringTo = (Objects.requireNonNull(DOMxmlReader.findValue(nameCurrencyTo, Objects.requireNonNull(DOMxmlReader.reader(nodeList))))).replace(",", ".");
        bigDecimalTo = new BigDecimal(valueStringTo);
    }

}
