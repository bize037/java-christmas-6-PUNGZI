package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Benefit;
import christmas.domain.MenuCatalog;
import christmas.domain.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.HashMap;

public class EventController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    private VisitDate visitDate;
    private MenuCatalog menuCatalog;
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
        outputAllBenefitBefore();
        outputAllBenefitAfter();
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
            menuCatalog = new MenuCatalog(inputView.inputConsole());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            generateMenuAndTotalNumber();
        }
    }

    private void outputAllBenefitBefore() {
        outputView.outputDatePreviewMessage(visitDate.getDate());
        outputView.orderMenuAndTotalNumberMessage();
        outputMenuAndTotalNumber();
        outputView.outputPresentMenu(menuCatalog.presentMenu());
        outputView.outputPresentMenuMoreNeedPay(menuCatalog.presentMenuMoreNeedPay());
    }

    private void outputAllBenefitAfter() {
        generateBenefit(visitDate.getDate(), menuCatalog.getMenusAndTotalNumbers(), menuCatalog.getMenusTotalPay());
        outputAllBenefitPay(benefit.printAllBenefitPay());
        outputAfterSalePay(benefit.printAfterSalePay());
        outputEventBadge();
    }

    private void outputMenuAndTotalNumber() {
        menuCatalog.outputOrderMenuAndTotalNumber();
        outputView.outputBeforeSaleAllPay(menuCatalog.beforeSaleTotalPay());
    }

    private void generateBenefit(int date, HashMap<String, Integer> orderMenuAndTotalNumber, int beforeSaleTotalPay) {
        benefit = new Benefit(date, orderMenuAndTotalNumber, beforeSaleTotalPay);
        String benefitList = benefit.printChristmasSale() + benefit.printDayOfWeekSale() + benefit.printSpecialSale() + benefit.printPresentEvent();
        outputBenefit(benefitList);
    }

    private void outputBenefit(String benefitList) {
        outputView.benefitDetailsMessage(benefit.getTotalBenefitPrice());
        System.out.println(benefitList);
    }

    private void outputAllBenefitPay(String allBenefitPay) {
        outputView.allBenefitPayMessage();
        System.out.println(allBenefitPay);
    }

    private void outputAfterSalePay(String afterSalePay) {
        outputView.afterSalePayMessage();
        System.out.println(afterSalePay);
    }

    private void outputEventBadge() {
        outputView.eventBadgeMessage();
        System.out.println(Badge.getBadge(benefit.getTotalBenefitPrice()));
    }
}
