package christmas.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import christmas.common.constants.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuCatalogTest {
    @DisplayName("입력한 메뉴 및 개수가 정상적으로 저장되는가")
    @ValueSource(strings = {"양송이수프-2,초코케이크-1", "바비큐립-3,제로콜라-2"})
    @ParameterizedTest
    void saveMenuCatalogTest(String menuAndTotalNumber) {
        assertThatCode(() -> new MenuCatalog(menuAndTotalNumber))
                .doesNotThrowAnyException();
    }

    @DisplayName("공백을 입력하면 예외처리 하는가")
    @Test
    void blankValidateMenuCatalogTest() {
        String inBlank = "";
        assertThatThrownBy(() -> new MenuCatalog(inBlank))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("','를 입력하지 않으면 예외처리 하는가")
    @ValueSource(strings = {"양송이수프-2 초코케이크-1"})
    @ParameterizedTest
    void notInputCommaMenuCatalogTest(String menuAndTotalNumber) {
        assertThatThrownBy(() -> new MenuCatalog(menuAndTotalNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("'-'을 입력하지 않으면 예외처리 하는가")
    @ValueSource(strings = {"양송이수프 2,초코케이크-1", "양송이수프-2,초코케이크 1"})
    @ParameterizedTest
    void notInputHyphenMenuCatalogTest(String menuAndTotalNumber) {
        assertThatThrownBy(() -> new MenuCatalog(menuAndTotalNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("메뉴판에 없는 메뉴를 입력하면 예외처리 하는가")
    @ValueSource(strings = {"딸기케이크-3,초코케이크-1", "양송이수프-1,크림수프-2"})
    @ParameterizedTest
    void notInMenuListMenuCatalogTest(String menuAndTotalNumber) {
        assertThatThrownBy(() -> new MenuCatalog(menuAndTotalNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("같은 메뉴를 2개 이상 입력하면 예외처리 하는가")
    @ValueSource(strings = {"초코케이크-3,양송이수프-1,초코케이크-1"})
    @ParameterizedTest
    void overlapMenuCatalogTest(String menuAndTotalNumber) {
        assertThatThrownBy(() -> new MenuCatalog(menuAndTotalNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("메뉴 개수의 합이 20을 초과하면 예외처리 하는가")
    @ValueSource(strings = {"바비큐립-15,양송이수프-7"})
    @ParameterizedTest
    void addTotalMenuCatalogTest(String menuAndTotalNumber) {
        assertThatThrownBy(() -> new MenuCatalog(menuAndTotalNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER.getMessage());
    }
}
