package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AddFeatureMenuCatalogTest {
    /*
    요구 사항에 들어있지 않지만, 고객에게 추가적으로 안내하면 좋을 것 같은 사항 구현 후 테스트
    기능에 대한 자세한 설명은 README의 추가 출력 사항 항목 참고
    */

    private static final String MORE_NEED_PAY = "11,000원";

    @DisplayName("입력한 메뉴의 가격 합이 95,000원 이상 120,000원 이하일 때 얼마를 더 결제하면 샴페인을 받을 수 있는지 정상적으로 출력")
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1"})
    @ParameterizedTest
    void presentMenuMoreNeedPayTest(String menuAndTotalNumber) {
        MenuCatalog menuCatalog = new MenuCatalog(menuAndTotalNumber);
        menuCatalog.beforeSaleTotalPay();
        assertThat(menuCatalog.presentMenuMoreNeedPay()).contains(MORE_NEED_PAY);

    }
}
