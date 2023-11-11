package christmas.controller;

import christmas.domain.MenuAndTotalNumber;
import christmas.domain.VisitDate;
import christmas.view.InputView;

public class EventController {
    InputView inputView = new InputView();

    public void startEvent() {
        inputView.inputVisitDateMessage();
        generateVisitDate();
        inputView.inputOrderMessage();
        generateOrder();
    }

    private void generateVisitDate() {
        try {
            new VisitDate(inputView.inputConsole());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            generateVisitDate();
        }
    }

    private void generateOrder() {
        try {
            new MenuAndTotalNumber(inputView.inputConsole());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            generateOrder();
        }
    }
}
