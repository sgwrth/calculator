package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorView
{
    private JFrame calculatorFrame;
    private JTextField txt_display;
    private JPanel pnl_buttons;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;

    public CalculatorView()
    {
        this.createDisplay();
        this.createButtonPanel();
        this.createCalculatorFrame();

    }

    private void createCalculatorFrame()
    {
        this.calculatorFrame = new JFrame();
        this.calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.calculatorFrame.setSize(420, 550);

        this.calculatorFrame.setMinimumSize(new Dimension(400, 200));
        this.calculatorFrame.setPreferredSize(new Dimension(400, 200));

        this.calculatorFrame.setLayout(null);

        this.calculatorFrame.setLocationRelativeTo(null);

        this.calculatorFrame.getContentPane().setBackground(new Color(104, 139,
                173));
        this.calculatorFrame.setTitle("CALCUTRON 9001");

        this.calculatorFrame.add(this.txt_display);
        this.calculatorFrame.add(this.pnl_buttons);
        // todo: handle elsewhere
        operatorButtons[6].setBounds(50, 430, 145, 50);
        operatorButtons[7].setBounds(205, 430, 145, 50);
        this.calculatorFrame.add(this.operatorButtons[6]);
        this.calculatorFrame.add(this.operatorButtons[7]);

        this.calculatorFrame.setVisible(true);
    }

    private void createDisplay()
    {
        this.txt_display = new JTextField();
        this.txt_display.setBounds(50, 25, 300, 50);
        this.txt_display.setFont(new Font("Arial", Font.BOLD, 30));
        this.txt_display.setHorizontalAlignment(JTextField.RIGHT);
        this.txt_display.setEditable(false);
        this.txt_display.setText("");
    }

    private void createButtonPanel() {

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.format(String.valueOf(i)));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 30));
            numberButtons[i].setFocusable(false);
        }

        Operators[] operators = Operators.values();
        operatorButtons = new JButton[operators.length];
        for (int i = 0; i < operators.length; i++) {
            operatorButtons[i] = new JButton();
            operatorButtons[i].setFocusable(false);
            operatorButtons[i].setFont(new Font("Arial", Font.BOLD, 30));
            operatorButtons[i].setText(operators[i].getBtnText());
        }

        pnl_buttons = new JPanel();
        pnl_buttons.add(numberButtons[7]);
        pnl_buttons.add(numberButtons[8]);
        pnl_buttons.add(numberButtons[9]);
        pnl_buttons.add(operatorButtons[Operators.ADDITION.ordinal()]);

        pnl_buttons.add(numberButtons[4]);
        pnl_buttons.add(numberButtons[5]);
        pnl_buttons.add(numberButtons[6]);
        pnl_buttons.add(operatorButtons[Operators.SUBTRACTION.ordinal()]);

        pnl_buttons.add(numberButtons[1]);
        pnl_buttons.add(numberButtons[2]);
        pnl_buttons.add(numberButtons[3]);
        pnl_buttons.add(operatorButtons[Operators.MULTIPLICATION.ordinal()]);

        pnl_buttons.add(operatorButtons[Operators.EQUALS.ordinal()]);
        pnl_buttons.add(numberButtons[0]);
        pnl_buttons.add(operatorButtons[Operators.SEPARATOR.ordinal()]);
        pnl_buttons.add(operatorButtons[Operators.DIVISION.ordinal()]);

        pnl_buttons.setBounds(50, 100, 300, 300);
        pnl_buttons.setBackground(new Color(87, 118, 148));
        pnl_buttons.setLayout(new GridLayout(4, 4, 10, 10));
    }

    public void setButtonClickListener(ActionListener buttonClickListener) {
        for (JButton numberButton : numberButtons) {
            numberButton.addActionListener(buttonClickListener);
	}
        for (JButton operatorButton : operatorButtons) {
            operatorButton.addActionListener(buttonClickListener);
	}
    }

    public void writeToDisplay(String content) {
        txt_display.setText(content);
    }
}
