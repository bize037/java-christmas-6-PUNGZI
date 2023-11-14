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

    @BeforeEach
    void generateBenefit() {
        benefit = new Benefit(24, menusAndTotalNumbers(), 145_000);
    }

    @DisplayName("크리스마스 할인 정보를 정상적으로 출력하는가")
    @ValueSource(strings = {"크리스마스 디데이 할인: -3,300원"})
    @ParameterizedTest
    void christmasSaleTest(String outputSentence) {
        assertThat(benefit.printChristmasSale()).contains(outputSentence);
    }

    private HashMap<String, Integer> menusAndTotalNumbers() {
        return new HashMap<String, Integer>(){{
            put(Menu.MAIN_3.getMenu(), 2);
            put(Menu.DESSERT_1.getMenu(), 1);
            put(Menu.DRINK_2.getMenu(), 1);
        }};
    }
}
