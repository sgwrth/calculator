package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import state.CalculatorState;
import strategy.Addition;
import strategy.Division;
import strategy.Multiplication;
import strategy.Subtraction;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Text stateDisplay;
    @FXML
    public Text stateMinidisplay;

    private CalculatorController controller;
    private CalculatorState state;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controller = new CalculatorController();
        this.state = new CalculatorState(
                this.stateDisplay,
                this.stateMinidisplay,
                new StringBuilder(),
                new StringBuilder(),
                "",
                false
        );
    }

    public void enter0() {
         controller.btnClickZero(state, "0");
    }

    public void enter1() {
        controller.updateState(state, controller.btnClickOneToNine(state, "1"));
    }

    public void enter2() {
        controller.updateState(state, controller.btnClickOneToNine(state, "2"));
    }

    public void enter3() {
        controller.updateState(state, controller.btnClickOneToNine(state, "3"));
    }

    public void enter4() {
        controller.updateState(state, controller.btnClickOneToNine(state, "4"));
    }

    public void enter5() {
        controller.updateState(state, controller.btnClickOneToNine(state, "5"));
    }

    public void enter6() {
        controller.updateState(state, controller.btnClickOneToNine(state, "6"));
    }

    public void enter7() {
        controller.updateState(state, controller.btnClickOneToNine(state, "7"));
    }

    public void enter8() {
        controller.updateState(state, controller.btnClickOneToNine(state, "8"));
    }

    public void enter9() {
        controller.updateState(state, controller.btnClickOneToNine(state, "9"));
    }

    public void enterPeriod() {
        controller.btnClickPeriod(state, ".");
    }

    public void selectAddition() {
        controller.btnClickStrategy(state, new Addition(), "+ ");
    }

    public void selectSubtraction() {
        controller.btnClickStrategy(state, new Subtraction(), "- ");
    }

    public void selectMultiplication() {
        controller.btnClickStrategy(state, new Multiplication(), "* ");
    }

    public void selectDivision() {
        controller.btnClickStrategy(state, new Division(), "/ ");
    }

    public void equals() {
        controller.btnClickEquals(state);
    }

    public void delete() {
        controller.btnClickDelete(state);
    }

    public void clear() {
        controller.btnClickClear(state);
    }

}
