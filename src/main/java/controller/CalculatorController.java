package controller;

import model.Addition;
import model.Calculator;
import model.Division;
import model.IArithmeticStrategy;
import model.Multiplication;
import model.Subtraction;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
// import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class CalculatorController implements Initializable {

    public Text display;
    public Text minidisplay;

    private Calculator calculator;
    private StringBuilder a = new StringBuilder();
    private StringBuilder b = new StringBuilder();
    private StringBuilder selectString;
    private String temp = "";
    private IArithmeticStrategy arithmeticStrategy;

    public CalculatorController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.calculator = new Calculator();
        this.selectString = a;
    }

    public void enter0() {
        btnClickZero();
    }

    public void enter1() {
        btnClickEinsBisNeun("1");
    }

    public void enter2() {
        btnClickEinsBisNeun("2");
    }

    public void enter3() {
        btnClickEinsBisNeun("3");
    }

    public void enter4() {
        btnClickEinsBisNeun("4");
    }

    public void enter5() {
        btnClickEinsBisNeun("5");
    }

    public void enter6() {
        btnClickEinsBisNeun("6");
    }

    public void enter7() {
        btnClickEinsBisNeun("7");
    }

    public void enter8() {
        btnClickEinsBisNeun("8");
    }

    public void enter9() {
        btnClickEinsBisNeun("9");
    }

    public void enterPeriod() {
        btnClickPeriod();
    }

    public void selectAddition() {
        btnClickRechenart(new Addition(), " + ");
    }

    public void selectSubtraction() {
        btnClickRechenart(new Subtraction(), " - ");
    }

    public void selectMultiplication() {
        btnClickRechenart(new Multiplication(), " * ");
    }

    public void selectDivision() {
        btnClickRechenart(new Division(), " / ");
    }

    public void equals() {
        btnClickEquals();
    }

    public void delete() {
        btnClickDelete();
    }

    public void clear() {
        btnClickClear();
    }

    public void btnClickEinsBisNeun(String number) {
        if (!selectString.toString().equals("0")) {
            selectString.append(number);
        } else {
            selectString.replace(0, 1, number);
        }
        display.setText(temp + selectString);
    }

    public void btnClickZero() {
        if (!selectString.toString().equals("0")) {
            selectString.append("0");
        }
        display.setText(temp + selectString);
    }

    public void btnClickRechenart(
            IArithmeticStrategy strat,
            String operatorZeichen) {
        if (selectString == a) {
            arithmeticStrategy = strat;
            selectString = b;
            // temp = removePeriod(a.toString()) + operatorZeichen;
            temp = operatorZeichen;
            display.setText(temp);
            minidisplay.setText(a.toString());
        } else {
            arithmeticStrategy = strat;
            temp = removePeriod(a.toString()) + operatorZeichen;
            display.setText(temp + selectString);
        }
    }

    public void btnClickEquals() {
        try {
            double result = arithmeticStrategy.calculate(
                    Double.parseDouble(a.toString()),
                    Double.parseDouble(b.toString()));
            String resultString = String.valueOf(result);
            if (resultString.charAt(resultString.length() - 1) == '0'
                    && resultString.charAt(resultString.length() - 2) == '.') {
                StringBuilder cropped = new StringBuilder();
                cropped.append(resultString);
                cropped.delete(cropped.length() - 2, cropped.length());
                display.setText(cropped.toString());
                a = new StringBuilder();
                a.append(cropped);
            } else {
                if (isOverlong(resultString)) {
                    display.setText(cropOverlong(resultString));
                } else {
                    display.setText(resultString);
                }
                a = new StringBuilder();
                a.append(resultString);
            }
            selectString = a;
            b = new StringBuilder();
            temp = "";
            minidisplay.setText("");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public boolean isOverlong(String string) {
        if (string.length() >= 14) {
            return true;
        } else {
            return false;
        }
    }

    public String cropOverlong(String string) {
        if (string.length() >= 14) {
            String torso = string.substring(0, 11);
            return torso + "...";
        } else {
            return string;
        }
    }

    public void btnClickDelete() {
        char[] tempArray = selectString.toString().toCharArray();
        if (selectString.length() == 2 && tempArray[0] == '-') {
            selectString.replace(0, 1, "0");
            selectString.delete(1, selectString.length());
        }
        if (selectString.length() == 1 && !selectString.toString().equals("0")) {
            selectString.replace(0, 1, "0");
        }
        if (selectString.length() > 1) {
            selectString.deleteCharAt(selectString.length() - 1);
        }
        display.setText(temp + selectString.toString());
    }

    public void btnClickClear() {
        temp = "";
        a = new StringBuilder();
        b = new StringBuilder();
        selectString = a;
        selectString.append("0");
        display.setText(selectString.toString());
        minidisplay.setText("");
    }

    public String removePeriod(String string) {
        try {
            char[] temp = string.toCharArray();
            if (temp[temp.length - 1] == '.') {
                StringBuilder tempStringB = new StringBuilder();
                tempStringB.append(string);
                tempStringB.delete(tempStringB.length() - 1, tempStringB.length());
                string = tempStringB.toString();
            }
            return string;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return "0";
        }
    }

    public void btnClickPeriod() {
        if (selectString.length() == 0) {
            selectString.append("0");
            selectString.append(".");
        }
        char[] array = selectString.toString().toCharArray();
        boolean noPeriod = true;
        for (char c : array) {
            if (c == '.') {
                noPeriod = false;
                break;
            }
        }
        if (noPeriod) {
            selectString.append(".");
        }
        display.setText(temp + selectString);
    }

}
