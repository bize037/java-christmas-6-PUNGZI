package christmas.controller;

import christmas.common.constants.OutputMessage;
import christmas.domain.Benefit;
import christmas.domain.MenuAndTotalNumber;
import christmas.domain.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;

public class EventController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    private VisitDate visitDate;
    private MenuAndTotalNumber menuAndTotalNumber;
    private Benefit benefit;

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
        outputMenuAndTotalNumber();

        generateBenefit(visitDate.getDate(), menuAndTotalNumber.getMenusAndTotalNumbers(), menuAndTotalNumber.beforeSaleTotalPay());
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

    private void outputMenuAndTotalNumber() {
        menuAndTotalNumber.outputOrderMenuAndTotalNumber();
        outputView.outputBeforeSaleAllPay(menuAndTotalNumber.beforeSaleTotalPay());
        outputView.outputPresentMenu(menuAndTotalNumber.presentMenu());
    }

    private void generateBenefit(int date, HashMap<String, Integer> orderMenuAndTotalNumber, int beforeSaleTotalPay) {
        benefit = new Benefit(date, orderMenuAndTotalNumber, beforeSaleTotalPay);
        String totalBenefitPrice = benefit.christmasSale() + benefit.dayOfWeekSale() + benefit.specialSale() + benefit.presentEvent();
        outputBenefit(totalBenefitPrice);
    }

    private void outputBenefit(String totalBenefitPrice) {
        System.out.println(OutputMessage.BENEFIT_DETAILS.getMessage());
        System.out.println(totalBenefitPrice);
    }
}
