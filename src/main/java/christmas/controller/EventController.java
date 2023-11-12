package christmas.controller;

import christmas.domain.MenuAndTotalNumber;
import christmas.domain.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    private VisitDate visitDate;
    private MenuAndTotalNumber menuAndTotalNumber;

    public void startEvent() {
        inputEvent();
        outputEvent();
    }

    private void inputEvent() {
        inputView.visitDateMessage();
        generateVisitDate();
        inputView.menuAndTotalNumberMessage();
        generateMenuAndTotalNumber();
    }

    private void outputEvent() {
        outputView.outputDatePreviewMessage(visitDate.monthAndDate());
        outputView.orderMenuAndTotalNumberMessage();
        menuAndTotalNumber.outputOrderMenuAndTotalNumber();
        outputView.outputBeforeSaleAllPay(menuAndTotalNumber.beforeSaleAllPay());
        outputView.outputPresentMenu(menuAndTotalNumber.presentMenu());
    }

    private void generateVisitDate() {
        try {
            visitDate = new VisitDate(inputView.inputConsole());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            generateVisitDate();
        }
    }

    private void generateMenuAndTotalNumber() {
        try {
            menuAndTotalNumber = new MenuAndTotalNumber(inputView.inputConsole());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            generateMenuAndTotalNumber();
        }
    }
}
