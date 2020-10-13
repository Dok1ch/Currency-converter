public class Currency {

    private String charCode;
    private int nominal;
    private String name;
    private String value;

    public Currency(String charCode, int nominal, String name, String value) {
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public Currency() {

    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Code:" + this.charCode + "\nNominal:" + this.nominal + "\nName:" + this.name + "\nValue:" + this.value + "\n";
    }
}
