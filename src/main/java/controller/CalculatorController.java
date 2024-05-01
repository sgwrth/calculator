package controller;

import view.*;
import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {

    protected CalculatorView calculatorView;
    protected Calculator calculator;
    private StringBuilder a = new StringBuilder();
    private StringBuilder b = new StringBuilder();
    private StringBuilder selectString;
    private String temp = "";
    private IArithmeticStrategy arithmeticStrategy;

    public CalculatorController(
	    CalculatorView calculatorView,
	    Calculator calculator
    ) {
        this.calculatorView = calculatorView;
        this.calculator = calculator;
        calculatorView.setButtonClickListener(new ButtonClickListener());
	selectString = a;
    }

    class ButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "0" :
                    btnClickZero();
                    break;
                case "1" :
                    btnClickEinsBisNeun("1");
                    break;
                case "2" :
                    btnClickEinsBisNeun("2");
                    break;
                case "3" :
                    btnClickEinsBisNeun("3");
                    break;
                case "4" :
                    btnClickEinsBisNeun("4");
                    break;
                case "5" :
                    btnClickEinsBisNeun("5");
                    break;
                case "6" :
                    btnClickEinsBisNeun("6");
                    break;
                case "7" :
                    btnClickEinsBisNeun("7");
                    break;
                case "8" :
                    btnClickEinsBisNeun("8");
                    break;
                case "9" :
                    btnClickEinsBisNeun("9");
                    break;
                case "." :
                    btnClickPeriod();
                    break;
                case "+" :
                    btnClickRechenart(new Addition(), " + ");
                    break;
                case "-" :
                    btnClickRechenart(new Subtraction(), " - ");
                    break;
                case "*" :
                    btnClickRechenart(new Multiplication(), " * ");
                    break;
                case "/" :
                    btnClickRechenart(new Division(), " / ");
                    break;
                case "=" :
                    btnClickEquals();
                    break;
                case "Del" :
                    btnClickDelete();
                    break;
                case "Clr" :
                    btnClickClear();
                    break;
            }
        }

        public void btnClickEinsBisNeun(String number) {
            if (!selectString.toString().equals("0")) {
                selectString.append(number);
	    } else {
                selectString.replace(0, 1, number);
	    }
            calculatorView.writeToDisplay(temp + selectString);
        }

        public void btnClickZero() {
            if (!selectString.toString().equals("0")) {
                selectString.append("0");
	    }
            calculatorView.writeToDisplay(temp + selectString);
        }

        public void btnClickRechenart(
		IArithmeticStrategy strat,
		String operatorZeichen
	) {
            if (selectString == a) {
                arithmeticStrategy = strat;
                selectString = b;
                temp = removePeriod(a.toString()) + operatorZeichen;
                calculatorView.writeToDisplay(temp);
            } else {
                arithmeticStrategy = strat;
                temp = removePeriod(a.toString()) + operatorZeichen;
                calculatorView.writeToDisplay(temp + selectString);
            }
        }

        public void btnClickEquals() {
            try {
                double result = arithmeticStrategy.calculate(
			Double.parseDouble(a.toString()),
			Double.parseDouble(b.toString())
                );
                String resultString = String.valueOf(result);
                if (
			resultString.charAt(resultString.length() - 1) == '0'
                        && resultString.charAt(resultString.length() - 2) == '.'
		) {
                    StringBuilder cropped = new StringBuilder();
                    cropped.append(resultString);
                    cropped.delete(cropped.length() - 2, cropped.length());
                    calculatorView.writeToDisplay(cropped.toString());
                    a = new StringBuilder();
                    a.append(cropped);
                } else {
                    calculatorView.writeToDisplay(resultString);
                    a = new StringBuilder();
                    a.append(resultString);
                }
                selectString = a;
                b = new StringBuilder();
                temp = "";
            } catch (NumberFormatException numEx) {
                System.out.println("NumberFormatExcep...");
            }
        }

        public void btnClickDelete() {
            char[] tempArray = selectString.toString().toCharArray();
            if (selectString.length() == 2 && tempArray[0] == '-') {
                selectString.replace(0, 1, "0");
                selectString.delete(1, selectString.length());
            }
            if (
		    selectString.length() == 1
		    && !selectString.toString().equals("0")
	    ) {
                selectString.replace(0, 1, "0");
	    }
            if (selectString.length() > 1) {
                selectString.deleteCharAt(selectString.length() - 1);
            }
            calculatorView.writeToDisplay(temp + selectString.toString());
        }

        public void btnClickClear() {
            temp = "";
            a = new StringBuilder();
            b = new StringBuilder();
            selectString = a;
            selectString.append("0");
            calculatorView.writeToDisplay(selectString.toString());
        }

        public String removePeriod(String string) {
            try {
                char[] temp = string.toCharArray();
                if (temp[temp.length - 1] == '.') {
                    StringBuilder tempStringB = new StringBuilder();
                    tempStringB.append(string);
                    tempStringB.delete(tempStringB.length() - 1,
                            tempStringB.length());
                    string = tempStringB.toString();
                }
                return string;
            } catch (ArrayIndexOutOfBoundsException arrEx) {
                System.out.println("Error: ArrayIndexOutOfBounds...");
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
            calculatorView.writeToDisplay(temp + selectString);
        }
    }
}
