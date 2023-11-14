package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.common.constants.Menu;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NotApplyBenefitTest {
    private Benefit benefit;

    private static final int DATE = 30;
    private static final int BEFORE_SALE_TOTAL_PAY = 6_000;

    @BeforeEach
    void generateBenefit() {
        benefit = new Benefit(DATE, menusAndTotalNumbers(), BEFORE_SALE_TOTAL_PAY);
    }

    // 이벤트 미적용 시 할인 정보가 뜨지 않아야 함 -> 뜨지 않는지 테스트

    @DisplayName("크리스마스 할인 정보가 뜨지 않는가")
    @ValueSource(strings = {"크리스마스 디데이 할인"})
    @ParameterizedTest
    void printChristmasSaleTest(String outputSentence) {
        assertThat(benefit.printChristmasSale()).doesNotContain(outputSentence);
    }

    @DisplayName("특별 할인 정보가 뜨지 않는가")
    @ValueSource(strings = {"특별 할인"})
    @ParameterizedTest
    void printSpecialSaleTest(String outputSentence) {
        assertThat(benefit.printSpecialSale()).doesNotContain(outputSentence);
    }

    @DisplayName("요일별 할인 정보가 뜨지 않는가")
    @ValueSource(strings = {"평일 할인"})
    @ParameterizedTest
    void printDayOfWeekSaleTest(String outputSentence) {
        assertThat(benefit.printDayOfWeekSale()).doesNotContain(outputSentence);
    }

    @DisplayName("증정 이벤트 정보가 뜨지 않는가")
    @ValueSource(strings = {"증정 이벤트"})
    @ParameterizedTest
    void printPresentEventTest(String outputSentence) {
        assertThat(benefit.printPresentEvent()).doesNotContain(outputSentence);
    }

    @DisplayName("총혜택 금액을 정상적으로 출력하는가")
    @ValueSource(strings = {"0원"})
    @ParameterizedTest
    void printAllBenefitTest(String outputSentence) {
        addTotalBenefitPrice();
        assertThat(benefit.printAllBenefitPay()).contains(outputSentence);
    }

    @DisplayName("할인 후 예상 결제 금액을 정상적으로 출력하는가")
    @ValueSource(strings = {"6,000원"})
    @ParameterizedTest
    void printAfterSalePayTest(String outputSentence) {
        addTotalBenefitPrice();
        assertThat(benefit.printAfterSalePay()).contains(outputSentence);
    }

    private HashMap<String, Integer> menusAndTotalNumbers() {
        return new HashMap<String, Integer>(){{
            put(Menu.APPETIZER_1.getMenu(), 1);
        }};
    }

    private void addTotalBenefitPrice() {
        benefit.printChristmasSale();
        benefit.printSpecialSale();
        benefit.printDayOfWeekSale();
        benefit.printPresentEvent();
    }
}
