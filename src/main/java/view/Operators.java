package view;

public enum Operators {

    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    SEPARATOR("."),
    EQUALS("="),
    DELETE("Del"),
    CLEAR("Clr");

    private String btnText;

    Operators(String btnText) {
        this.btnText = btnText;
    }

    public String getBtnText() {
        return btnText;
    }
}
