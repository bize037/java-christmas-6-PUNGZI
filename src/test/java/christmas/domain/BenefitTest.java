package christmas.domain;

import static org.assertj.core.api.Assertions.*;

import christmas.common.constants.Menu;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BenefitTest {
    private Benefit benefit;

    private static final int DATE = 24;
    private static final int BEFORE_SALE_TOTAL_PAY = 145_000;

    @BeforeEach
    void generateBenefit() {
        benefit = new Benefit(DATE, menusAndTotalNumbers(), BEFORE_SALE_TOTAL_PAY);
    }

    @DisplayName("크리스마스 할인 정보를 정상적으로 출력하는가")
    @ValueSource(strings = {"크리스마스 디데이 할인: -3,300원"})
    @ParameterizedTest
    void printChristmasSaleTest(String outputSentence) {
        assertThat(benefit.printChristmasSale()).contains(outputSentence);
    }

    @DisplayName("특별 할인 정보를 정상적으로 출력하는가")
    @ValueSource(strings = {"특별 할인: -1,000원"})
    @ParameterizedTest
    void printSpecialSaleTest(String outputSentence) {
        assertThat(benefit.printSpecialSale()).contains(outputSentence);
    }

    @DisplayName("요일별 할인 정보를 정상적으로 출력하는가")
    @ValueSource(strings = {"평일 할인: -2,023원"})
    @ParameterizedTest
    void printDayOfWeekSaleTest(String outputSentence) {
        assertThat(benefit.printDayOfWeekSale()).contains(outputSentence);
    }

    @DisplayName("증정 이벤트 정보를 정상적으로 출력하는가")
    @ValueSource(strings = {"증정 이벤트: -25,000원"})
    @ParameterizedTest
    void printPresentEventTest(String outputSentence) {
        assertThat(benefit.printPresentEvent()).contains(outputSentence);
    }

    @DisplayName("총혜택 금액을 정상적으로 출력하는가")
    @ValueSource(strings = {"31,323원"})
    @ParameterizedTest
    void printAllBenefitTest(String outputSentence) {
        addTotalBenefitPrice();
        assertThat(benefit.printAllBenefitPay()).contains(outputSentence);
    }

    @DisplayName("할인 후 예상 결제 금액을 정상적으로 출력하는가")
    @ValueSource(strings = {"113,677원"})
    @ParameterizedTest
    void printAfterSalePayTest(String outputSentence) {
        addTotalBenefitPrice();
        assertThat(benefit.printAfterSalePay()).contains(outputSentence);
    }

    private HashMap<String, Integer> menusAndTotalNumbers() {
        return new HashMap<String, Integer>(){{
            put(Menu.MAIN_3.getMenu(), 2);
            put(Menu.DESSERT_1.getMenu(), 1);
            put(Menu.DRINK_2.getMenu(), 1);
        }};
    }

    private void addTotalBenefitPrice() {
        benefit.printChristmasSale();
        benefit.printSpecialSale();
        benefit.printDayOfWeekSale();
        benefit.printPresentEvent();
    }
}
