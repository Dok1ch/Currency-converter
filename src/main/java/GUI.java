import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class GUI extends JFrame {

    private final JComboBox<String> comboBoxFrom;
    private final JComboBox<String> comboBoxTo;
    private final JTextField valueTextFrom;
    private final JTextField valueTextTo;

    public String[] elements = new String[]{"Австралийский доллар", "Азербайджанский манат", "Фунт стерлингов Соединенного королевства", "Армянских драмов", "Белорусский рубль", "Болгарский лев", "Бразильский реал", "Венгерских форинтов", "Гонконгских долларов", "Датская крона", "Доллар США", "Евро", "Индийских рупий", "Казахстанских тенге", "Канадский доллар", "Киргизских сомов", "Китайский юань", "Молдавских леев", "Норвежских крон", "Польский злотый", "Румынский лей", "СДР (специальные права заимствования)", "Сингапурский доллар", "Таджикских сомони", "Турецкая лира", "Новый туркменский манат", "Узбекских сумов", "Украинских гривен", "Чешских крон", "Шведских крон", "Швейцарский франк", "Южноафриканских рэндов", "Вон Республики Корея", "Японских иен", "Российский рубль"};

    public GUI() {

        JLabel labelValueFrom;
        JLabel labelValueTo;
        JButton buttonSwap;
        DefaultComboBoxModel<String> comboBoxModelFrom;
        DefaultComboBoxModel<String> comboBoxModelTo;

        labelValueFrom = new JLabel("У меня есть:");
        labelValueTo = new JLabel("Хочу приобрести:");
        buttonSwap = new JButton();
        comboBoxModelFrom = new DefaultComboBoxModel<>();
        comboBoxModelTo = new DefaultComboBoxModel<>();

//        ImageIcon iconBtn = new ImageIcon("src/main/resources/images/swap.png");
//        buttonSwap.setIcon(iconBtn);

        for (String s : elements) comboBoxModelFrom.addElement(s);

        for (String element : elements) comboBoxModelTo.addElement(element);

        comboBoxFrom = new JComboBox<>(comboBoxModelFrom);
        comboBoxTo = new JComboBox<>(comboBoxModelTo);

        comboBoxFrom.setMaximumRowCount(9);
        comboBoxTo.setMaximumRowCount(9);
        comboBoxFrom.setMaximumRowCount(9);
        comboBoxTo.setMaximumRowCount(9);

        comboBoxTo.setSelectedIndex(1);

        valueTextFrom = new JTextField();
        valueTextTo = new JTextField();

        valueTextTo.setEditable(false);

        labelValueFrom.setBounds(10, 10, 115, 40);
        comboBoxFrom.setBounds(135, 10, 300, 40);
        valueTextFrom.setBounds(10, 60, 425, 40);
        labelValueTo.setBounds(10, 110, 115, 40);
        comboBoxTo.setBounds(135, 110, 300, 40);
        valueTextTo.setBounds(10, 160, 425, 40);
        buttonSwap.setBounds(445, 10, 50, 190);

        setLayout(null);
        setFocusable(true);

        add(labelValueFrom);
        add(comboBoxFrom);
        add(valueTextFrom);
        add(labelValueTo);

//        add(buttonSwap);

        add(valueTextTo);
        add(comboBoxTo);

        try {
            Image image = ImageIO.read(new File("src/main/resources/images/logo.png"));
            setIconImage(image);
        } catch (IOException exception) {
            System.out.println("Error!");
        }

        setResizable(false);
        setTitle("Конвертер валют");
        setSize(460, 250);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        valueTextFrom.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {

                if (valueTextFrom.getText().length() >= 50) // limit to 50 characters
                    e.consume();

                char symbol = e.getKeyChar();
                if ((symbol < '0') || (symbol > '9')) {
                    e.consume();  // игнорируем введенные буквы и пробел
                }

            }
        });

        comboBoxFrom.addActionListener(e -> {

            if ((Objects.equals(comboBoxFrom.getSelectedItem(), comboBoxTo.getSelectedItem()))) {
                int index = comboBoxFrom.getSelectedIndex();

                if (index >= 0 && index < 34) {
                    comboBoxFrom.setSelectedIndex(index + 1);
                }

                if (index <= 34 && index > 0) {
                    comboBoxFrom.setSelectedIndex(index - 1);
                }

                JOptionPane.showMessageDialog(null, "Выберите разные валюты.", "Ошибка", JOptionPane.ERROR_MESSAGE);

            }

            updateValue();
        });

        comboBoxTo.addActionListener(e -> {

            if ((Objects.equals(comboBoxTo.getSelectedItem(), comboBoxFrom.getSelectedItem()))) {
                int index = comboBoxTo.getSelectedIndex();

                if (index >= 0 && index < 34) {
                    comboBoxTo.setSelectedIndex(index + 1);
                }

                if (index <= 34 && index > 0) {
                    comboBoxTo.setSelectedIndex(index - 1);
                }

                JOptionPane.showMessageDialog(null, "Выберите разные валюты.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            updateValue();
        });

        valueTextFrom.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateValue();
            }

            public void removeUpdate(DocumentEvent e) {
                updateValue();
            }

            public void insertUpdate(DocumentEvent e) {
                updateValue();
            }

        });

    }

    public void updateValue() {
        //       valueTextTo.setText(Converter.convert(comboBoxFrom, comboBoxTo, valueTextFrom));
        try {
            Converter.getBigDecimalAmount(valueTextFrom);
            Converter.getNameCurrencyFrom(comboBoxFrom);
            Converter.getNameCurrencyTo(comboBoxTo);
            Converter.getNominalFrom();
            Converter.getNominalTo();
            Converter.getValueStringFrom();
            Converter.getValueStringTo();
            valueTextTo.setText(Converter.convert());

        } catch (NumberFormatException ignored) {
            valueTextTo.setText("");
        }


    }

}
